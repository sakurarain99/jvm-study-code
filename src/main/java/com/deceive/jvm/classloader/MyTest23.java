package com.deceive.jvm.classloader;

import sun.misc.Launcher;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/6 18:24
 * @Modified By: Cherry
 *
 * 在运行期，一个Java类是由该类的完全限定名（binary name <二进制名>）和用于加载该类的定义类加载器（defining loader）所共同决定的
 * 如果同样的名字(即相同的完全限定名)的类是由两个不同的类加载器所加载，那么这些类就是不同的，即便.class文件和字节码完全一样，即使从相同的位置加载亦如此。
 *
 * 在Oracle的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则运行会出错，提示如下错误信息
 *  Error occurred during initialization of VM
 *  java/lang/NoClassDefFoundError: java/lang/Object
 */
public class MyTest23 {
    public static void main(String[] args) {
        System.out.println("根/启动类加载器："+System.getProperty("sun.boot.class.path"));
        System.out.println("扩展类加载器："+System.getProperty("java.ext.dirs"));
        System.out.println("系统/应用类加载器："+System.getProperty("java.class.path"));

        System.out.println("ClassLoader的类加载器："+ClassLoader.class.getClassLoader());
        ///扩展类加载器与系统类加载器也是启动类加载器加载的
        System.out.println("扩展/系统的类加载器："+Launcher.class.getClassLoader());

        System.out.println("----------------------");


        System.out.println(ClassLoader.getSystemClassLoader());
        //如果java.system.class.loader 是未定义的 那么系统类加载器就会默认指向 AppClassLoader
        System.out.println(System.getProperty("java.system.class.loader"));
        /*
            java -Djava.system.class.loader=com.deceive.jvm.classloader.MyTest16 com.deceive.jvm.classloader.MyTest23
            修改java.system.class.loader的值为自定义类加载器 从而把自定义类加载器设置为系统类加载器
         */
        System.out.println("MyTest23的类加载器："+MyTest23.class.getClassLoader());
        System.out.println("MyTest16的类加载器："+MyTest16.class.getClassLoader());

        System.out.println("设置java.system.class.loader后的系统类加载器是："+ClassLoader.getSystemClassLoader());

        System.out.println(MyTest23.class);

    }
}




/*
如果ClassLoader.getSystemClassLoader() 首先在运行时的启动顺序中首先调用此方法，此后它将创建系统类加载器并将其设置为调用线程的上下文类加载器。在首次调用此方法时定义了系统属性“ java.system.class.loader”，则该属性的值将要作为系统类加载器返回的类的名称。
该类使用默认的系统类加载器加载，并且必须定义一个公共构造函数，该构造函数采用单个类型为ClassLoader的参数作为委托父级。
然后使用此构造函数创建一个实例，并使用默认系统类加载器作为参数。结果类加载器定义为系统类加载器。
If the system property "java.system.class.loader" is defined when this method is first invoked then the value of that property is taken to be the name of a class that will be returned as the system class loader. The class is loaded using the default system class loader and must define a public constructor that takes a single parameter of type ClassLoader which is used as the delegation parent. An instance is then created using this constructor with the default system class loader as the parameter. The resulting class loader is defined to be the system class loader.





 This method is first invoked early in the runtime's startup sequence, at which point it creates the system class loader and sets it as the context class loader of the invoking Thread.










Returns a Constructor object that reflects the specified constructor of the class or interface represented by this Class object. The parameterTypes parameter is an array of Class objects that identify the constructor's formal parameter types, in declared order. If this Class object represents an inner class declared in a non-static context, the formal parameter types include the explicit enclosing instance as the first parameter.
 */