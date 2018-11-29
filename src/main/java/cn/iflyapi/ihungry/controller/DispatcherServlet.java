package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.annotation.*;
import cn.iflyapi.ihungry.databind.PropertyEditorRegistry;
import cn.iflyapi.ihungry.databind.PropertyEditorRegistrySupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyEditor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.*;

/**
 * @author: qfwang
 * @date: 2018-11-01 11:20 AM
 */
public class DispatcherServlet extends HttpServlet {


    private Map<String, Object> beanFactory = new HashMap<>();

    private List<String> clazzNames = new ArrayList<>();

    private Map<String, Method> handlerMapping = new HashMap<>();

    private Map<Method, String> methodInstanceMap = new HashMap<>();

    private PropertyEditorRegistry propertyEditorRegistry = new PropertyEditorRegistrySupport();

    @Override
    public void init() throws ServletException {

        scanPackage("cn.iflyapi.ihungry");
        System.out.println(clazzNames.toString());

        try {
            newInstance();
            di();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println(beanFactory.size());
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
                beanFactory.put(service.value(), ob);
            }
            if (isController) {
                Controller controller = (Controller) clazz.getAnnotation(Controller.class);
                beanFactory.put(controller.value(), ob);
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

        for (String key : beanFactory.keySet()) {
            Field[] fields = beanFactory.get(key).getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    String instanceName = qualifier.value();
                    Object target = beanFactory.get(instanceName);
                    field.setAccessible(true);
                    field.set(beanFactory.get(key), target);
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
                handlerMapping.put(getMapping.method() + formatUrl(getMapping.value()), method);
                methodInstanceMap.put(method, controller.value());
            }

            if (isPost) {
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                handlerMapping.put(postMapping.method() + formatUrl(postMapping.value()), method);
                methodInstanceMap.put(method, controller.value());
            }
        }
    }

    private String formatUrl(String url) {
        String realUrl;
        if (!url.startsWith("/")) {
            realUrl = "/" + url;
        } else {
            realUrl = url;
        }
        return realUrl.replaceAll("//", "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> requestParamMap = req.getParameterMap();

        String uri = req.getRequestURI().replaceAll(req.getContextPath(), "");
        String url = req.getMethod() + uri;

        Method method = handlerMapping.get(url);
        if (Objects.isNull(method)) {
            return;
        }

        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        Object o = null;
        for (int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            String[] val = requestParamMap.get(name);
            if (Objects.isNull(val)) {
                continue;
            }

            Class clazz = parameters[i].getType();
            String instanceName = methodInstanceMap.get(method);
            o = beanFactory.get(instanceName);
            PropertyEditor propertyEditor = propertyEditorRegistry.findCustomConverter(clazz);
            args[i] = propertyEditor.getValue();
        }
        try {
            method.invoke(o, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String params = req.getReader().readLine();
    }

    public static void main(String[] args) {
        System.out.println(Integer.class.isAssignableFrom(Number.class));
        System.out.println(Number.class.isAssignableFrom(Integer.class));
    }

}
