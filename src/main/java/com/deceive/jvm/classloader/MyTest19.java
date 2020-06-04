package com.deceive.jvm.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 * @Author: Cherry
 * @Description: 扩展类加载器
 * @Date: 2019/10/6 16:40
 * @Modified By: Cherry
 */
public class MyTest19 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
    }
}
