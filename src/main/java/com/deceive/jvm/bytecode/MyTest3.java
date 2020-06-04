package com.deceive.jvm.bytecode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * @program: JVM_lecture
 * @description: ${description}
 * @author: Mr.Cherry
 * @create: 2019-11-24 20:48
 *
 * 对于Java类中的每一个实例方法(非static方法 因为静态方法中无法使用this 也因为静态方法也不属于当前对象 而是属于这个对象对应的class对象)
 * 其在编译后所生成的字节码当中，方法参数的数量总会比源码中方法参数的数量多一个 (this)，它位于方法的第一个参数位置处。这样，我们就可以在Java
 * 的实例方法中使用this来去访问当前对象的属性以及其它方法。
 *
 * 这个操作实在编译期完成的，即由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问。接下来在运行期间，由JVM在调用实例方法时
 * 自动向实例方法传入该this参数。所以，在实例方法的局部变量表中，至少有一个指向当前对象的局部变量this。
 *
 *
 * Java字节码对于异常的处理方式：
 * 1.统一采用异常表的方式来对异常进行处理。
 * 2.在jdk 1.4.2之前的版本中，并不是使用异常表的方式来对异常进行处理的，而是采用特定的指令方式。
 * 3.当异常处理存在finally语句块时，现代化的JVM采用的处理方式是将finally语句块的字节码拼接到每一个catch快后面，
 *    换句话说，程序中存在多少个catch块，就会在每一个catch块后面重复多少个finally语句块的字节码。
 *    (JVM会自动生成一个catch_type为0的异常处理情况，处理Exception无法处理的异常)
 *
 **/
public class MyTest3 {

    public void test(){

        try {
            FileInputStream is = new FileInputStream("Test.txt");

            ServerSocket serverSocket = new ServerSocket(9527);
            serverSocket.accept();

        }catch (FileNotFoundException ex){

        }catch (IOException ex){

        }catch (Exception ex){

        }finally {
            System.out.println("try catch - ");
        }
    }
}
