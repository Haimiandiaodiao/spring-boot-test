package _000_day._011_11月18号JDK基础;

import com.google.common.collect.Lists;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 20:41 2018/12/31
 */
public class 测试方法区内存溢出 {

    public static void main(String[] args){
        ArrayList<Object> objects = Lists.newArrayList();
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
            Object o = enhancer.create();
            objects.add(o);
        }
    }
    static class OOMObject{
        private Integer a;
        private Integer a1;
        private Integer a2;
        private Integer a3;
        private Integer a4;
        private Integer a5;
        private Integer a6;
        private Integer a7;
        private Integer a8;
        private Integer a9;
        private Integer a0;
        private Integer a11;
        private Integer a22;
        private Integer a33;
        private Integer a44;
        private Integer a55;
        private Integer a66;
        private Integer a77;
        private Integer a88;
        private Integer a99;
        private Integer a00;
    }
}

