package com.deceive.jvm.classloader;

public class MyTest25 implements Runnable{

    private Thread thread;

    public MyTest25(){
        thread = new Thread(this);
        thread.run();
    }


    public void run() {
        ClassLoader loader = this.thread.getContextClassLoader();
        this.thread.setContextClassLoader(loader);

        System.out.println("Class："+loader.getClass());
        System.out.println("Parent："+loader.getParent().getClass());
    }

    public static void main(String[] args) {
        /*
            为什么默认的上下类加载器是系统类加载器
                答案参考 sun.misc.Launcher类 36/52/57 行
         */
        new MyTest25();
    }
}
