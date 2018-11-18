package _000_day._010_10月23号Guava的请求操作;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 12:38 PM<br/>
 */
public class _014_RangeSet区间集合 {

    /**
     *
     * RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略。例如：
     *
     * 查看源代码打印帮助
     * RangeSet<Integer> rangeSet = TreeRangeSet.create();
     * rangeSet.add(Range.closed(1, 10)); // {[1,10]}
     * rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
     * rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
     * rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
     * rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}
     *
     *
     */


    /**
     * 视图方法
     *
     * complement()：返回RangeSet的补集视图。complement也是RangeSet类型,包含了不相连的、非空的区间。
     * subRangeSet(Range<C>)：返回RangeSet与给定Range的交集视图。这扩展了传统排序集合中的headSet、subSet和tailSet操作。
     * asRanges()：用Set<Range<C>>表现RangeSet，这样可以遍历其中的Range。
     * asSet(DiscreteDomain<C>)（仅ImmutableRangeSet支持）：用ImmutableSortedSet<C>表现RangeSet，以区间中所有元素的形式而不是区间本身的形式查看。（这个操作不支持DiscreteDomain 和RangeSet都没有上边界，或都没有下边界的情况）
     *
     */


    /**
     * 支持的查询方法
     * contains(C)：RangeSet最基本的操作，判断RangeSet中是否有任何区间包含给定元素。
     * rangeContaining(C)：返回包含给定元素的区间；若没有这样的区间，则返回null。
     * encloses(Range<C>)：简单明了，判断RangeSet中是否有任何区间包括给定区间。
     * span()：返回包括RangeSet中所有区间的最小区间。
     *
     *
     *
     */

}
