package _000_day;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import jdk.nashorn.internal.runtime.regexp.joni.EncodingHelper;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;
import sun.misc.Unsafe;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 18:36 2018/12/24
 */
public class testBean {

    @Test
    public void teste1(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 1, 1, 2, 3, 4);
        ArrayList<Integer> integers1 = Lists.newArrayList(1,2);
        boolean b = integers.remove((Object)1);
        System.out.println(integers);
    }

    @Test
    public void test(){
        ArrayList<String> list = Lists.newArrayList();
        int i =0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    @Test
    public void 测试字符串(){
        String s = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s.intern() == s);


        String s1 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1.intern() == s1);

        String s11 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s11.intern() == s);
    }

    @Test
    public void 测试出现方法区内存溢出(){
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }

    public static class OOMObject{}



    @Test
    public void 测试直接内存溢出异常() throws IllegalAccessException {
        Field fi = Unsafe.class.getDeclaredFields()[0];
        fi.setAccessible(true);
        Unsafe o = (Unsafe)fi.get(null);
        while (true){
            o.allocateMemory(1024*1024);
        }
    }


    @Test
    public void 字符串拼接null字段(){
        String a = null;
        String b = "bb";
        String s = a + b;
        System.out.println(s);

        String s1 = joinValue("a","b",null,"B");
        System.out.println(s1);
    }

    public static String joinValue(String... value){
        int length = value.length;
        StringBuffer sb = new StringBuffer(length);
        for (String s : value) {
            if(s != null && !"".equals(s)) {
                sb.append(s);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        return sb.toString();
    }


    @Test
    public void testDD(){
        Integer integer = new Integer(1000);
        Integer integer1 = new Integer(1);
        System.out.println(integer == 1000);
    }
}




