package com.deceive.jvm.bytecode;

import java.util.Date;

/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-25 12:54
 **/

/*
    针对于方法调用动态分派的过程，虚拟机会在类的方法区建立一个虚方法表的数据结构(virtual method table，简称vtable)
    针对于invokeinterface指令来说，虚拟机会建立一个叫做接口方法表的数据结构 (interface method table，简称itable)

    虚方法表：
        虚方法表中的每一个方法都是被描述出一个方法调用的入口地址，如果子类没有重写父类的某个方法，
        那么子类的虚方法表不会将这个方法复制出来重新复制一份，而是直接指向/引用父类中方法的入口地址
     如果子类和父类存在重写的关系的话，那么对于相同描述符的方法，的索引是一样的，子类找完找父类，那么就可以直接在方法表中通过索引查找
        可以大大提升效率
 */

public class MyTest7 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.test("hello");
        dog.test(new Date());
    }
}

class Animal{
    public void test(String str){
        System.out.println("Animal str");
    }
    public void test(Date date){
        System.out.println("Animal date");
    }
}

class Dog extends Animal{
    @Override
    public void test(String str){
        System.out.println("Animal str");
    }
    @Override
    public void test(Date date){
        System.out.println("Animal date");
    }
}