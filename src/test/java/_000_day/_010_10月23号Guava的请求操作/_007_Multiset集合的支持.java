package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.*;

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

    //1.Multiset把重复元素放入到集合中 可以完成数量统计功能 使用hash和equals 集合中存放重复元素
    //2.count方法也使用了hash和equals方法
    //3.这里的set的指的的是数学中的Set的{a,a,b},{b,a,b}是相等的
    //4。 multiset的实现大多都是 JDK中map的实现
   /**
    * Map	            对应的Multiset	        是否支持null元素
    * HashMap	        HashMultiset	        是
    * TreeMap	        TreeMultiset	        是（如果comparator支持的话）
    * LinkedHashMap	    LinkedHashMultiset      是
    * ConcurrentHashMap	ConcurrentHashMultiset	否
    * ImmutableMap	    ImmutableMultiset	    否
    */


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

        Entity1 entity4 = new Entity1();
        entity4.setId(2);
        entity4.setNum(1);


        set1.add(entity1);
        set1.add(entity2);
        set1.add(entity3);
        set1.add(entity4);

        int count = set1.count(entity4);

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


    @Test
    public void Multiset测试() {
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

        LinkedHashMultiset<String> mset = LinkedHashMultiset.create();
        mset.addAll(words);

        //1.查询a的个数
        int b = mset.count("b");
        System.out.println("b元素的个数："+b);
        //2.查询集合中的元素总个数
        int size = mset.size();
        System.out.println("mset元素的总个数："+size);
        //3.转化为set
        Set<String> strings = mset.elementSet();
        System.out.println("转化为set的元素："+strings);
        //4。转化为entrySet
        Set<Multiset.Entry<String>> entries = mset.entrySet();
        //5。添加指定元素指定个数

        //6。删除指定元素指定个数


    }


    @Test
    public void name() {
        String a = new String("a");
        int i = a.hashCode();
        System.out.println(i);


        String aaaa = new String("aaaa");
        int i1 = aaaa.hashCode();
        System.out.println(i1);

        //String的equals比较没有用到equals
        System.out.println(a.equals(aaaa));
    }


    //3.这里的set的指的的是数学中的Set的{a,a,b},{b,a,b}是相等的
    @Test
    public void 测试mutilsSet的是否数学意义上的相等() {
        LinkedHashMultiset<String> a1 = LinkedHashMultiset.create();
        LinkedHashMultiset<String> a2 = LinkedHashMultiset.create();

        a1.add("a");
        a1.add("b");
        a1.add("b");
        a1.add("b");
        a1.add("c");
        a1.add("c");
        a1.add("c");


        a2.add("a");
        a2.add("b");
        a2.add("e");

        //1。其实就是 a1 - a2的效果  包括数量和种类

        Multiset<String> difference = Multisets.difference(a1, a2);
        System.out.println(difference);

        //2。a2-a1 >=0 数量和种类上
        //网上解释  对任意o，如果a2.count(o)<=a1.count(o)，返回true
//        boolean b = Multisets.containsOccurrences(a2, a1);
//        System.out.println(b);
        
        
        //3。返回的是 有没有从a2中有移除的a1的元素 有返回true 无返回false  有一个也算
        //网上的解释  对toRemove中的重复元素，仅在removeFrom中删除相同个数。
//        boolean b1 = Multisets.removeOccurrences(a1, a2);
//        System.out.println(a1);
//        System.out.println(b1);
        
        //4 修改a1，以保证任意o都符合a1.count(o)<=a2.count(o)
//        boolean b2 = Multisets.retainOccurrences(a1, a2);
//        System.out.println(a1);
//        System.out.println(b2);
        
        //5.返回两个multiset的交集 包含数量的交集
        Multiset<String> intersection = Multisets.intersection(a1, a2);
        System.out.println(intersection);

    }


    /**
     *  ConcurrentHashMultiset  key不可以为空
     */
    @Test
    public void ConcurrentSet和Concumultiset不能指出null的对象() {
        ConcurrentHashMultiset<String> a = ConcurrentHashMultiset.create();
        a.add(null);
        System.out.println();

    }

    /**
     * sortedMultiset的实现
     */
    @Test
    public void 获得一个区间内的排序() {
        TreeMultiset<String> a = TreeMultiset.create();
        a.add("a",2);
        a.add("b",1);
        a.add("c",3);
        a.add("d",5);
        SortedMultiset<String> strings = a.subMultiset("a", BoundType.CLOSED, "c", BoundType.CLOSED);
        
        /**
         *  可以对排序的集合进行截取
         */
        System.out.println(strings);

    }
}
