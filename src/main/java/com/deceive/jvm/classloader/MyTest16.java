package com.deceive.jvm.classloader;

import java.io.*;

/**
 * @Author: Cherry
 * @Description:
 * @Date: 2019/10/5 14:51
 * @Modified By: Cherry
 *
 * 自定义类加载器 双亲委托机制
 * -XX:+TraceClassUnloading  跟踪类的卸载
 *
 * jvisualvm  命令打开java监控软件
 */
public class MyTest16 extends ClassLoader {

    private String path;
    private String classLoaderName = (this.getClass()).toString();
    private final String fileExtension = ".class";

    /**
     * ClassLoader的构造方法作用就是用于创建一个新的类加载器，并以系统类加载器作为委托双亲
     * Creates a new class loader using the ClassLoader returned by the method getSystemClassLoader() as the parent class loader.If there is a security manager, its checkCreateClassLoader method is invoked. This may result in a security exception.
     */

    public MyTest16(String classLoaderName){
        //将系统类加载器当做该类加载器的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 把你传过来的ClassLoader 作为双亲委托机
     */
    public MyTest16(ClassLoader parent,String classLoaderName){
        //显示指定该类加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }
    //修改MyTest16为系统类加载器 所需要的构造方法
    public MyTest16(ClassLoader parent){
        super(parent);
    }



    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked：" + className);
        System.out.println("class loader name：" + this.classLoaderName);

        byte[] data = this.loadClassData(className);
        //defineClass(类名,数据,从哪开始读取,读取长度)
        return this.defineClass(className,data,0,data.length);
    }

    private byte[] loadClassData(String className){
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos= null;
        className = className.replace(".","/");
        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch;
            while(-1 != (ch = is.read())){
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "[ " + this.classLoaderName + " ] ";
    }

    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        Class<?> clazz = loader1.loadClass("com.deceive.jvm.classloader.MyTest");
        System.out.println("hashCode1："+clazz.hashCode());
        Object object = clazz.newInstance();
        System.out.println(object+" >> "+object.getClass().getClassLoader());

        System.out.println("---------------------------");

        loader1 = null;
        clazz = null;
        object = null;
        //执行一次垃圾回收
        //System.gc();
        System.out.println("---------------------------");
        Thread.sleep(10000);

        loader1 = new MyTest16("loader1");
        loader1.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        clazz = loader1.loadClass("com.deceive.jvm.classloader.MyTest");
        System.out.println("hashCode1："+clazz.hashCode());
        object = clazz.newInstance();
        System.out.println(object+" >> "+object.getClass().getClassLoader());

/*
        System.out.println("---------------------------");

        MyTest16 loader2 = new MyTest16(loader1,"loader2");
        loader2.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        Class<?> clazz2 = loader2.loadClass("com.deceive.jvm.classloader.MyTest");
        System.out.println("hashCode2："+clazz2.hashCode());
        Object object2 = clazz2.newInstance();
        System.out.println(object2+" >> "+object2.getClass().getClassLoader());

        System.out.println("---------------------------");

        MyTest16 loader3 = new MyTest16(loader2,"loader3");
        loader3.setPath("C:\\Users\\Cherry blossoms\\Desktop\\");
        Class<?> clazz3 = loader3.loadClass("com.deceive.jvm.classloader.MyTest");
        System.out.println("hashCode3："+clazz3.hashCode());
        Object object3 = clazz3.newInstance();
        System.out.println(object3+" >> "+object3.getClass().getClassLoader());
*/

    }




}
