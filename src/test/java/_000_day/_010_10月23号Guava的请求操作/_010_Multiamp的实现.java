package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multiset;
import org.apache.commons.collections.MultiMap;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 11:44 AM<br/>
 */
public class _010_Multiamp的实现 {

    /**
     *  场景解释
     *  每个有经验的Java程序员都在某处实现过Map<K, List<V>>或Map<K, Set<V>>，并且要忍受这个结构的笨拙。
     *  例如，Map<K, Set<V>>通常用来表示非标定有向图。Guava的 Multimap可以很容易地把一个键映射到多个值。
     *  换句话说，Multimap是把键映射到任意多个值的一般方式。
     *
     *  可以用两种方式思考Multimap的概念：”键-单个值映射”的集合：
     *
     *  a -> 1 a -> 2 a ->4 b -> 3 c -> 5
     *
     *  或者”键-值集合映射”的映射：
     *
     *  a -> [1, 2, 4] b -> 3 c -> 5
     *
     *  一般来说，Multimap接口应该用第一种方式看待，但asMap()视图返回Map<K, Collection<V>>，让你可以按另一种方式看待Multimap。重要的是，不会有任何键映射到空集合：一个键要么至少到一个值，要么根本就不在Multimap中。
     *  很少会直接使用Multimap接口，更多时候你会用ListMultimap或SetMultimap接口，它们分别把键映射到List或Set
     *  可以一对多的集合
     *
     */



    /**
     * 支持的基础的方法
     *方法签名	                        描述	等价于
     * put(K, V)	                    添加键到单个值的映射	                                multimap.get(key).add(value)
     * putAll(K, Iterable<V>)	        依次添加键到多个值的映射	                            Iterables.addAll(multimap.get(key), values)
     * remove(K, V)	                    移除键到值的映射；如果有这样的键值并成功移除，返回true。	multimap.get(key).remove(value)
     * removeAll(K)	                    清除键对应的所有值，返回的集合包含所有之前映射到K的值，
     *                                  但修改这个集合就不会影响Multimap了。	                multimap.get(key).clear()
     * replaceValues(K, Iterable<V>)	清除键对应的所有值，并重新把key关联到Iterable中的每个元素。
     *                                  返回的集合包含所有之前映射到K的值。	                    multimap.get(key).clear();
     *                                                                                      Iterables.addAll(multimap.get(key), values)
     *
     *
     *
     */

    /**
     * 支持的强大的视图操作
     *  asMap     为Multimap<K, V>提供Map<K,Collection<V>>形式的视图
     *  entries   用Collection<Map.Entry<K, V>>返回Multimap中所有”键-单个值映射”——包括重复键
     *  keySet    用Set表示Multimap中所有不同的键。
     *  keys      用Multiset表示Multimap中的所有键，每个键重复出现的次数等于它映射的值的个数。可以从这个Multiset中移除元素，但不能做添加操作
     *  values    用一个”扁平”的Collection<V>包含Multimap中的所有值。这有一点类似于Iterables.concat(multimap.asMap().values())
     *
     *
     */


    /**
     * 支持的多种方法的实现
     *  实现	                        键行为类似	    值行为类似
     * ArrayListMultimap	        HashMap	        ArrayList
     * HashMultimap	                HashMap	        HashSet
     * LinkedListMultimap*	        LinkedHashMap*	LinkedList*
     * LinkedHashMultimap**	        LinkedHashMap	LinkedHashMap
     * TreeMultimap	                TreeMap	        TreeSet
     * ImmutableListMultimap	    ImmutableMap	ImmutableList
     * ImmutableSetMultimap	        ImmutableMap	ImmutableSet
     *
     *
     * 除了两个不可变形式的实现，其他所有实现都支持null键和null值
     *
     *
     */

    @Test
    public void 基础使用() {

        ArrayListMultimap<String, String> a = ArrayListMultimap.create();
        a.put("hobbis","A");
        a.put("hobbis","B");
        a.put("hobbis","C");
        a.put("hobbis","D");

        Map<String, Collection<String>> aa = a.asMap();
        
        System.out.println(aa.get("hobbis"));


        ArrayListMultimap<String, String> b = ArrayListMultimap.create();
        b.put("hobbis","A");
        b.put("hobbis","B");
        b.put("hobbis","C");
        b.put("hobbis","D");
        b.put("age","D");

        Map<String, Collection<String>> bb = b.asMap();

        Collection<String> hobbis = bb.get("hobbis");
        System.out.println(hobbis);
        
        Collection<Map.Entry<String, String>> entries = b.entries();
        System.out.println(entries);

        Set<String> strings = b.keySet();
        
        System.out.println(strings);

        Multiset<String> keys = b.keys();
        //返回的不支持添加操作
        keys.add("dd");
        System.out.println(keys);


        Collection<String> values = b.values();
        System.out.println(values);
    }
}
