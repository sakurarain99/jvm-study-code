package com.deceive.jvm.bytecode;


/*
    方法的静态分派
    Grandpa g1 = new Father();
    以上代码，g1的静态类型是Grandpa，而g1的实际类型(真正指向的类型)是Father。
    则可以得出一个结论：
        变量的静态类型是不会发生变化的，而变量的实际类型则是可以发生变化的(多态的一种体现)，
        实际类型是在运行期方可确定的。
 */

/**
 * @program: JVM_lecture
 * @description: Java静态分派   重载
 * @author: Mr.Cherry
 * @create: 2019-11-25 10:40
 **/
public class MyTest5 {

    //方法重载 是一种静态行为 (在调用重载方法的时候JVM会根据方法本身接收的参数的静态类型，来决定调用哪一个方法，而不是方法参数的实际类型决定的)
    //编译期就可以完全确定。


    /*
        重载和重写是一个完全不同的概念
        重载：本身是一种静态的概念
        重写：是一种动态的概念
     */

    public void test(Grandpa grandpa){
        System.out.println("grandpa");
    }
    public void test(Father pather){
        System.out.println("pather");
    }
    public void test(Son son){
        System.out.println("son");
    }

    public static void main(String[] args) {
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();

        MyTest5 myTest5 = new MyTest5();
        myTest5.test(g1);
        myTest5.test(g2);
    }



}


class Grandpa{

}
class Father extends Grandpa{

}
class Son extends Father{

}
