package com.deceive.jvm.classloader;

import java.lang.reflect.Method;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/6 17:07
 * @Modified By: Cherry
 *
 * 类加载器的双亲委托模型的好处：
 *  1.可以确保Java核心库的类型安全：所有的Java应用都至少会引用java.lang.Object类，也就是说在运行期，java.lang.Object这个类
 *      会被加载到Java虚拟机中；如果这个加载过程是由Java应用自己的类加载器所完成的，那么很可能就会在JVM中存在多个版本的java.lang.Object类，
 *      而这些类之间还是不兼容的，相互不可见的（命名空间在发挥作用）
 *      借助于双亲委托机制，Java核心类库中的类的加载工作都是由启动/根类加载器来统一完成，从而确保了Java应用所使用的都是同一个版本的Java核心类库
 *      他们之间是相互兼容的，可见的。
 *  2.可以确保Java核心类库所提供的类不会被自定义的类所替代
 *  3.不同的类加载器可以为相同名称（binary name）的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要用没有任何父子关系的类加载器来加载它们即可。
 *      不同的且没有父子关系的类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一个又一个相互隔离的Java类空间
 *
 *  自己理解：
 *  3.不同的类加载器可以为相同名称（binary name）的类创建额外的命名空间。相同名称的类可以并存在Java虚拟机中，只需要用没有任何父子关系的类加载器来加载它们即可。
 *      不同的且没有父子关系的类加载器所加载的类之间是不兼容的，这就相当于在Java虚拟机内部创建了一个又一个相互隔离的Java类空间
 *
 *
 */
public class MyTest21 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        loader1.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        loader2.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");

        Class<?> clazz1 = loader1.loadClass("com.deceive.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.deceive.jvm.classloader.MyPerson");

        System.out.println(clazz1.getClassLoader());
        System.out.println(clazz1 == clazz2);
        ;
        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();
        ;
        //object1和object2 不在一个命名空间 相互不可见

        //获得setMyPerson方法所对应的Method对象
        Method method = clazz1.getMethod("setMyPerson", Object.class);
        //如果两个加载器之间没有直接或间接的父子关系，那么它们各自加载的类相互不可见
        method.invoke(object1,object2);
        //不能这样调用 相互不可见
    }
}
