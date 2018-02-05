package com.dyy.Modul.SystemPojo;

import java.io.File;
import java.net.*;

/**
 * @auther Dyy
 * @create 2018/1/23
 */
public class FileUrlClassLoader extends URLClassLoader{

    //需要写一下构造函数

    public FileUrlClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public FileUrlClassLoader(URL[] urls) {
        super(urls);
    }

    public FileUrlClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    static{
        System.out.println("ddddd");
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String rootDir="E:\\mars\\springFast\\target\\classes";
        //创建自定义文件类加载器
        File file = new File(rootDir);
        URI uri = file.toURI();
        URL[] urls = {uri.toURL()};

        FileUrlClassLoader loader = new FileUrlClassLoader(urls);

        Class<?> object1 = loader.loadClass("com.dyy.Modul.DemoObj");
        System.out.println(object1.newInstance().toString());

    }

}
