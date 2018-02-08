package _002_ThirdProjectTestPackage;

import com.dyy.Modul.Entity.Father;
import com.dyy.Modul.Entity.Son;
import org.apache.commons.beanutils.BeanPropertyValueEqualsPredicate;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * BeanUtil的使用
 * @auther Dyy
 * @create 2018/1/25
 */
public class _003_BeanUnitTest {

    /**
     * 1.将Map封装成Bean
     */
    @Test
    public void baseUse1() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Map<String, Object> faMap = new HashMap();
        Map<String, Object> soMap = new HashMap();

        List<String> soH = new ArrayList<>();
        soH.add("篮球");
        soH.add("足球");
        List<String> foH = new ArrayList<>();
        foH.add("飞机");
        foH.add("坦克");


        soMap.put("name", "小名");
        soMap.put("age", 1);
        soMap.put("hobbies", soH);

        faMap.put("name", "大名");
        faMap.put("age", 50);
        faMap.put("hobbies", foH);
        //faMap.put("son",soMap);
        //=============================核心===========
        Father result = new Father();
        // BeanUtils.populate(result,faMap);

        long l = System.currentTimeMillis();
        Son son = new Son();
        org.apache.commons.beanutils.BeanUtils.populate(son, soMap);
        result.setSon(son);
        org.apache.commons.beanutils.BeanUtils.populate(result, faMap);

        long l1 = System.currentTimeMillis();
        System.out.println("所用的时间--->" + (l1 - l));

        Object son1 = PropertyUtils.getSimpleProperty(result, "son");
        System.out.println(son1);

        System.out.println(result.toString());
    }

    /**
     * 设置属性的值
     */
    @Test
    public void baseUse2() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Son son = new Son();
        Father f = new Father();
        f.setSon(son);
        f.setAge(48);
        f.setName("666");

        son.setName("西欧阿米哥");
        son.setAge(1);
        Map<String, Object> addr = new HashMap();
        addr.put("school", "天津");
        addr.put("home", "北京");
        son.setAddr(addr);
        List<String> strings = Arrays.asList("A", "B", "C");
        son.setHobbies(strings);
        /*PropertyUtils.setIndexedProperty(son,"hobbies",1,"G");
        Object name = PropertyUtils.getIndexedProperty(son, "hobbies",0);
        Object mappedProperty = PropertyUtils.getMappedProperty(son, "addr(home)");

        System.out.println(mappedProperty);

        System.out.println(name);*/

//        Object nestedProperty = PropertyUtils.getNestedProperty(f, "son.addr(school)");
//        Object property = PropertyUtils.getProperty(f, "son.addr(school)");
//        System.out.println(property);

//        Map<String, String> describe = BeanUtils.describe(f);
//        String name = describe.get("name");
//
//        System.out.println(name);

//        DynaBean dynaBean = new LazyDynaBean();
//        dynaBean.set("name","666");
//        dynaBean.set("age",100);
//        dynaBean.set("shool","牛");
//        dynaBean.set("address",1,"A");
//        dynaBean.set("address",2,"B");


        List<Son> listSons = new ArrayList<>();
        Son a = new Son("A", 1, null, null);
        Son b = new Son("A", 2, null, null);
        Son c = new Son("A", 3, null, null);

        listSons.add(a);
        listSons.add(b);
        listSons.add(c);

//        BeanPropertyValueChangeClosure age = new BeanPropertyValueChangeClosure("age", 100);
//        CollectionUtils.forAllDo(listSons,age);
//        System.out.println(listSons);

        BeanPropertyValueEqualsPredicate age = new BeanPropertyValueEqualsPredicate("age", 2);
        CollectionUtils.filter(listSons,age);
        System.out.println(listSons);

    }
}
