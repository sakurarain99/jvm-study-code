package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/9/29 14:21
 * @Modified By: Cherry
 */
public class MyTest6 {

    public static void main(String[] args) {
        //对Singleton类的主动使用  类会被初始化
        Singleton instance = Singleton.getInstance();

        System.out.println(Singleton.counter1);
        System.out.println(Singleton.counter2);
    }

}

class  Singleton{
    public static  int counter1;

    private static Singleton  singleton = new Singleton();

    private Singleton() {
        counter1++;
        //准备阶段的重要意义 在未给变量赋初始值之前可以使用，使用准备阶段被赋予了数据类型的默认值
        counter2++;

        System.out.println("构造方法："+counter1 +"   "+counter2);
    }
    public static int counter2 = 0;         //初始化阶段重新赋值 把构造方法中提前更改的值覆盖掉了

    public static Singleton getInstance(){
        return singleton;
    }

}

/**
 * 2.结果 1 0                                                              准备阶段        初始化阶段
 * class  Singleton{
 *     public static  int counter1;                                         0                   0
 *
 *     private static Singleton  singleton = new Singleton();              null          new Singleton()
 *
 *     private Singleton() {                                               不被调用     此时 counter1/counter2  都为1
 *         counter1++;                                                                      counter1++的是以及初始化之后的值 而counter2还未初始化
 *         counter2++;
 *
 *         System.out.println("构造方法："+counter1 +"  "+counter2);                         1 1
 *     }
 *     public static int counter2 = 0;                                      0             把0赋给counter2 把之前的值覆盖掉了 所以初始化之后  counter2为 0
 *
 *     public static Singleton getInstance(){                   getInstance()被调用则是对类的
 *         return singleton;                                        主动使用则会进行初始化
 *     }
 *
 * }
 */

//初始化阶段重新赋值 把构造方法中提前更改的值覆盖掉了

/**
 * 1.结果都是 1                                                 准备阶段        初始化阶段
 *  class  Singleton{
 *     public static  int counter1;                                0                 0
 *     public static int counter2 = 0;                             0                 0
 *
 *     private static Singleton  singleton = new Singleton();     null        new Singleton()   Singleton被实例化
 *
 *     private Singleton() {
 *          counter1++;                                        不会调用         调用  counter1/counter2都加1
 *          counter2++;
 *     }
 *
 *     public static Singleton getInstance(){             getInstance()被调用      singleton返回
 *         return singleton;
 *     }
 * }
 */
