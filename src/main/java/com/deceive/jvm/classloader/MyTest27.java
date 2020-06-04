package com.deceive.jvm.classloader;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author ：cherry
 * @description： ${description}
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/10/30 11:09
 */
public class MyTest27 {
    public static void main(String[] args)throws Exception {

        System.out.println(System.getProperty("jdbc.drivers"));
        Class.forName("com.mysql.cj.jdbc.Driver");
        Thread.currentThread().setContextClassLoader(MyTest27.class.getClassLoader().getParent());
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","root");
    }
}
