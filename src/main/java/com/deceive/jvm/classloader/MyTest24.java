package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/8 11:29
 * @Modified By: Cherry
 */
/**
 当前类加载器(Current Classloader)
    每个类都会使用自己的类加载器(即加载自身的类加载器)来去加载其它类(指的是所依赖/使用的类)
    如果ClassX引用了ClassY 那么ClassX的类加载器就回去加载ClassY(前提是ClassY尚未被加载)

 线程上下文类加载器(Context ClassLoader)
 线程上下文类加载器是从JDK 1.2开始引入的 类Thread中的getContextClassLoader()和setContextClassLoader(ClassLoader cl)
 分别用来获取和设置上下文类加载器

 如果没有通过setContextClassLoader(ClassLoader cl)进行设置的话，线程将继承其父线程的上下文类加载器。
 Java应用运行时的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过该类加载器来加载类与资源。

 SPI(Service Provider Interface)<服务提供者>

 父ClassLoader可以使用当前线程Thread.currentThread().getContextClassLoader()所指定的classloader所加载的类
 这就改变了父ClassLoader不能使用子ClassLoader或是其它没有直接父子关系的ClassLoader所加载的类的情况，即改变了双亲委托模型。

 */
public class MyTest24 {

    public static void main(String[] args) {
        //Thread.currentThread()获取当前正在执行的线程对象的引用
        System.out.println("当前线程的上下文类加载器："+Thread.currentThread().getContextClassLoader());
        System.out.println("Thread类的类加载器："+Thread.class.getClassLoader());
    }
}


