package com.sysm;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-20 13:57
 * @Team : 系统集成部
 * @description :
 **/
public class TicketDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();

        Thread t1 = new Thread(t, "窗口1");
        Thread t2 = new Thread(t, "窗口2");
        Thread t3 = new Thread(t, "窗口3");
        Thread t4 = new Thread(t, "窗口4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
