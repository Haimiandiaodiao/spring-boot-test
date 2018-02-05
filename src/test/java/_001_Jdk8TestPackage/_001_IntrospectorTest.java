package _001_Jdk8TestPackage;

import com.dyy.Modul.Entity.Son;
import org.junit.Test;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 6.1 内省的测试
 * @auther Dyy
 * @create 2018/1/25
 */
public class _001_IntrospectorTest {

    /**
     * 1.通过PropertyDescription修改属性
     */
    @Test
    public void baseUser1() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        String name1= "WD";
        String name = "Dyy";
        Integer age = 11;
        List<String> hobbies= new ArrayList<>();
        hobbies.add("篮球");
        hobbies.add("足球");
        hobbies.add("排球");

        Son son = new Son(name, age, hobbies,null);
        //====================关键代码================
        PropertyDescriptor nameInt = new PropertyDescriptor("name", Son.class);

        Method writeMethod = nameInt.getWriteMethod();
        Method readMethod = nameInt.getReadMethod();
        //=============================================
        Class  a = Son.class;

        Object invoke = readMethod.invoke(son);
        System.out.println("内省到对象的name->"+invoke);
        Object invoke1 = writeMethod.invoke(son, name1);
        System.out.println("内省设置完name之后的结果->"+invoke1);
        Object invoke3 = readMethod.invoke(son);
        System.out.println("内省重新调用的结果->"+invoke3);
    }


    /**
     * 2.通过Introspector修改属性
     */
    @Test
    public void baseUser2() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Son.class);

        String name1= "WD";
        String name = "Dyy";
        Integer age = 11;
        List<String> hobbies= new ArrayList<>();
        hobbies.add("篮球");
        hobbies.add("足球");
        hobbies.add("排球");

        Son son = new Son(name, age, hobbies,null);

        //===========================1.得到所有的属性======================
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor tmp : propertyDescriptors) {
                String nameV = tmp.getName();//属性的名字
                Class<?> propertyType = tmp.getPropertyType();//属性的类型
                Method readMethod = tmp.getReadMethod();//get方法
                Method writeMethod = tmp.getWriteMethod();//set方法
                Object value = readMethod.invoke(son);
                System.out.println("属性的名字-->" + nameV);
                System.out.println("属性的类型-->" + propertyType);
                System.out.println("属性的值-->" + value);
                System.out.println("=======================================");
            }
        }
        long l1 = System.currentTimeMillis();

        System.out.println("得到属性所需的时间是-->"+(l1-l));

        //=============================2.得到所有的方法============================
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor tmp : methodDescriptors) {
            String name2 = tmp.getName();//方法的名字
            ParameterDescriptor[] parameterDescriptors = tmp.getParameterDescriptors();
            Method method = tmp.getMethod();
            System.out.println("方法的名字-->"+name2);
            System.out.println("方法的描述-->"+parameterDescriptors);
            System.out.println("方法-->"+method);
            System.out.println("=========================================");
        }


    }
}
