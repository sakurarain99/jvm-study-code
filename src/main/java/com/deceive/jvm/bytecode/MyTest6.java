package com.deceive.jvm.bytecode;


/*
    方法的动态分派
    方法的动态分派涉及到一个重要的概念：方法接收者(方法调用者)

    invokevirtual字节码指令的多态查找流程
        1.寻找操作数栈顶的第一个元素，所指向的那个对象的实际类型，然后在实际类型中寻找特定的方法
            如果找到了并且权限校验也通过了，那么它就会直接调用这个方法，然后流程结束
            1.2.如果找不到，则按照继承的层次关系，从子类往父类进行依次的重复查找，直到查找到完全匹配的方法，否则抛异常
    比较方法重载(overload)与方法重写(overwrite)，可以得出一个结论：
        方法重载是静态的，是编译期行为；方法重写是动态的，是运行期行为。
 */
/**
 * @program: JVM_lecture
 * @description: Java动态分派   重写
 * @author: Mr.Cherry
 * @create: 2019-11-25 11:09
 **/
public class MyTest6 {
    public static void main(String[] args) {
        Fruit apple = new Apple();
        Fruit orange = new Orange();
        apple.test();
        orange.test();
        apple = new Orange();
        apple.test();
    }
}


class Fruit{
    public void test(){
        System.out.println("Fruit - ");
    }
}

class Apple extends Fruit{
    @Override
    public void test(){
        System.out.println("Apple - ");
    }
}

class Orange extends Fruit{
    @Override
    public void test(){
        System.out.println("Orange - ");
    }
}

