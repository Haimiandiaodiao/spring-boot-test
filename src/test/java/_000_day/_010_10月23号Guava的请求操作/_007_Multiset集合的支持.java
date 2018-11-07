package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import org.apache.tools.ant.util.LinkedHashtable;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:24 2018/11/6
 */
public class _007_Multiset集合的支持 {

    //1.Multiset把重复元素放入到集合中 可以完成数量统计功能
    @Test
    public void baseUse1(){
        List<String> words = new LinkedList<>();
        words.add("a");
        words.add("a");
        words.add("b");
        words.add("c");
        words.add("b");
        words.add("d");
        words.add("c");
        words.add("e");
        words.add("a");

        HashMultiset<String> multiSet = HashMultiset.create();
        boolean b = multiSet.addAll(words);
        //1.获得该元素在列表中出现的次数  如果元素不存在会返回0而不是抛出异常
        int a = multiSet.count("a");
        int dddd = multiSet.count("dddd");

        //2.返回set集合 数据是不不重复的
        Set<String> strings = multiSet.elementSet();

        //3.删除集合中的指定元素的个数  不能为负数 异常    大于在集合中的大小的话就会删除集合中现存的
        int a1 = multiSet.remove("a", 1);

        System.out.println("");

    }


    @Test
    public void 一些静态方法的支持(){
        //0.对List的支持

        //1. == new ArrayList(size);
        //按照指定大小生成集合
        ArrayList<String> Strings1 = Lists.newArrayListWithCapacity(2);
        //2. == new ArrayList(size*1.5);
        //按照指定大小的期望扩充值扩充
        ArrayList<Object> objects = Lists.newArrayListWithExpectedSize(2);
        //3. 新建一个写时复制的集合
        CopyOnWriteArrayList<Object> copy = Lists.newCopyOnWriteArrayList();
        //4.新建一个链表集合
        LinkedList<Object> objects1 = Lists.newLinkedList();
        //5.不能直接生成线程安全的队列要使用Collections工具类的方法
//        Collections.synchronizedList()

        //6.线程安全的set
        Set<Object> objects2 = Sets.newConcurrentHashSet();

        //7.对set的支持
        TreeSet<Comparable> setTree = Sets.newTreeSet();
        HashSet<Object> objects3 = Sets.newHashSet();
        LinkedHashSet<Object> objects4 = Sets.newLinkedHashSet();
        CopyOnWriteArraySet<Object> objects5 = Sets.newCopyOnWriteArraySet();


        //8.对Map的支持
        ConcurrentMap<Object, Object> map1 = Maps.newConcurrentMap();
        HashMap<Object, Object> map2 = Maps.newHashMap();
        IdentityHashMap<Object, Object> map3 = Maps.newIdentityHashMap();//重复的Map
        LinkedHashMap<Object, Object> map4 = Maps.newLinkedHashMap();
        TreeMap<Comparable, Object> map5 = Maps.newTreeMap();

        //9.Multiset可重复的Set存放的是key和这个元素的个数
        HashMultiset<Entity1> set1 = HashMultiset.create();
        LinkedHashMultiset<Object> set2 = LinkedHashMultiset.create();
        HashMultimap<Object, Object> muMap1 = HashMultimap.create();
        LinkedHashMultimap<Object, Object> muMap2 = LinkedHashMultimap.create();

        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(2);
        Entity1 entity3 = new Entity1();
        entity3.setId(5);
        entity3.setNum(1);

        set1.add(entity1);
        set1.add(entity2);
        set1.add(entity3);



        System.out.println("");
    }


    @Test
    public void Map的测试(){
        HashMap<String, Integer> map1 = Maps.newHashMap();
        map1.put("a",1);
        map1.put("a",2);
        map1.put("a",3);
        map1.put("a",1);

        System.out.println(map1); //1.Key相同表示key存在


    }
}
