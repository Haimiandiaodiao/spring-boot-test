package _000_day._010_10月23号Guava的请求操作;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 12:42 PM<br/>
 */
public class _015_RangeMap的区间集合映射 {
    
    /**
     * RangeMap描述了”不相交的、非空的区间”到特定值的映射。和RangeSet不同，RangeMap不会合并相邻的映射，即便相邻的区间映射到相同的值。例如：
     * 查看源代码打印帮助
     * RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
     * rangeMap.put(Range.closed(1, 10), "foo"); //{[1,10] => "foo"}
     * rangeMap.put(Range.open(3, 6), "bar"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
     * rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
     * rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
     *
     */


    /**
     *
     * 排序的集合的视图映射
     * asMapOfRanges()：用Map<Range<K>, V>表现RangeMap。这可以用来遍历RangeMap。
     * subRangeMap(Range<K>)：用RangeMap类型返回RangeMap与给定Range的交集视图。这扩展了传统的headMap、subMap和tailMap操作。
     *
     */
}
