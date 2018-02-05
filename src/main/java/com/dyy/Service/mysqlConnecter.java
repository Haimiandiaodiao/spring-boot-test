package com.dyy.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @auther Dyy
 * @create 2018/1/23
 */
public class mysqlConnecter {

    public static void main(String[] args) throws SQLException, IOException {
     /*   String property = System.getProperty("jdbc.drivers");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        loader = (loader == null) ? ClassLoader.getSystemClassLoader() : loader;
        String fullName = "META-INF/services/"+"java.sql.Driver";

        Enumeration<URL> source = loader.getSystemResources(fullName);
        while (source.hasMoreElements()){
            URL url = source.nextElement();
            System.out.println(url.toString());
        }*/

        String url = "jdbc:coe://118.190.2.253:3306/mars_prod?useUnicode=true&autoReconnect=true&rewriteBatchedStatements=TRUE";
        DriverManager.setLogWriter(new PrintWriter(System.out));
        Connection mars = DriverManager.getConnection(url, "mars", "mars");
        System.out.println(mars);
    }
}
