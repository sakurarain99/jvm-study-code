package com.deceive.jvm.bytecode;

/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-25 09:21
 **/

/*
    栈帧(stack frame)

    栈帧是一种用于帮助虚拟机执行方法调用与方法执行的数据结构

    栈帧本身是一种数据结构，封装了方法的局部变量表、动态链接信息、方法的返回值以及操作数栈等信息。

    符号引用、直接引用

    有些符号引用是在类加载阶段或是第一次使用时就会转化为直接引用，这种转换叫做静态解析(字节码还没有真正运行时)；
    另外一些符号引用则是在每次运行期转换为直接引用，这种转换叫做动态链接(程序每次运行时)，这体现为Java的多态性

    invokevirtual 会检查当前对象a 在此刻真正指向的那个对象是什么，
        从而把由对Animal的sleep调用，转换为对于它真正指向的那个对象的，特定的sleep方法的调用。
    动态链接：
        Animal a = new Cat();
        a.sleep();
        a = new Dog();
        a.sleep();
 */
/*
    JVM方法调用的字节码指令一共存在五种情况：
    1.invokeinterface：调用接口中的方法，实际上是在运行期决定的，决定到底调用实现该接口
    2.invokestatic：调用静态方法。
    3.invokespecial：调用自己的私有方法、构造方法(<init>)以及父类的方法
    4.invokevirtual：调用虚方法，运行期动态查找的过程
    5.invokedynamic：动态调用方法。

    静态解析的4中情形：
        1.静态方法
        2.父类方法
        3.构造方法
        4.私有方法 (为什么会有私有方法，因为公有方法会存在重写 那么就会存在多态的可能，而私有方法是不能被重写的，因此它在类加载的时候就可以确定下来)
        以上4类方法称作非虚方法，他们是在类加载阶段就可以将符号引用转化为直接引用的。

 */

/***/
public class MyTest4 {


    private void test(){

    }
    private void test(int i){

    }

}
