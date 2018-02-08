package _001_Jdk8TestPackage;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 自定义质数收集器
 * @auther Dyy
 * @create 2018/2/7
 */
public class _006_PrimeNumber {
    //自定义质数匹配规则
    //检测数不能超过被测试数的平方根， 检测没有能整除的元素
    public boolean isPrime(int candidate){
        int sqrt = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,sqrt).noneMatch(i ->candidate % i ==0);
    }


    //版本2 的质数检测
    public boolean isPrime1(List<Integer> primes,int candidate){
        int sqrt = (int) Math.sqrt((double) candidate);
        return takeWhile(primes,i -> i <= candidate).stream().noneMatch(i ->candidate % i ==0);
    }

    //返回满足条件p的最大列表
    public <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        int i = 0;
        for (A a : list) {
            if(!p.test(a)){
                return  list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    /**
     * 1.获得指定范围的质数
     */
    @Test
    public void baseUse1(){
        int rangEnd = 50;
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, rangEnd).boxed()
                .collect(Collectors.partitioningBy(s -> isPrime(s)));
    }

    /**
     * 2.获得指定范围的质数
     */
    @Test
    public void baseUse2(){
        int rangEnd = 50;
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, rangEnd).boxed()
                .collect(new PrimeNumersCollector());
    }

    /**
     * 自定义的collector收集器
     */
    private class PrimeNumersCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>>{

        @Override
        public Supplier<Map<Boolean, List<Integer>>> supplier() {
            return () -> new HashMap<Boolean,List<Integer>>(){{
                //这个算是匿名内部类
                put(true,new ArrayList<Integer>());
                put(false,new ArrayList<Integer>());
            }};
        }

        //迭代中的累加器

        @Override
        public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
            return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
                acc.get( isPrime1(acc.get(true), candidate) )
                        .add(candidate);
            };
        }

        @Override
        public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
            return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
                map1.get(true).addAll(map2.get(true));
                map1.get(false).addAll(map2.get(false));
                return  map1;
            };
        }

        @Override
        public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
            return Function.identity();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
        }
    }
}
