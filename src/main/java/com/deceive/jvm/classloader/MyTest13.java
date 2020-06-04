package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 17:36
 * @Modified By: Cherry
 *
 * 类加载器的双亲委托机制 验证
 */
public class MyTest13 {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        System.out.println(loader);
        while (null != loader){
            //ClassLoader.getParent(); 获取父加载器
             loader = loader.getParent();
            System.out.println(loader);
        }
    }
}
