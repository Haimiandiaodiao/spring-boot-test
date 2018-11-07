package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.BoundType;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:56 2018/10/26
 */
public class _005_Range序列 {

    /**
     *   最好采用Debug的形式进行查看运行结果
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


        Range<Integer> closed1 = Range.closed(1, 10);
        Range<Integer> closed2 = Range.closed(50, 100);

        //10.判断是否有上下限  hasLowerBound    hasUpperBound

        //11.交集 intersection  如果连个不存在交集的集合作交集运算会产生异常
//        Range<Integer> intersection = closed1.intersection(closed2);
        //12.查询是否存在交集 判断两个区间是否产生交集
        boolean connected = closed1.isConnected(closed2);


        //13.判断区间集合是否为空(1,1]这样的区间存在则说明是空的
        Range<Integer> integerRange5 = Range.closedOpen(10, 10);
        boolean empty = integerRange5.isEmpty();


        //14.返回严格小于一个点的区间
        Range<Integer> integerRange6 = Range.lessThan(10);

        //15.返回一个区间类型的上下限 是开区间还是闭区间
        BoundType boundType = integerRange5.lowerBoundType();
        BoundType boundType1 = integerRange5.upperBoundType();

        //16.返回该低端点的数  但是在返回无边界的区间对象时 会抛出异常
        Integer integer = integerRange5.lowerEndpoint();
        Integer integer1 = integerRange6.lowerEndpoint();




    }
}
