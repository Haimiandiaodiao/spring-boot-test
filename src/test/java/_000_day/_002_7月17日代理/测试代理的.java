package _000_day._002_7月17日代理;

import net.sf.cglib.beans.*;
import net.sf.cglib.proxy.*;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午3:51<br/>
 */
public class 测试代理的 {
    @Test
    public void 方法1使用装饰类来进行代理增强() {
        doMethodClass doMethodClass = new doMethodClass();
        targer targer = new targer(doMethodClass);
        targer.show1();
        targer.show2();
        targer.show3();
    }

    @Test
    public void 方法2使用JDK动态代理() {
        doMethodClass doMethodClass = new doMethodClass();
        Class<?> clazz = doMethodClass.getClass();
        needMethodInteface o = (needMethodInteface)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代理类是——-————>" + proxy.getClass());//输出的代理对象的话有可能会出现死循环
                System.out.println("我是动态代理的开始");
                Object invoke = method.invoke(doMethodClass, args);
                System.out.println("我是动态代理的结束");
                return invoke;
            }
        });
        String s = o.show1();
        System.out.println(s);
        o.show2();

        boolean proxyClass = Proxy.isProxyClass(o.getClass());
        System.out.println(proxyClass);
    }


    /**
     * 1.enhancer
     * 2.methodInterceptor
     */
    @Test
    public void 使用cglib代理简单的类() {
        CglibSampleEntity a = new CglibSampleEntity();
        Enhancer enhancer = new Enhancer();                 //增强器
        enhancer.setSuperclass(a.getClass());//设置父类
        enhancer.setCallback(new MethodInterceptor() {      //设置方法拦截器
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("使用cglib设置的前置赠强======>start");
                Object invoke =  methodProxy.invokeSuper(o, objects);   //使用代理方法进行增强
                System.out.println("使用cglib设置的前置赠强======>end");
                return invoke;
            }
        });

        CglibSampleEntity o = (CglibSampleEntity)enhancer.create();
        System.out.println(o.getClass());//查看增强的实际实体类
        o.show1();

        String s = o.show2();
        System.out.println(s);


    }

    @Test
    public void 使用cglib返回固定的值() {
        CglibSampleEntity a = new CglibSampleEntity();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(a.getClass());
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "我就是代理返回的固定值";
            }
        });
        CglibSampleEntity aa = (CglibSampleEntity) enhancer.create();
        String s = aa.show2();
        System.out.println(s);

        Integer aaa = aa.show3();
        Class<? extends CglibSampleEntity> aClass = aa.getClass();
        //System.out.println(aaa);//报错因为无论什么返回的都是固定的代理返回值  带有返回值的都会调用那个返回固定值的
    }


    @Test
    public void 创建不可修改的类() {
        SampleBean a = new SampleBean();
        a.setValue("aaaa");
        System.out.println(a.getValue());
        a.setValue("bbbb");
        System.out.println(a.getValue());

        SampleBean sampleBean = (SampleBean) ImmutableBean.create(a);//创建不可以修改的类
        System.out.println(sampleBean.getClass());
        a=null;
        System.out.println(sampleBean.getValue());

        sampleBean.setValue("11111");
        System.out.println(sampleBean instanceof SampleBean);
    }

    @Test
    public void 运行期间动态的创建Bean() throws Exception {
        BeanGenerator beanGenerator = new BeanGenerator();
        beanGenerator.addProperty("name",String.class);
        beanGenerator.addProperty("age",Integer.class);
        Object o = beanGenerator.create();
        Class<?> aClass = o.getClass();
        System.out.println("动态的创建的Bean的Class"+aClass);
        Method setName = aClass.getMethod("setName", String.class);
        Method setAge = aClass.getMethod("setAge", Integer.class);
        Method getName = aClass.getMethod("getName", null);
        Method getAge = aClass.getMethod("getAge", null);
        setName.invoke(o,"Dyy");
        setAge.invoke(o,26);
        System.out.println(o.toString());

        Object invoke = getName.invoke(o, null);
        Object invoke1 = getAge.invoke(o, null);
        System.out.println(invoke+"===>"+invoke1);

    }


    @Test
    public void cglib对Bean进行copy() {
        BeanCopier beanCopier = BeanCopier.create(SampleBean2.class, SampleBean.class, false);
        SampleBean2 dyy = new SampleBean2("Dyy", 2);
        SampleBean sampleBean = new SampleBean();
        beanCopier.copy(dyy,sampleBean,null);
        System.out.println(dyy);
        System.out.println(sampleBean);
    }

    /**
     * 1.避免每次进行BulkBean.create创建对象，一般将其声明为static的
     * 2.针对特定属性的get,set操作，一般适用通过xml配置注入和注出的属性，运行时才确定处理的Source,Target类，只需要关注属性名即可。
     * 3.必须要给所有设置的get set的方法同时设置值
     */
    @Test
    public void 分离get和set的方法的复制() {
        BulkBean bulkBean = BulkBean.create(SampleBean2.class, new String[]{"getValue", "getAge"}, new String[]{"setValue", "setAge"}, new Class[]{String.class, Integer.class});
        SampleBean2 sampleBean2 = new SampleBean2();
        bulkBean.setPropertyValues(sampleBean2,new Object[]{null,null});
        System.out.println(sampleBean2);
    }


    @Test
    public void 将Bean转换为Map的操作() {
        SampleBean2 sampleBean2 = new SampleBean2("Dyy",11);
        BeanMap beanMap = BeanMap.create(sampleBean2);

        beanMap.put("value","WZZ");
        beanMap.put("age",22);

        Object value = beanMap.get("value");
        Object age = beanMap.get("age");

        System.out.println(value);
        System.out.println(age);
        System.out.println(sampleBean2);
    }

    @Test
    public void 将多个对象整合到一对象中() {
        MixinTest mixinTest = new MixinTest();
        MixinTest.Class1 class1 = mixinTest.new Class1();
        MixinTest.Class2 class2 = mixinTest.new Class2();

        Mixin mixin = Mixin.create(new Class[]{MixinTest.Interface1.class, MixinTest.Interface2.class, MixinTest.TotalInterface3.class}
                , new Object[]{class1, class2});
        MixinTest.TotalInterface3 mixin1 = (MixinTest.TotalInterface3) mixin;
        String first = mixin1.first();
        String second = mixin1.second();
        System.out.println(first+"===>"+second);


        Class<? extends Mixin> aClass = mixin.getClass();
        System.out.println(aClass);
        //得到所有的接口
        System.out.println("实现的所有接口===========>");
        Class<?>[] interfaces = aClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface.getName());
        }
        System.out.println("父类=======》");
        Class<?> superclass = aClass.getSuperclass();
        System.out.println(superclass);
    }
}



class targer implements needMethodInteface{
    private needMethodInteface entity;

    public targer(needMethodInteface entity) {
        this.entity = entity;
    }

    @Override
    public String show1() {
        System.out.println("我是增强show1的代理类start");
        return entity.show1();
    }

    @Override
    public void show2() {
        System.out.println("我是增强show2的代理类start");
        entity.show2();
    }

    @Override
    public Integer show3() {
        System.out.println("我是增强show3的代理类start");
        return entity.show3();
    }
}