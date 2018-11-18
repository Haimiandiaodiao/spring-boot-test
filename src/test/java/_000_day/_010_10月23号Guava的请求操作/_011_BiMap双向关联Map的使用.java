package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.HashBiMap;
import org.junit.Test;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 12:08 PM<br/>
 */
public class _011_BiMap双向关联Map的使用 {


    /**
     * 背景介绍和应用场景
     * 传统上，实现键值对的双向映射需要维护两个单独的map，并保持它们间的同步。但这种方式很容易出错，而且对于值已经在map中的情况，会变得非常混乱。例如：
     *
     * Map<String, Integer> nameToId = Maps.newHashMap();
     * Map<Integer, String> idToName = Maps.newHashMap();
     * nameToId.put("Bob", 42);
     * idToName.put(42, "Bob");
     * //如果"Bob"和42已经在map中了，会发生什么?
     * //如果我们忘了同步两个map，会有诡异的bug发生...
     *
     */


    /**
     *
     * 键–值实现	        值–键实现	        对应的BiMap实现
     * HashMap	        HashMap	        HashBiMap
     * ImmutableMap	    ImmutableMap	ImmutableBiMap
     * EnumMap	        EnumMap	        EnumBiMap
     * EnumMap	        HashMap	        EnumHashBiMap
     *
     *
     */

    /**
     *  常用的方法
     *  可以用 inverse()反转BiMap<K, V>的键值映射
     *   保证值是唯一的，因此 values()返回Set而不是普通的Collection
     *
     */

    //不能完成多对以的反向映射
    @Test
    public void 基础的使用() {

        HashBiMap<String, String> a = HashBiMap.create();

        a.put("1","Dyy");
        a.put("1","Dyy1");//会覆盖之前的值
        a.put("2","Dyy2");
        a.put("3","Dyy3");

        String s = a.get("1");
        System.out.println(s);
    }
}
