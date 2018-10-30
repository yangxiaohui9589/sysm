package com.sysm;

import org.apache.commons.fileupload.util.Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : yangxh
 * @version : 1.0
 * @create 2018-10-29 16:44
 * @Team : 系统集成部
 * @description :
 **/
public class Patch2 {
    public static String patchtype = "INT";//自定义补丁名字
    public static String versionname = "12月份常规版本V5";//自定义补丁名字
    public static String ymainpath = "D:/workspace";//源文件夹路径
    public static String mmainpath = "E:/patch";//目标文件夹路径
    public static String propath = "/Test/com.test/path.properties";//需要存放打补丁路径的配置文件，这里可以添加自己修改过的文件路径

    public static void main(String[] args) throws Exception {
        //System.out.println(getPatchName());
        File file = new File(mmainpath);//如果目标文件夹不存在就生成一个，例如不小心删除的
        if (!file.exists()) {
            file.mkdirs();
        }
        List<String> targetlist = analyse();//分析配置文件循环获取路径
        for (int i = 0; i < targetlist.size(); i++) {
            String targetpath = targetlist.get(i);
            try {
                String cleanpath = cleanPath(targetpath);//根据配置文件路径设置目标具体路径
                copy(cleanpath, targetpath);//将源路径里面的文件复制到目标具体路径
            } catch (Exception e) {
                System.out.println("patch失败：" + mmainpath + targetpath);
            }
        }
        System.out.println("patch完毕！");
    }

    public static String getPatchName() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String time = sdf.format(d);
        return patchtype + "_" + time + "_" + versionname;
    }

    public static List<String> analyse() throws Exception {
        List<String> list = new ArrayList<String>();
        Properties prop = new Properties();
        InputStream in = new FileInputStream(ymainpath + propath);
        prop.load(in);
        Set<Object> set = prop.keySet();
        for (Object obj : set) {
            String path = obj.toString();
            String[] arr = path.split("/");
            if (!"".equals(arr[0])) {
                System.out.println("存在数据路径配置问题，请检查！");
                return list;
            }
            String newpath = "";
            if ("TPXD_CORE".equals(arr[1]) && "WebContent".equals(arr[2])) {
                newpath = "/P2P_Core";
                for (int i = 3; i < arr.length; i++) {
                    newpath = newpath + "/" + arr[i];
                }
            } else if ("TPXD_CORE".equals(arr[1]) && "src".equals(arr[2])) {
                newpath = "/P2P_Core/WEB-INF/classes";
                for (int i = 3; i < arr.length; i++) {
                    newpath = newpath + "/" + arr[i];
                }
                newpath = newpath.replace(".java", ".class");
            } else if ("TPGCI_CORE".equals(arr[1]) && "src".equals(arr[2])) {
                newpath = "/TPGCI_CORE/bin";
                for (int i = 3; i < arr.length; i++) {
                    newpath = newpath + "/" + arr[i];
                }
                newpath = newpath.replace(".java", ".class");
            }
            list.add(newpath);
        }
        return list;
    }

    public static String cleanPath(String targetpath) {
        String sopath = "";
        if (targetpath.indexOf("P2P_Core") != -1 && targetpath.indexOf(".jsp") != -1) {
            sopath = targetpath.replace("/P2P_Core/", "/TPXD_CORE/WebContent/");
        } else if (targetpath.indexOf("P2P_Core") != -1 && targetpath.indexOf(".class") != -1) {
            sopath = targetpath.replace("/P2P_Core/WEB-INF/", "/TPXD_CORE/build/");
        } else {
            sopath = targetpath;
        }
        return sopath;
    }

    public static void copy(String sofilepath, String targetfilepath) throws Exception {
        File sfile = new File(ymainpath + sofilepath);
        File dir = new File(mmainpath + targetfilepath.substring(0, targetfilepath.lastIndexOf("/")));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File dfile = new File(dir, "" + targetfilepath.substring(targetfilepath.lastIndexOf("/"), targetfilepath.length()));
        if (sfile.exists()) {
            FileInputStream fis = new FileInputStream(sfile);
            FileOutputStream fos = new FileOutputStream(dfile);
            try {
                Streams.copy(fis, fos, true);
            } catch (Exception e) {
                System.out.println("复制异常");
            }
            dfile.setLastModified(sfile.lastModified());//保留原文件的修改时间
            System.out.println("patch成功：" + mmainpath + targetfilepath);
        } else {
            System.out.println("文件不存在！");
        }
    }
}
