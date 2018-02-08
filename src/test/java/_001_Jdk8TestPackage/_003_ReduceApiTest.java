package _001_Jdk8TestPackage;

import com.dyy.Jdk8_Package.Modul.Trader;
import com.dyy.Jdk8_Package.Modul.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Reduce 实践操作
 * @auther Dyy
 * @create 2018/2/5
 */
public class _003_ReduceApiTest {
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario","Milan");
    Trader alan = new Trader("Alan","Cambridge");
    Trader brian = new Trader("Brian","Cambridge");
    List<Transaction> tr = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    /**
     * 1.
     *
     */
    @Test
    public void baseUse1(){
       //查询2011年的所有的交易额进行排序从低到高
//        List<Transaction> collect = tr.stream().filter(s -> s.getYear() == 2011)
//                .sorted(Comparator.comparing(Transaction::getValue))
//                .collect(Collectors.toList());
//        System.out.println(collect);

        //================1.1交易员都在哪些不同的城市工作过

// List<String> collect = tr.stream().map(s -> s.getTrader().getCity())
//                .distinct()
//                .collect(Collectors.toList());
//        System.out.println(collect);

        //===============1.2交易员都在哪些不同的城市工作过另外一种方式 变为set

//        Set<String> collect = tr.stream().map(s -> s.getTrader().getCity())
//                .collect(Collectors.toSet());
//        System.out.println(collect);

        //================2查找来自剑桥的交易员 并且按照字母顺序进行排序
        List<Trader> cambridge = tr.stream().map(s -> s.getTrader()).filter(s -> s.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(toList());
        System.out.println(cambridge);


        //================3返回所有交易员的姓名字符串，按字母顺序排序
        String reduce = tr.stream().map(s -> s.getTrader().getName())
                .distinct().sorted().reduce("", (n1, n2) -> n1 + "---" + n2);
        System.out.println(reduce);

        //===============3.1新的方案来解决上面重复创建String对象的问题
        String reduce1 = tr.stream().map(s -> s.getTrader().getName())
                .distinct().sorted().collect(Collectors.joining(","));
        System.out.println(reduce1);


        //==============4有没有交易员是在米兰工作的
        boolean milan = tr.stream().anyMatch(s -> s.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        //==============5打印生活在剑桥的交易员的所有交易额
        Integer cambridge1 = tr.stream().filter(s -> s.getTrader().getCity().equals("Cambridge")).map(s -> s.getValue())
                .reduce(0, (s1, s2) -> s1 + s2);
        System.out.println("总得交易额-》"+cambridge1);

        //==============6所有交易额中最高的交易额
        Optional<Integer> reduce2 = tr.stream().map(s -> s.getValue()).reduce(Integer::max);
        System.out.println("最大交易额-》"+reduce2.get());
        //=============7查询最大交易额是谁产生的
        Optional<Transaction> max = tr.stream().max(Comparator.comparing(s -> s.getValue()));
        Optional<Transaction> max1 = tr.stream().max(Comparator.comparing(Transaction::getValue));
        System.out.println(max1);

        //=============8查询所有的交易额
        IntStream intStream = tr.stream().mapToInt(Transaction::getValue);
        int sum = intStream.sum();
        OptionalInt max2 = intStream.max();

        System.out.println(sum);
    }

    /**
     * 2.
     */
    @Test
    public void baseUse2(){
        //100范围的偶数
        IntStream intStream = IntStream.rangeClosed(1, 100).filter(s->s%2 ==0);
        long count = intStream.count();
        System.out.println(count);
    }

    /**
     * 3.重新回顾map和flatMap
     */
    @Test
    public void baseUse3(){
        //用数组来表示勾股数 new int[] {3,4,5}
        String[] strings = {"Hello", "World"};
        //每个去除完之后再合并
        List<String> collect = Arrays.stream(strings).map(a -> a.split("")).flatMap(s -> Arrays.stream(s).distinct()).collect(Collectors.toList());
        //合并完在去重
        List<String> collect1 = Arrays.stream(strings).map(a -> a.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        //每一个数组去重
        List<String[]> collect2 = Arrays.stream(strings).map(a -> a.split("")).distinct().collect(toList());

        System.out.println("去重完在和并"+collect);
        System.out.println("合并完在去重"+collect1);
    }


    /**
     * 4.勾股数的枚举
     */
    @Test
    public void baseUse4(){
        List<int[]> collect = IntStream.rangeClosed(1, 100).boxed().flatMap(
                s -> IntStream.rangeClosed(s, 100)
                        .filter(a -> Math.sqrt(s * s + a * a) % 1 == 0).mapToObj(a -> new int[]{s, a, (int) Math.sqrt(s * s + a * a)})
        ).collect(toList());

//        int i=1;
//        for (Stream<int[]> tmp : collect) {
//            List<int[]> collect1 = tmp.collect(toList());
//            for (int[] ints : collect1) {
//                System.out.println(Arrays.toString(ints));
//            }
//            System.out.println("下一个流=============="+i);
//            i++;
//        }
        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }
    }

}
