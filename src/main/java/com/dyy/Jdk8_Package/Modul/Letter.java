package com.dyy.Jdk8_Package.Modul;

/**
 * @auther Dyy
 * @create 2018/2/2
 */

public class Letter {

    public static String addHeader(String  text){
        return "From Raoul, Mario and Alan : "+text;
    }

    public static String addFooter(String text){
        return text + " Kind regards";
    }

    public static String checkSpelling(String text){
        return text.replaceAll("labda","lambda");
    }

    public static void testDyy(String text){
        System.out.println("Dyy");
    }
}
