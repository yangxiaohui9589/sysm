package com.sysm;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-11 17:31
 * @Team : 系统集成部
 * @description :
 **/
public class ThreadDeadLock {
    private final String A = "AAAAA";
    private final String B = "BBBBB";

    public static void main(String[] args) {
        ThreadDeadLock td = new ThreadDeadLock();
        td.testDeadLock();
    }
    public  void testDeadLock(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A){
                    try {
                        Thread.currentThread().sleep(50000);
                        throw new RuntimeException();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println("t--------------------------");
                    }
                }
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B){
                    synchronized (A){
                        System.out.println("t1================");
                    }
                }
            }
        });
        t.start();
        t1.start();
    }
}
