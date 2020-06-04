package com.deceive.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/6 16:50
 * @Modified By: Cherry
 */
public class MyTest20 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        Class<?> clazz1 = loader1.loadClass("com.deceive.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.deceive.jvm.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);
        Object object1 = clazz1.newInstance();
        Object object2 = clazz1.newInstance();

        //获得setMyPerson方法所对应的Method对象
        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1,object2);

    }

}
