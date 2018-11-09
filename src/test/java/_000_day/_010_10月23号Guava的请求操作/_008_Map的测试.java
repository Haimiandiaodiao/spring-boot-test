package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import net.bytebuddy.implementation.bytecode.StackSize;
import org.junit.Test;

import java.util.*;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:15 2018/11/7
 */
public class _008_Map的测试 {

    /**
     * IdentityHashMap  比较的是地址  key和value 都可以为null
     * HasMap 比较的是hashCode 如果 hashCode相同则使用equals进行比较  可以存放 key 和 value 都可为null
     * Hashtable 比较的是hashCode 如果 hashCode相同则使用equals进行比较  不可以存放key和value都不可为null  是线程安全的
     * TreeMap 不能存放compable比较相同的元素 和 hashcode和equals都无关  不可存key不可以为null value可以为null
     * LinkedHashtable 继承Hashtable的规则
     *
     * */
    /*set底层是由Map来实现的，key存放的是set具体的值，value是放的固定的值1，使用了hashcode和equal来判断是够相等*/
    @Test
    public void baseMapTest(){
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(2);

        Entity1 entity3 = new Entity1();
        entity3.setId(1);
        entity3.setNum(1);

        System.out.println(entity1==entity3);// == 使用的是比较的是对象的地址和hashCode和equals都木有关系
        System.out.println(entity1.equals(entity2));//  ==用的是equal方法

        HashMap<Object, Object> map = Maps.newHashMap();
        map.put(entity1,"1");
        map.put(entity1,"2");
        map.put(entity2,"3");//注意使用map的put的时候使用的使用的是hashCode方法 key的比较是hashCode  和equals无关
        map.put(entity3,"4");
        map.put(null,"5");
        map.put(null,"6");

        System.out.println(map);
    }


    /**
     * 对IdentityHashMap的测试
     * @Author:Dyy
     * @Description:
     * @Date: Created in 10:34 2018/11/7
     * @param
     */
    @Test
    public void identityHashMapTest(){
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(1);
        entity3.setNum(1);

        IdentityHashMap map = Maps.newIdentityHashMap();
        map.put(entity1,"1");
        map.put(entity1,"2");//IdentityHashMap比较key时使用的是 == 号也就是 比较的是地址
        map.put(entity3,"3");
        map.put(entity2,"4");//注意使用map的put的时候使用的使用的是hashCode方法 key的比较是hashCode  和equals无关
        map.put(null,"100");
        map.put(null,"200");


        System.out.println(map);
    }

    /**
     * setadd是使用了什么方法
     * <br/>
     * @author Dyy <br/>
     * @description <br/>
     * @date 2018/11/8 11:58 PM <br/>
     * @param  <br/>
     */
    @Test
    public void setTest() {
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(1);
        entity3.setNum(1);

        Set<Entity1> aaa = new HashSet<>();
        aaa.add(entity1);
        aaa.add(entity2);
        aaa.add(entity3);
        aaa.add(null);
        aaa.add(null);

        System.out.println();


    }

    @Test
    public void HashTable的测试(){
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(2);
        entity3.setNum(1);

        Hashtable map = new Hashtable();
        map.put(entity1,"1");
        map.put(entity1,"2");//IdentityHashMap比较key时使用的是 == 号也就是 比较的是地址
        map.put(entity3,"3");
        map.put(entity2,"4");//注意使用map的put的时候使用的使用的是hashCode方法 key的比较是hashCode 然后使用eqauls进行比较
        map.put(null,"100");
        map.put(null,"200");


    }

    /**
     * treeMap继承了sortedMap
     * @Author:Dyy
     * @Description:
     * @Date: Created in 12:03 2018/11/8
     * @param
     */
    @Test
    public void TreeMapOrSortMap的使用(){
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(2);
        entity3.setNum(1);

        TreeMap map = new TreeMap<>();

        map.put(entity1,"1");
        map.put(entity1,"2");
        map.put(entity3,"3");
        map.put(entity2,"4");
        map.put(null,"100");
        map.put(null,"200");

        System.out.println("");
    }

    @Test
    public void 空值测试(){
        TreeMap a = new TreeMap();
        //a.put(null,1);//no
        //a.put("1",null);//ok
        IdentityHashMap b = new IdentityHashMap();
//        b.put(null,1);//ok
//        b.put(1,null);//ok
//        b.put(null,2);//覆盖之前

        Hashtable c = new Hashtable();
        c.put(null,1);//no
//        c.put(1,null);//no

        System.out.println("");
    }



}
