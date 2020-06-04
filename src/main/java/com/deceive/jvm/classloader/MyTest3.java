package com.deceive.jvm.classloader;

import java.util.UUID;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/9/28 20:52
 * @Modified By: Cherry
 *
 * 当一个常量的值并非编译期间可以确定的，那么其值就不会被放到调用类的常量池中
 * 这时在程序运行时，会导致主动使用这个常量所在的类，那么显然会导致这个类被初始化
 *
 */
public class MyTest3 {

    public static void main(String[] args) {
        System.out.println(MyParent3.str);
    }

}

class MyParent3{
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static code");
    }


}