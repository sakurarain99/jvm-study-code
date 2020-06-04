package com.deceive.jvm.classloader;

import java.util.Random;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/4 12:08
 * @Modified By: Cherry
 */
public class MyTest8 {
    public static void main(String[] args) {
        System.out.println(FinalTest.x);
    }
}

class FinalTest{

    public static final int x = new Random().nextInt(3);
    static {
        System.out.println("FinalTest static block");
    }

}
