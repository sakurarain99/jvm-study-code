package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/6 16:21
 * @Modified By: Cherry
 *
 * 所有加载器加载的路径
 */
public class MyTest18 {
    public static void main(String[] args) {
        System.out.println("根/启动类加载器："+System.getProperty("sun.boot.class.path"));
        System.out.println("扩展类加载器："+System.getProperty("java.ext.dirs"));
        System.out.println("系统/应用类加载器："+System.getProperty("java.class.path"));
    }
}
