package com.tendy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtil {

    /**
     * 删除文件或文件目录
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 获取文件格式
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        String exName = "";
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                exName =  filename.substring(dot);
            }
        }
        return exName;
    }

    /**
     * 拷贝文件 默认保存路径在java.io.tmpdir
     * @param file 原文件
     * @param fileName 拷贝文件名
     * @return
     * @throws IOException
     */
    public static File copyFile(File file, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        File copyFile = new File(System.getProperty("java.io.tmpdir"), fileName);
        FileOutputStream fos = new FileOutputStream(copyFile);

        byte[] bts = new byte[2048];
        int len = 0;
        while((len = fis.read(bts)) != -1){
            fos.write(bts,0,len);
        }
        fis.close();
        fos.close();

        return copyFile;
    }

    /**
     * 拷贝文件
     * @param file 原文件
     * @param fileName 拷贝文件完整路径
     * @return
     * @throws IOException
     */
    public static File copyFileAbsolute(File file, String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(file);

        File copyFile = new File(fileName);
        FileOutputStream fos = new FileOutputStream(copyFile);

        byte[] bts = new byte[2048];
        int len = 0;
        while((len = fis.read(bts)) != -1){
            fos.write(bts,0,len);
        }
        fis.close();
        fos.close();
        return copyFile;
    }
}
