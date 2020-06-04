package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/6 16:28
 * @Modified By: Cherry
 */
public class MyTest18_1 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        Class<?> clazz = loader1.loadClass("com.deceive.jvm.classloader.MyTest");
        System.out.println("hashCode：" + clazz.hashCode());
        System.out.println("class loader：" + clazz.getClassLoader());

    }
}
