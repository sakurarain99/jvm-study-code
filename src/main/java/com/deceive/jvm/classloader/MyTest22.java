package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description: 扩展类加载器
 * @Date: 2019/10/6 18:07
 * @Modified By: Cherry
 *
 *  jar cvf test.jar com/deceive/jvm/classloader/MyTest.class
 *  想要扩展类加载器加载 需要把class打成jar包
 *
 *  java -Djava.ext.dirs=./ com.deceive.jvm.classloader.MyTest22
 */
public class MyTest22 {

    static{
        System.out.println("MyTest22 initializer");
    }

    public static void main(String[] args) {
        System.out.println(MyTest22.class.getClassLoader());

        System.out.println(MyTest.class.getClassLoader());
    }
}
