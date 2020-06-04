package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 17:12
 * @Modified By: Cherry
 *
 * 调用ClassLoader类的loadClass方法加载一个类 并不是对类的主动使用 不会导致类的初始化
 *  ClassLoader.getSystemClassLoader(); 返回一个系统/应用类加载器  >> 返回一个ClassLoader
 *  加载一个类  ClassLoader.loadClass("类全名");                   >> 返回一个Class<?>
 *  反射Class.forName("全类名");                                   >> 返回一个Class<?>
 *
 */
public class MyTest12 {
    public static void main(String[] args)  throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> clazz = loader.loadClass("com.deceive.jvm.classloader.CL");
        System.out.println(clazz);
        System.out.println("----------------");
        clazz = Class.forName("com.deceive.jvm.classloader.CL");

        System.out.println(clazz);
    }
}

class CL{
    static{
        System.out.println("Class CL");
    }
}
