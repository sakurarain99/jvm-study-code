package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description: 测试双亲委托机制
 * @Date: 2019/10/4 11:45
 * @Modified By: Cherry
 */
public class MyTest7 {
    public static void main(String[] args) throws Exception{
        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz.getClassLoader());

        //获取一个类对象
        Class<?> clazz2 = Class.forName("com.deceive.jvm.classloader.C");
        //获取加载这个类的类加载器
        System.out.println(clazz2.getClassLoader());

    }
}


/**
 * 被加载的类
 */
class C{

}