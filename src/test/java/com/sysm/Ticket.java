package com.sysm;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-20 13:55
 * @Team : 系统集成部
 * @description :
 **/
public class Ticket implements Runnable{
    private int tickets = 100;

    // @Override
    // public void run() {
    // // t1,t2,t3,t4
    // while (true) {
    // // t1,t2,t3,t4
    // // tickets = 100
    // // 假设t1抢到了CPU的执行权
    // if (tickets > 0) {
    // // 模拟正常的现象，我这里稍微的等待一下
    // // public static void sleep(long millis)
    // try {
    // Thread.sleep(10); // t1睡眠了,在睡眠的时候，t2抢到了CPU的执行权，t2进来后，也睡眠了。
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    //
    // // t1醒过来，继续执行。
    // System.out.println(Thread.currentThread().getName() + "正在出售第"
    // + (tickets--) + "张票");
    // // 窗口1正在出售第100张票。
    // // tickets = 99
    // // 这仅仅是我们的理想状况，但是实际情况并不是这个样子的。
    // // 实际情况是这样的：每一次线程执行的程序应该是一个原子性的操作。也就是，这个操作，是不能再分割的操作。
    // // tickets--这个动作。
    // // tickets-1 = 99
    // // tickets = 99
    // // 如果你能想到这里至少是两个动作，那么，假设在减1的动作执行后，并没有给tickets重新赋值的时候，
    // // t2醒过来了，这个时候tickets还是100。所以，这个时候，窗口2正在出售第100张票。
    //
    // }
    // }
    // }

    @Override
    public void run() {
        while (true) {
            // tickets = 1;
            // t1,t2,t3,t4都过来了
            // 假设t1抢到了，
            // 假设t2抢到了，
            // 假设t3抢到了，
            // 假设t4抢到了，
            if (tickets > 0) {
                try {
                    // t1睡下了,
                    // t2睡下了,
                    // t3睡下了,
                    // t4睡下了,
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在出售第"
                        + (tickets--) + "张票");
                // t1醒过来了
                // 窗口1正在出售第1张票,tickets = 0;
                // t2醒过来了
                // 窗口2正在出售第0张票,tickets = -1;
                // t3醒过来了
                // 窗口3正在出售第-1张票,tickets = -2;
                // t4醒过来了
                // 窗口4正在出售第-2张票,tickets = -3;
            }
        }
    }
}
