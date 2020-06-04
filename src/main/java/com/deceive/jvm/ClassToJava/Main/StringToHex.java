package com.deceive.jvm.ClassToJava.Main;

import com.deceive.jvm.ClassToJava.utils.ConvertHexToASCLL;

/**
 * @author ：cherry
 * @description： ${description}
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/11/3 14:40
 */
public class StringToHex{
    public static void main(String[] args) {

        String hex = "57656C636F6D65";
        System.out.println("\n***** 16进制转换为ASCII *****");
        System.out.println("Hex : " + hex);
        System.out.println("ASCII : " + ConvertHexToASCLL.convertHexToString(hex));
    }
}
