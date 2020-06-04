package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/5 18:16
 * @Modified By: Cherry
 */
public class MySample {
    public MySample(){
        System.out.println("MySample is loaded by >> " + this.getClass().getClassLoader() );

        new MyCat();

        System.out.println("from MySample >> " + MyCat.class);
    }
}
