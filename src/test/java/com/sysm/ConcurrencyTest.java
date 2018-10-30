package com.sysm;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-11 16:11
 * @Team : 系统集成部
 * @description :
 **/
public class ConcurrencyTest {

    private static int count = 1000000000;

    public static void main(String[] args)throws InterruptedException{
        currency();
        serial();
    }

    private static void currency() throws InterruptedException{
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i=0;i<count;i++){
                    a+=5;
                }
            }
        });
        t.start();
        int b = 0;
        for (int i=0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-startTime;
        t.join();
        System.out.println("currency:"+time+"ms,b="+b);
    }

    private static void serial(){
        long startTime = System.currentTimeMillis();
        int a = 0;
        for (int i=0;i<count;i++){
            a+=5;
        }
        int b = 0;
        for (int i=0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis()-startTime;
        System.out.println("serial:"+time+"ms,a="+a+",b="+b);
    }

}
