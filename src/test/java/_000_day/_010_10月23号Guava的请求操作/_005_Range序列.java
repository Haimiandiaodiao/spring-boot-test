package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:56 2018/10/26
 */
public class _005_Range序列 {

    /**
     *   linuxprobe官方网站 Range 表示一个间隔或一个序列
     * */
    @Test
    public void 序列的使用(){
        //1.得到一个序列
        Range<Integer> all = Range.all();
        boolean contains = all.contains(1);
        System.out.println(contains);
        //2.判断一个对象是否存在于序列中
        Range<Comparable<?>> all1 = Range.all();
        boolean contains1 = all1.contains(2.22);
        System.out.println(contains1);



        //3.atLeast返回》=10的点   atMost返回《= 10的点
        Range<Integer> integerRange = Range.atLeast(10);
        System.out.println(integerRange.contains(5));
        System.out.println(integerRange.contains(10));
        //false
        //true

        //3.返回一个权威的区间范围值 有Integer Long BigInteger
        Range<Integer> canonical = integerRange.canonical(DiscreteDomain.integers());


        //4.闭区间的范围 [1,10]
        Range<Integer> closed = Range.closed(1, 10);
        System.out.println(closed.contains(1));
        System.out.println(closed.contains(10));
        //true
        //true
        //4.左半闭区间[1,10)
        Range<Integer> integerRange1 = Range.closedOpen(1, 10);
        System.out.println(integerRange1.contains(1));
        System.out.println(integerRange1.contains(10));
        //true
        //false
        //5.右半闭区间(1,10]
        Range<Integer> integerRange2 = Range.openClosed(1, 10);
        System.out.println(integerRange2.contains(1));
        System.out.println(integerRange2.contains(10));
        //false
        //true

        //6.全闭区间(1,10)
        Range<Integer> open = Range.open(1, 10);
        System.out.println(open.contains(1));
        System.out.println(open.contains(10));
        //false
        //false


        //7.返回无上线[10,无穷]
        Range<Integer> integerRange3 = Range.downTo(10, BoundType.CLOSED);
        System.out.println(integerRange3.contains(1000));
        System.out.println(integerRange3.contains(1000111));
        System.out.println(integerRange3.contains(1));
        //true
        //true
        //false

        //8.返回无下限(无穷,10]
        Range<Integer> integerRange4 = Range.upTo(10, BoundType.CLOSED);


        //9.根据范围返回一个[1,10]
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(10);
        Range<Integer> d = Range.encloseAll(a);
        System.out.println(d.contains(1));
        System.out.println(d.contains(4));
        System.out.println(d.contains(9));
        System.out.println(d.contains(10));
        System.out.println(d.contains(1111));
        //true
        //true
        //true
        //true
        //false


        //10.判断是否有上下限  hasLowerBound    hasUpperBound

        //11.交集 intersection
    }
}
