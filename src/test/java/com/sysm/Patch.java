package com.sysm;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.fileupload.util.Streams;

public class Patch {
    public static String patchtype = "INT";
    public static String versionname = "12.V5";
    public static String ymainpath = "D:/workspace";
    public static String mmainpath = "E:/patch";
    public static String propath = "/Test/com.test/path.properties";

    public static void main(String[] args) throws Exception {
        //System.out.println(getPatchName());
        File file = new File(mmainpath);
        if (!file.exists()) {
            file.mkdirs();
        }
        List<String> targetlist = analyse();
        for (int i = 0; i < targetlist.size(); i++) {
            String targetpath = targetlist.get(i);
            try {
                String cleanpath = cleanPath(targetpath);
                copy(cleanpath, targetpath);
            } catch (Exception e) {
                System.out.println("patch FAIL" + mmainpath + targetpath);
            }
        }
        System.out.println("patch done");
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
                System.out.println("error");
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
                System.out.println("error");
            }
            dfile.setLastModified(sfile.lastModified());
            System.out.println("patch done" + mmainpath + targetfilepath);
        } else {
            System.out.println("file not exist");
        }
    }
}
