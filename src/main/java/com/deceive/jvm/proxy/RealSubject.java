package com.deceive.jvm.proxy;

/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-25 19:22
 **/
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("From real subject   - ");
    }
}
