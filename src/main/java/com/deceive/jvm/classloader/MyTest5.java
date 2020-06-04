package com.deceive.jvm.classloader;

import java.util.Random;
import java.util.UUID;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/9/29 13:46
 * @Modified By: Cherry
 *
 * 接口的成员变量都默认拥有 public static final xxx 修饰符
 * 重点： -----------》》
 * 当一个接口在初始化时, 并不要求其父接口都完成(被)初始化
 * 只有在真正使用到父接口的时候 (如直接引用父接口中所定义的常量时) 才会初始化
 *        -----------》》
 *
 * 调用运行期常量时：
 *  当调用一个接口中的运行时常量的时候 这个接口的父接口(父类)都需要完成 加载
 *  当调用的不是一个运行时常量时 不止父类不需要都完成加载 接口本身都不需要被加载
 *
 *
 * 运行期产生的
 * Random.nextInt()方法 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
 */

/**
 *
 * 在初始化一个类时，并不会先初始化它所实现的接口
 * 在初始化一个接口时，并不会先初始化它的父接口
 * 验证
 */
public class MyTest5 {

    public static void main(String[] args) {
        //System.out.println(MyChild5.b);
        System.out.println(MyChild5.thread);
    }
}


class MyParent5{

    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5 invoked");
        }
    };

}
class MyChild5 implements MyGrandpa5_1{
    public static final int b = 5;
    /**
     * { xxx } 实例化块，在每一个对象被创建的时候执行，构造方法之前，实例代码块都会被执行一次
     * static { xxx } 静态代码块，类被加载的时候完成初始化阶段，调用的
     */
    {
        System.out.println("实例的模块");
    }
}

interface MyGrandpa5_1{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyGrandpa5_1 invoked");
        }
    };
}

interface MyParent5_1{
    public static Thread thread = new Thread(){
        {
            System.out.println("MyParent5_1 invoked");
        }
    };
}