package com.sysm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-07-17 13:30
 * @Team : 系统集成部
 * @description :
 **/
public class TestThreadMXBean {
    public static void main(String[] args) {
        ThreadMXBean tmxb = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = tmxb.dumpAllThreads(false,false);
        for(ThreadInfo ti:threadInfos){
            System.out.println("id="+ti.getThreadId()+" >< name="+ti.getThreadName());
        }


    }
}
