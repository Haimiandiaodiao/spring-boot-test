package _001_Jdk8TestPackage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 构造流
 * @auther Dyy
 * @create 2018/2/6
 */
public class _004_StreamCreateApi {

    /**
     * 1.由值来创建流
     */
    @Test
    public void baseUse1(){
        Stream<String> a = Stream.of("A", "B", "C", "D");
        a.map(String::toLowerCase).forEach(System.out::println);
        System.out.println(a);
        //================
        //2.得到一个空流
        Stream<Object> empty = Stream.empty();
        //================
        //3.由数组创建一个流
        int[] number = {1,2,3,4,5,6,7};
        IntStream stream = Arrays.stream(number);

    }

    /**
     * 2.由函数生成流 無限流
     */
    @Test
    public void baseUse2(){
        Stream.iterate(0,n -> n+2).limit(10).forEach(System.out::println);
    }

    /**
     * 3.斐波纳契元数组前 30个元素
     */
    @Test
    public void baseUse3(){
        List<int[]> collect = Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10).collect(Collectors.toList());

        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }
    }


    /**
     * 4.使用Stream的generate来生成流的操作
     */
    @Test
    public void baseUse4(){
        Stream<Double> limit = Stream.generate(Math::random).limit(5);
        IntStream intStream = limit.mapToInt(s -> (int) ((10) * s));
        //==================返回斐波那契额的

    }
}
