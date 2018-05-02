package _001_Jdk8TestPackage;

import com.dyy.Modul.Entity.Trader;
import com.dyy.Modul.Entity.Transaction;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collector.*;
import static java.util.stream.Collectors.*;

public class _007_StreamTest {

    /**
     * 1.几个例题
     */
    @Test
    public void baseUse1(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    //(1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> collect = transactions.stream().filter(e -> e.getYear() == 2011).sorted(Comparator.comparing(Transaction::getYear)).collect(toList());
        collect.stream().forEach(System.out::println);

        //(2) 交易员都在哪些不同的城市工作过
        List<String> collect1 = Stream.of(raoul, mario, alan, brian).map(Trader::getCity).distinct().collect(toList());
        collect1.stream().forEach(System.out::println);

        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> cambridge = Stream.of(raoul, mario, alan, brian).filter(e -> e.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName)).collect(toList());
        cambridge.stream().forEach(System.out::println);

        Stream<Trader> raoul1 = Stream.of(raoul, mario, alan, brian);
        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> collect2 = raoul1.map(Trader::getName).sorted(String::compareTo).collect(toList());
        collect2.stream().forEach(System.out::println);




        //(5) 有没有交易员是在米兰工作的？
        boolean milan = Stream.of(raoul, mario, alan, brian).anyMatch(e -> e.getCity().equals("Milan"));
        System.out.println(milan);

        //(6) 打印生活在剑桥的交易员的所有交易额。
        int cambridge2 = transactions.stream().filter(e -> {
            Stream<Trader> raoul2 = Stream.of(raoul, mario, alan, brian);
            Stream<Trader> cambridge1 = raoul2.filter(user -> user.getCity().equals("Cambridge"));
            return cambridge1.anyMatch(user -> user.equals(e.getTrader()));
        }).mapToInt(Transaction::getValue).sum();
        System.out.println(cambridge2);


//      transactions.stream().sorted(Comparator.comparing(Comparator.comparing(Integer::compare)));
    }


    /**
     * 2.生成基础类型流
     */
    @Test
    public void baseUse2(){
        //转换成基础类型流
        IntStream intStream = Stream.of(0,-1,-2).mapToInt(Integer::intValue);
        OptionalInt max = intStream.min();
        System.out.println(max.getAsInt());
    }

    /**
     * 3.数值的范围
     */
    @Test
    public void baseUse3(){
        IntStream intStream = IntStream.rangeClosed(1, 100);
    }


    /**
     * 4.100以内的整数勾股数
     */
    @Test
    public void baseUse4(){
        IntStream.rangeClosed(1,100).boxed().flatMap(a->{
            return  IntStream.rangeClosed(1, 100).filter(b -> {
                return Math.sqrt(a * a + b * b) % 1 == 0;
            }).mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)});
        });
    }


    /**
     * 5.流的创建
     */
    @Test
    public void baseUse5(){
        //产生斐波那契数
        Stream.iterate(new int[]{0,1},e->new int[]{e[1],e[0]+e[1]}).limit(10).forEach(e->System.out.println(Arrays.toString(e)));
    }

    /**
     * 6.字符数组的拼接
     */
    @Test
    public void baseUse6(){
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(1, 20).boxed().collect(groupingBy(e -> e < 10 ));


        IntStream.rangeClosed(1, 30).boxed().collect(groupingBy(e -> {
            if (e <= 10 && e > 0) {
                return "Ten";
            } else {
                if (e <= 20 && e > 10) {
                    return "Two";
                } else {
                    if (e <= 30 && e > 20) {
                        return "Three";
                    } else {
                        return "Other";
                    }
                }
            }
        }, groupingBy(e -> {
            return e % 2 == 0;
        },groupingBy(ss->{return ss == 1;}))));
        System.out.println("");
    }
}
