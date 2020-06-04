package com.deceive.jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: JVM_lecture
 * @description: 动态代理类
 * @author: Mr.Cherry
 * @create: 2019-11-25 19:23
 **/
public class DynamicSubject implements InvocationHandler {

    private Object sub;

    public DynamicSubject(Object sub){
        this.sub = sub;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling： " + method);

        method.invoke(this.sub,args);

        System.out.println("after calling： " + method);

        return null;
    }
}
