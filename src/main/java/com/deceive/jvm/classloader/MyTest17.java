package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/5 18:19
 * @Modified By: Cherry
 *
 * JVM参数： -XX:+TraceClassLoading
 */
public class MyTest17 {

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        //只是把一个class加载到JVM中
        Class<?> clazz = loader1.loadClass("com.deceive.jvm.classloader.MySample");
        System.out.println("hashCode："+clazz.hashCode());

        //使用反射 对类的主动使用 会初始化类 （如果注释掉该行，那么并不会实例化MySample对象，即构造方法不会被调用）
        //因此不会实例化MyCat 对象,既没有对MyCat进行主动使用 这里就不会加载MyCat class
        Object object = clazz.newInstance();





    }

}
