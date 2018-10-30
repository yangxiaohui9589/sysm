package com.sysm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-10 17:16
 * @Team : 系统集成部
 * @description :
 **/
public class ThreadTest2 {
    private volatile  static List<String> list = new ArrayList<String>();

    public void add(){
        list.add("yangxh");
    }
    public int getSize(){
        return list.size();
    }


    public static void main(String args[]){

        final ThreadTest test = new ThreadTest();
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                    for (int i=0;i<10;i++){
                        test.add();
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(test.getSize()==5){
                            System.out.println("已经发出通知..");
                            countDownLatch.countDown();
                        }
                    }
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                    if(list.size()!=5){
                        System.out.println("当前线程：" + Thread.currentThread().getName() + "进入了..");
                    }
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("收到通知了。。。开始工作！");
                }
        });
        t1.start();
        t.start();
    }
}
