package com.deceive.jvm.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 17:52
 * @Modified By: Cherry
 */
public class MyTest14 {
    public static void main(String[] args) throws IOException {

        ClassLoader classLoader = MyTest14.class.getClassLoader();
        System.out.println(classLoader);

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        String resourceName = "com/deceive/jvm/classloader/MyTest13.class";
        Enumeration<URL> urls= loader.getResources(resourceName);
        while (urls.hasMoreElements()){
            URL url = urls.nextElement();
            System.out.println(url);
        }


        System.out.println("--------------------------");

        Class<?> clazz = Integer.class;
        System.out.println(clazz.getClassLoader());

    }
}

