package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 12:15
 * @Modified By: Cherry
 */
public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}

class Parent{
    static int a = 4;
    static {
        System.out.println("Parent static block");
    }

}

class Child extends Parent{
    static int b = 4;
    static {
        System.out.println("Child static block");
    }
}