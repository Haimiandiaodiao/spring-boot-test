package com.dyy.Jdk8_Package.Modul;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import static java.util.stream.Collector.Characteristics.*;
/**
 * 自定义收集器
 * @auther Dyy
 * @create 2018/2/6
 */
//T是流中要收集的项目的泛型
//A是累加器的类型，累加器是在收集过程中用于累积部分结果的对象
//R是收集器操作得到的对象，（通常但并不一定是集合）的类型
public class ToListCollector<T> implements Collector<T,List<T>,List<T>> {
    //创建集合操作的起始点 建立新的结果容器
    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }
    //累积遍历过的项目，原位修改累加器
    //当遍历到流汇总的地n个元素时，这个函数执行时会有两个参数保存地柜的已经收集了流汇总的前 n-1个项目，还有第n个元素本身
    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }
    //修改第一个累加器，将其与第二个累加器的内容合并
    //他定义了对流的哥哥子部分进行并行处理时，各个子部分规约到累加器要如何合并
    //这个可以进行并行计算
    @Override
    public BinaryOperator<List<T>> combiner() {
       return (list1,list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }
    //恒等函数
    //在遍历完流后finisher方法必须返回在累积过程的最后要调用的一个函数
    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }
    //为收集器添加IDENTITY_FINISH
    //会返回一个不可变的Characteristics集合他定义了收集器的行为尤其是关于流是否可以并行规约
    //1.unordered  合并结果不收六中项目的遍历和累积顺序的影响
    //2.concurrent accumulator 函数可以从多个线程同时调用并且该收集器可以并行合并，如果收集器灭有标识为unordered 那它仅在用于无序数据源时才可以并行合并
    //3.identity_finish 表明完成器方法返回的函数是一个恒等函数，可以跳过。这种情况下累加器对象将会直接用作合并过程的最终结果。这也意味着，将累加器A不加检查的转换为结果R是安全的
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH,CONCURRENT));
    }
}
