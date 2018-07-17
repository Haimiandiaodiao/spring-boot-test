package _000_day._001_7月17日反射;

import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 上午10:14<br/>
 */
public class ReflectTest {

    @Test
    public void 得到构造方法() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class<?> clazz = Class.forName("_000_day._001_7月17日反射.Student");

        System.out.println("**********************公有构造方法*********************************");
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> tmp : constructors) {
            System.out.println(tmp);
        }
        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> tmp : declaredConstructors) {
            System.out.println(tmp);
        }
        System.out.println("*****************获取公有、无参的构造方法*******************************");
//        Constructor<?> constructor = clazz.getConstructor(null);
//        System.out.println(constructor);
        System.out.println("******************获取私有构造方法，并调用*******************************");
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor(int.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance(10);
        System.out.println(o);
        
        
        System.out.println("*********************正常调用****************************************");
        Student a = new Student('a');
        System.out.println(a);

    }


    @Test
    public void 获得所有的属性() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Class<?> clazz = Class.forName("_000_day._001_7月17日反射.Student");
        System.out.println("************获取所有公有的字段********************");
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Student student = new Student();
        Field name = clazz.getField("name");
        name.set(student,"Dyy");
        System.out.println(student.toString());

        System.out.println("**************获取私有字段****并调用********************************");
        Field phoneNum = clazz.getDeclaredField("phoneNum");
        phoneNum.setAccessible(true);
        phoneNum.set(student,"110");
        System.out.println(student.toString());

    }

    @Test
    public void 获得所有的方法() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?> clazz = Class.forName("_000_day._001_7月17日反射.Student");
        System.out.println("***************获取所有的”公有“方法*******************");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }


        System.out.println("***************获取所有的方法，包括私有的*******************");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }

        System.out.println("***************获取公有的show1()方法*******************");
        Object o = clazz.newInstance();
        Method show1 = clazz.getDeclaredMethod("show1", String.class);
        Object invoke = show1.invoke(o, "6666");
        System.out.println("***************获取私有的show4()方法******************");

    }


    @Test
    public void 反射main方法() throws Exception {
        Class<?> clazz = Class.forName("_000_day._001_7月17日反射.Student");

        //2、获取main方法
        Method methodMain = clazz.getMethod("main", String[].class);//第一个参数：方法名称，第二个参数：方法形参的类型，
        //3、调用main方法
        // methodMain.invoke(null, new String[]{"a","b","c"});
        //第一个参数，对象类型，因为方法是static静态的，所以为null可以，第二个参数是String数组，这里要注意在jdk1.4时是数组，jdk1.5之后是可变参数
        //这里拆的时候将  new String[]{"a","b","c"} 拆成3个对象。。。所以需要将它强转。
        methodMain.invoke(null, (Object)new String[]{"a","b","c"});//方式一
        // methodMain.invoke(null, new Object[]{new String[]{"a","b","c"}});//方式二
    }


    @Test
    public void 通过反射加载属性文件() throws Exception {
        Properties properties = new Properties();
        FileReader in =new FileReader("src/test/java/_000_day/_001_7月17日反射/pro.txt");
        properties.load(in);
        in.close();

        Object className = properties.get("className");
        Object methodName = properties.get("methodName");
        System.out.println(className);
        System.out.println(methodName);

    }


    @Test
    public void 获得父类() throws Exception {
        Class<?> clazz = Class.forName("_000_day._001_7月17日反射.Student");
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        //获得父类
        Class<?> superclass = clazz.getSuperclass();
        System.out.println(superclass);
        //获得接口
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println(interfaces[0]);
    }


    @Test
    public void 获得父类范型() {
        Class clazz1 = new ArrayList<Integer>().getClass();
        Class clazz2 = new String[0].getClass();
        Class clazz3 = String.class;
        Class clazz4 = Object.class;
        Class clazz5 = new IntList().getClass();

        System.out.println(clazz1.getGenericSuperclass());
        System.out.println(clazz2.getGenericSuperclass());
        System.out.println(clazz3.getGenericSuperclass());
        System.out.println(clazz4.getGenericSuperclass());
        System.out.println(clazz5.getGenericSuperclass());
        
        System.out.println("===========================================");
        Class clazz11 = new ArrayList<Integer>().getClass();
        Type type1 = clazz11.getGenericSuperclass();
        ParameterizedType type11 = (ParameterizedType) type1;
        Type[] actualTypeArguments = type11.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println(actualTypeArgument.getTypeName());
        }

        Class<IntList> intListClass = IntList.class;
        Type type22 = intListClass.getGenericSuperclass();
        ParameterizedType type221 = (ParameterizedType) type22;
        Type[] actualTypeArguments1 = type221.getActualTypeArguments();
        System.out.println(type221);
        for (Type type : actualTypeArguments1) {
            System.out.println(type.getTypeName());
        }

        //获得自身的范型类型

    }


    @Test
    public void 使用范型进行抽取() {
        Son1 son1 = new Son1();
        Son2 son2 = new Son2();
        System.out.println(son1.doGeneric());
        System.out.println(son2.doGeneric());
    }
}
