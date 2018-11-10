package cn.iflyapi.ihungry.controller;

import cn.iflyapi.ihungry.annotation.Controller;
import cn.iflyapi.ihungry.annotation.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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

    private Map<String,Object> handlerMapping = new HashMap<>();

    @Override
    public void init() throws ServletException {

        scanPackage("cn.iflyapi.ihungry");
        System.out.println(clazzNames.toString());

        try {
            newInstance();
        } catch (ClassNotFoundException | InstantiationException |IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(instantMap.size());
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
            }
        }
    }

    private void ioc(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
