package com.deceive.jvm.ClassToJava.utils;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;

/**
 * @author ：cherry
 * @description： ${description}
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/11/3 14:57
 */
public class ClassToHex {

    private static String hexString = "0123456789ABCDEF";

    public static StringBuffer classToHex(String inputPath)throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();
        //读取Class文件要用字节输入流
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputPath));
        int readSize = 16;
        byte[] buffer_read = new byte[readSize];
        String line;
        int i = 0;
        while ((readSize = bis.read(buffer_read,0,readSize))!= -1)
        {
            line = encode(buffer_read,readSize);
            stringBuffer.append(line);
            i++;
        }
        bis.close();
        return stringBuffer;

    }
    public static String encode(byte[] buffer,int length)
    {
        StringBuilder sbr = new StringBuilder();
        //将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<length;i++)
        {
            sbr.append(hexString.charAt((buffer[i]&0xf0)>>4));
            sbr.append(hexString.charAt(buffer[i]&0x0f));
            //sbr.append("  ");
        }
        return sbr.toString();
    }
}
