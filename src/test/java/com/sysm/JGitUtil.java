package com.sysm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author : yangxh
 * @create 2018/10/30 8:55
 * @description : Git操作工具类
 **/
public class JGitUtil {

    private static String LOCAL_REPO_PATH ;
    private static String LOCAL_REPOGIT_CONFIG;
    private static String REMOTE_REPO_URI;
    private static String INIT_LOCAL_CODE_DIR;
    private static String LOCAL_CODE_CT_SQL_DIR;
    private static String BRANCH_NAME ;
    private static String GIT_USERNAME;
    private static String GIT_PASSWORD ;
    final static Logger LOG = LoggerFactory.getLogger(JGitUtil.class);

    static{
        LOCAL_REPO_PATH = "D:/test";
        REMOTE_REPO_URI = "https://github.com/yangxiaohui9589/db.git";
        GIT_USERNAME = "yangxiaohui9589";
        GIT_PASSWORD = "yxh6789503";
    }

    public static void setupRepo() throws GitAPIException{

    }
    public static void main(String[] args) throws GitAPIException{
        File file = new File("D:/testdb");
        boolean flag = false;
        if(file.exists()){
            flag = deleteFolder("D:/testdb");
        }
        if(flag){
            //建立与远程仓库的联系，仅需要执行一次
            Git git = Git.cloneRepository().setURI(REMOTE_REPO_URI).setDirectory(file).call();
            LogCommand d = git.log();
            Iterable it = d.call();
            while (it.iterator().hasNext()){
                System.out.println(it.iterator().next());
            }

            List list = git.diff().call().subList(0,1);
            System.out.println(list.size());
        }
    }
    public static boolean deleteFolder(String url) {
        File file = new File(url);
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            file.delete();
            return true;
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String root = files[i].getAbsolutePath();//得到子文件或文件夹的绝对路径
                //System.out.println(root);
                deleteFolder(root);
            }
            file.delete();
            return true;
        }
    }
}