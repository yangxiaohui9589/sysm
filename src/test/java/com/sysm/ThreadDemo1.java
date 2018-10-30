package com.sysm;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-20 14:22
 * @Team : 系统集成部
 * @description :
 **/
public class ThreadDemo1 {
    private int num = 10;

    public void add(){

        System.out.println(Thread.currentThread().getName()+":"+num);
        num ++;
        System.out.println("=="+Thread.currentThread().getName()+":"+num);
    }

    public static void main(String[] args) {
        ThreadDemo1 t = new ThreadDemo1();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.add();
            }
        },"t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.add();
            }
        },"t2");
        t1.start();
        t2.start();
    }
}
