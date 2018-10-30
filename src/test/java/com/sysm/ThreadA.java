package com.sysm;

import java.util.List;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-10 17:13
 * @Team : 系统集成部
 * @description :
 **/
public class ThreadA implements Runnable {

    private List<String> myList=null;
    private String myParam = null;

    public ThreadA(List<String> list, String parmStr){
        this.myList = list;
        this.myParam=parmStr;
    }

    public List<String> addParams(List<String> list,String paramStr){
        list.add(paramStr);
        return list;
    }

    @Override
    public void run() {
        for (int i = 0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"------AAAAA");
            addParams(myList,myParam);
        }
    }
}
