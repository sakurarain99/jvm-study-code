package com.deceive.jvm.bytecode;

/**
 * @author ：cherry
 * @description： ${description}
 * @modified By：
 * @version: $version$
 * @date ：Created in 2019/11/2 15:29
 */
public class MyTest2 {
    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;



    private static final int k = 1;
    private static int s;
    protected static final String sk ="Hello";



    public MyTest2(){
        try {
        }catch (Exception e){

        }
    }

    public MyTest2(Integer s,String k,int l)throws Exception{

        System.out.println("JVM - ");
    }

    public static void main(String[] args){
        //MyTest2 myTest2 = new MyTest2(1,"s",1);
        MyTest2 myTest2 = new MyTest2();

        myTest2.setX(8);
        in = 20;



        for(int i=0;i < 5;i++){
            if(i > 4){
                System.out.println("yes");
            }else {
                System.out.println("no");
            }
        }
        switch (s){
            case 1:
                break;
            default:
                break;
        }

        String s1 = s >2? "是" : "no";

        while (s > 1){
            System.out.println("是的 while");
        }

        do {
            System.out.println("是的 do while");
        }while (s>1);

        try{
            System.out.println("try catch");

        }catch (RuntimeException e){
            e.printStackTrace();
        }


        new test(){
            @Override
            public void hello() {
                System.out.println("匿名内部类 - ");
            }
        };



        throw new RuntimeException("异常");

    }

    private void test(String str){
        synchronized (str){
            System.out.println("synchronized - ");
        }
    }


    public void setX(int x) {
        this.x = x;
    }
}
