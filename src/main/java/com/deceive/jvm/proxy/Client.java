package com.deceive.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-25 19:25
 **/
public class Client {
   /* public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        RealSubject rs = new RealSubject();
        InvocationHandler ds = new DynamicSubject(rs);
        Class<?> aClass = rs.getClass();

        Subject subject = (Subject)
                Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),ds);

        subject.request();
    }*/

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.num();
    }
}
