package com.deceive.jvm.memory;


/*
JVM所管理的内存空间：
    虚拟机栈：stack Frame 栈帧 (存放 对于方法字节码执行时的，入栈、出栈、局部变量表相关的信息)
    程序计数器：(Program Counter)：字节码执行的顺序
    本地方法栈：主要用于处理/描述本地方法
    堆(Heap)：JVM管理的最大一块内存空间 (new出来的对象都是位于堆上的(实例本身)，
        但是引用(引用也是一个变量，但是引用并不是我们要操作/使用的那个对象，真正的对象会存在堆上，而引用是指向了那个对象)是位于虚拟机栈上的
    方法区(Method Area)：存储元信息(常量、静态变量、类本身所固有的信息<Class对象中固有的信息>、等...)。
        永久代(Permanent Generation),因为涉及到常量池的内容可能出现内存溢出的可能性，从JDK1.8开始，已经彻底废弃永久代，使用元空间代替(meta space)
    运行时常量池：方法区的一部分内容。编译期就可以确定好的
    直接内存：Direct Memory (不是由JVM管理的，由操作系统管理的)
 */
/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-25 21:06
 **/

public class MyTest1 {
}
