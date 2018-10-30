
package com.sysm;

import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-10 17:14
 * @Team : 系统集成部
 * @description :
 **/
public class ThreadB implements Runnable {

    private List<String> myList=null;
    private String myParam = null;

    public ThreadB(List<String> list, String parmStr){
        this.myList = list;
        this.myParam=parmStr;
    }
    public void pullOut(){
        synchronized (""){

        }
        if(myList.size()==5){
            System.out.println("------------------------------线程B啊。。。size=5");
            throw new RuntimeException();
        }
    }
    @Override
    public void run() {
        System.out.println("bb");
        while(true){
            pullOut();
        }
    }
}
