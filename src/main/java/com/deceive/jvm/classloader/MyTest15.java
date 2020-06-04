package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 18:50
 * @Modified By: Cherry
 * 数组的类对象不是由类加载器创建的，而是根据Java运行时动态创建的。
 * 调用数组的 Class.getClassLoader() 返回的类加载器是与数组中元素的类加载器相同。
 * 如果元素类型是原始类型(基本数据类型/包装类)，则数组类没有类加载器。
 *
 * 类加载器都会有一个security managers 安全管理器
 * defineClass 会将一个字节数组转换为一个class实例，这个新定义的类的实例可以通过Class.newTnstance创建
 *
 * 由类加载器创建的对象的，方法或构造方法还可能会引用其它的类，这种情况JVM就会调用loadClass方法创建
 */
public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("---------------------");
        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());

        System.out.println("---------------------");
        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());
    }
}
