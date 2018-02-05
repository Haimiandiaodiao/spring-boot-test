package com.dyy.Modul.SystemPojo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @auther Dyy
 * @create 2018/1/23
 */
public class FileClassLoader extends ClassLoader {
    private String rootDir;

    public FileClassLoader(String rootDir){
        this.rootDir = rootDir;
    }


    /**
     * 类文件的完全路径
     * 方法:classNameToPath</br>
     * 描述:</br>
     * 作者:Dyy</br>
     * 时间:2018/1/23 16:12</br>
     * @param className
     */
    private String classNameToPath(String className){
        return rootDir + File.separatorChar + className.replace('.',File.separatorChar)+".class";
    }


    /**
     *
     * 方法:getClassData</br>
     * 描述:</br>
     * 作者:Dyy</br>
     * 时间:2018/1/23 16:13</br>
     * @param className
     */
    private byte[] getClassData(String className){
        //读取类文件的字节
        String path = classNameToPath(className);
        try{
            FileInputStream ins = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 4096;
            int bytesNumRead = 0;
            byte[] buffer = new byte[bufferSize];

            while ((bytesNumRead = ins.read(buffer)) != -1){
                baos.write(buffer,0,bytesNumRead);
            }

            return baos.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 编写findClass方法的逻辑
     * 方法:findClass</br>
     * 描述:</br>
     * 作者:Dyy</br>
     * 时间:2018/1/23 16:08</br>
     * @param name
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //获取类的class文件字节数组
        byte[] classData = getClassData(name);
        if(classData == null){
            throw new ClassNotFoundException();
        }else{
            //直接生成class对象
            return defineClass(name,classData,0,classData.length);
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
        String rootDir = "E:\\mars\\springFast\\target\\classes";//"D:\\classes";

        /*FileClassLoader loader = new FileClassLoader(rootDir);
        try{
            Class<?> object1 = loader.loadClass("com.dyy.Modul.DemoObj");
            System.out.println(object1.newInstance().toString());
        }catch (Exception e){
            e.printStackTrace();
        }*/

        FileClassLoader loader1 = new FileClassLoader(rootDir);
        FileClassLoader loader2 = new FileClassLoader(rootDir);
        Class<?> aClass = loader1.findClass("com.dyy.Modul.DemoObj");
        Class<?> aClass1 = loader2.findClass("com.dyy.Modul.DemoObj");

        System.out.println("findClass->"+aClass.hashCode());
        System.out.println("findClass->"+ aClass1.hashCode());

    }

}
