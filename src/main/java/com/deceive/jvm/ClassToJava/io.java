package com.deceive.jvm.ClassToJava;

import java.io.*;

/**
 * @author ：cherry
 * @description： ${description}
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/11/1 15:31
 */
public class io {


    private static String hexString = "0123456789ABCDEF";

    public static void main(String[] args)throws Exception {
         //C:\Users\Cherry blossoms\Desktop\MyTest1.class
        StringBuffer stringBuffer = ClassToJava("C:\\Users\\Cherry blossoms\\Desktop\\MyTest1.class", "C:\\Users\\Cherry blossoms\\Desktop\\MyTest1.java");
        System.out.println(stringBuffer);
    }


    public static StringBuffer ClassToJava(String inputPath, String outputPath)throws Exception
    {
        StringBuffer stringBuffer = new StringBuffer();
        //读取Class文件要用字节输入流
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inputPath));
        //输出转换后的文件要用字符输出流
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(outputPath));
        int readSize = 16;
        byte[] buffer_read = new byte[readSize];
        String line;
        int i = 0;
        while ((readSize = bis.read(buffer_read,0,readSize))!= -1)
        {
            line = encode(buffer_read,readSize);
            stringBuffer.append(line);
            bw.write(line);
            bw.newLine();
            i++;
        }
        bis.close();
        bw.close();

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
            sbr.append("  ");
        }
        return sbr.toString();
    }

}
