package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.Collections2;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 12:19 PM<br/>
 */
public class _012_Table多值索引的使用 {

    /**
     * 背景的介绍
     *
     *  通常来说，当你想使用多个键做索引的时候，你可能会用类似Map<FirstName, Map<LastName, Person>>的实现，
     * 这种方式很丑陋，使用上也不友好。Guava为此提供了新集合类型Table，它有两个支持所有类型的键：”行”和”列”。Table提供多种视图，以便你从各种角度使用它
     *
     * Table<Vertex, Vertex, Double> weightedGraph = HashBasedTable.create();
     * weightedGraph.put(v1, v2, 4);
     * weightedGraph.put(v1, v3, 20);
     * weightedGraph.put(v2, v3, 5);
     *
     * weightedGraph.row(v1); // returns a Map mapping v2 to 4, v3 to 20
     * weightedGraph.column(v3); // returns a Map mapping v1 to 20, v2 to 5
     *
     */


    /**
     * 基本的使用方法
     * rowMap()：用Map<R, Map<C, V>>表现Table<R, C, V>。同样的， rowKeySet()返回”行”的集合Set<R>。
     * row(r) ：用Map<C, V>返回给定”行”的所有列，对这个map进行的写操作也将写入Table中。
     * 类似的列访问方法：columnMap()、columnKeySet()、column(c)。（基于列的访问会比基于的行访问稍微低效点）
     * cellSet()：用元素类型为Table.Cell<R, C, V>的Set表现Table<R, C, V>。Cell类似于Map.Entry，但它是用行和列两个键区分的
     */


    /**
     * 具体的实现：
     * HashBasedTable：本质上用HashMap<R, HashMap<C, V>>实现；
     * TreeBasedTable：本质上用TreeMap<R, TreeMap<C,V>>实现；
     * ImmutableTable：本质上用ImmutableMap<R, ImmutableMap<C, V>>实现；注：ImmutableTable对稀疏或密集的数据集都有优化。
     * ArrayTable：要求在构造时就指定行和列的大小，本质上由一个二维数组实现，以提升访问速度和密集Table的内存利用率。ArrayTable与其他Table的工作原理有点不同，请参见Javadoc了解详情
     *
     *
     *
     *
     */

    @Test
    public void Table的基础使用() {

        HashBasedTable<Integer, Integer, Object> a = HashBasedTable.create();
        a.put(1,1,"a");
        a.put(1,2,"b");
        a.put(1,3,"c");
        a.put(2,1,"d");
        a.put(2,2,"e");
        a.put(2,3,"f");
        a.put(3,1,"g");
        a.put(3,2,"h");
        a.put(3,3,"i");

        //1.行索引
        Map<Integer, Object> row = a.row(1);
        System.out.println(row);
        
        //2.返回行列的索引  {1={1=a, 2=b, 3=c}, 2={1=d, 2=e, 3=f}, 3={1=g, 2=h, 3=i}}
        Map<Integer, Map<Integer, Object>> integerMapMap = a.rowMap();
        System.out.println(integerMapMap);
        
        //  [(1,1)=a, (1,2)=b, (1,3)=c, (2,1)=d, (2,2)=e, (2,3)=f, (3,1)=g, (3,2)=h, (3,3)=i]
        Set<Table.Cell<Integer, Integer, Object>> cells = a.cellSet();
        System.out.println(cells);

    }
}
