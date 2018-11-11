package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: qfwang
 * @date: 2018-11-01 11:20 AM
 */
public class DispatcherServlet extends HttpServlet {


    private Map<String, Object> instantMap = new HashMap<>();

    private List<String> clazzNames = new ArrayList<>();

    private Map<String, Method> handlerMapping = new HashMap<>();

    private Map<Method, String> methodInstanceMap = new HashMap<>();

    @Override
    public void init() throws ServletException {

/*        scanPackage("cn.iflyapi.ihungry");
        System.out.println(clazzNames.toString());

        try {
            newInstance();
        } catch (ClassNotFoundException | InstantiationException |IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(instantMap.size());*/
    }

    /**
     * 扫描包下所有类文件名
     *
     * @param basePackage
     */
    private void scanPackage(String basePackage) {
        String s = packageToPath(basePackage);
        URL url = this.getClass().getClassLoader().getResource(s);

        File baseFilePath = new File(url.getPath());

        File[] files = baseFilePath.listFiles();

        for (File file : files) {

            if (file.isDirectory()) {
                scanPackage(basePackage + "." + file.getName());
            } else {
                clazzNames.add(basePackage + "." + file.getName().split("\\.")[0]);
            }
        }

    }

    /**
     * 包名称转为路径
     *
     * @param basePackage
     * @return
     */
    private String packageToPath(String basePackage) {
        return basePackage.replaceAll("\\.", "/");
    }


    /**
     * 创建实例
     *
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private void newInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        for (String clazzName : clazzNames) {
            Class clazz = Class.forName(clazzName);


            boolean isService = clazz.isAnnotationPresent(Service.class);
            boolean isController = clazz.isAnnotationPresent(Controller.class);

            if (!isService && !isController) {
                continue;
            }
            Object ob = clazz.newInstance();

            if (isService) {
                Service service = (Service) clazz.getAnnotation(Service.class);
                instantMap.put(service.value(), ob);
            }
            if (isController) {
                Controller controller = (Controller) clazz.getAnnotation(Controller.class);
                instantMap.put(controller.value(), ob);
                handlerMapping(ob, controller);
            }
        }
    }

    /**
     * 依赖注入
     *
     * @throws IllegalAccessException
     */
    private void di() throws IllegalAccessException {

        for (String key : instantMap.keySet()) {
            Field[] fields = instantMap.get(key).getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    String instanceName = qualifier.value();
                    Object target = instantMap.get(instanceName);
                    field.setAccessible(true);
                    field.set(instantMap.get(key), target);
                }
            }
        }
    }

    /**
     * 请求路径映射
     *
     * @param o
     * @param controller
     */
    private void handlerMapping(Object o, Controller controller) {
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method method : methods) {
            boolean isPost = method.isAnnotationPresent(PostMapping.class);
            boolean isGet = method.isAnnotationPresent(GetMapping.class);
            if (isGet) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                handlerMapping.put(getMapping.method() + getMapping.value(), method);
                methodInstanceMap.put(method, controller.value());
            }

            if (isPost) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                handlerMapping.put(postMapping.method() + postMapping.value(), method);
                methodInstanceMap.put(method, controller.value());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
