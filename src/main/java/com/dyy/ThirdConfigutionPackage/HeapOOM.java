package com.dyy.ThirdConfigutionPackage;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/12/26 11:50 PM<br/>
 */
public class HeapOOM {

    public static void main(String[] args){
        ArrayList<Object> objects = Lists.newArrayList();
        while (true){
            objects.add(new Integer(1000));
        }


    }
}
