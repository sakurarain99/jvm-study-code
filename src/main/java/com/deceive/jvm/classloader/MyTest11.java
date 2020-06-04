package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 12:28
 * @Modified By: Cherry
 */
public class MyTest11 {
    public static void main(String[] args) {
        System.out.println(Child3.a);
        System.out.println("----------");
        Child3.doSomething();
    }
}

class Parent3{
    static int a = 3;
    static {
        System.out.println("Parent3 static block");
    }
    static void doSomething(){
        System.out.println("do something");
    }

}

class Child3 extends Parent3{
    static {
        System.out.println("Child3 static block");
    }
}