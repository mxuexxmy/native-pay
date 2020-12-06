package com.example.pay.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author mxuexxmy
 * @ClassName GetInputStream
 * @Description TODO
 * @Date 11/19/2020 4:41 PM
 * @Version 1.0
 **/
public class GetInputStream {

    public static void main(String[] args) {
        System.out.println(loadFromOsFileSystemOrClasspathAsStream("cert/apiclient_cert.p12"));
    }

    public static InputStream loadFromOsFileSystemOrClasspathAsStream(String filePath) {
        InputStream in = null;
        try {
            // 优先从文件系统路径加载
            if (new File(filePath).exists()) {
                in = new FileInputStream(filePath);
                //System.out.println("loadFrom...file path done");
            }
            // 从类路径加载
            else {
                in = classLoader().getResourceAsStream(filePath);
                //System.out.println("loadFrom...class path done");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return in;
    }

    public static ClassLoader classLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        return loader;
    }

}
