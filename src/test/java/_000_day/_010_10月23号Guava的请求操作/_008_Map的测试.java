package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 10:15 2018/11/7
 */
public class _008_Map的测试 {

    @Test
    public void baseMapTest(){
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);

        Entity1 entity3 = new Entity1();
        entity3.setId(1);
        entity3.setNum(1);

        System.out.println(entity1==entity3);// == 使用的是比较的是对象的地址和hashCode和equals都木有关系
        System.out.println(entity1.equals(entity2));//  ==用的是equal方法

        HashMap<Object, Object> map = Maps.newHashMap();
        map.put(entity1,"1");
        map.put(entity1,"2");
        map.put(entity2,"2");//注意使用map的put的时候使用的使用的是hashCode方法 key的比较是hashCode  和equals无关
        map.put(null,"4");
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
        map.put(entity3,"2");
        map.put(entity2,"2");//注意使用map的put的时候使用的使用的是hashCode方法 key的比较是hashCode  和equals无关
        map.put(null,"100");
        map.put(null,"200");


        System.out.println(map);
    }





}
