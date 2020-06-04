package com.deceive.jvm.classloader;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/5 18:14
 * @Modified By: Cherry
 */
public class MyCat {

    public MyCat(){
        System.out.println("MyCat is loaded by >> " + this.getClass().getClassLoader());

       // System.out.println("from MyCat >> " + MySample.class);
    }

}
