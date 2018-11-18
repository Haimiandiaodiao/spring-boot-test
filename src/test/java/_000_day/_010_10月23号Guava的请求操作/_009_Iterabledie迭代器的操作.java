package _000_day._010_10月23号Guava的请求操作;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/9 11:34 PM<br/>
 */
public class _009_Iterabledie迭代器的操作 {
    //http://ifeve.com/google-guava-collectionutilities/
    //http://ifeve.com/google-guava-newcollectiontypes/
    @Test
    public void 迭代器的基本使用() {
        List<Integer> integers = Ints.asList(1, 2, 3);
        List<Float> floats = Floats.asList(1.1f, 2.2f);
        BigDecimal bigDecimal = new BigDecimal("1.1111");
        BigInteger bigInteger = new BigInteger("11111",2);
        List<Integer> integers1 = Ints.asList(1, 1, 2);

        ArrayList<Number> bigDecimals = Lists.newArrayList(bigDecimal,bigInteger);


        Iterable<? extends Number> concat = Iterables.concat(integers,floats,bigDecimals,integers1);

        int frequency = Iterables.frequency(concat, 1);

        //Integer
        System.out.println(concat);

    }


    /**
     * frequency使用的是equals的方法
     * <br/>
     * @author Dyy <br/>
     * @description <br/>
     * @date 2018/11/11 11:57 PM <br/>
     * @param  <br/>
     */
    @Test
    public void 测试Iterables的frequency使用的比较方法() {
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(3);
        entity3.setNum(1);

        ArrayList<Entity1> entity1s = Lists.newArrayList(entity1, entity2, entity3, entity1);

        int frequency = Iterables.frequency(entity1s, entity1);
        Iterable<Entity1> concat = Iterables.concat(entity1s);
        int frequency1 = Iterables.frequency(concat, entity1);
        System.out.println(frequency+"<=====>"+frequency1);

        System.out.println();

        //1.分割成子集但是都不能进行增删改的操作
        Iterable<List<Entity1>> partition = Iterables.partition(concat, 2);

        ArrayList<Entity1> entity1s1 = Lists.newArrayList(entity1);
        Iterable<Entity1> concat1 = Iterables.concat(entity1s1);

        partition.forEach(e->{
            Iterables.addAll(e,concat1);
            System.out.println("===>"+e.toString());


        });
        System.out.println(partition);
    }

    @Test
    public void Iterables的常规用法() {
        Entity1 entity1 = new Entity1();
        entity1.setId(1);
        Entity1 entity2 = new Entity1();
        entity2.setId(1);
        Entity1 entity3 = new Entity1();
        entity3.setId(3);
        entity3.setNum(1);

        ArrayList<Entity1> entity1s = Lists.newArrayList(entity1, entity2, entity3, entity1);
        Iterable<Entity1> concat = Iterables.concat(entity1s);

        Iterable<String> concat1 = Iterables.concat();


        //1.得到集合个第一个元素如果为空则返回默认值
        Comparable<? extends Comparable<?>> first = Iterables.getFirst(concat, "1");
        String a = Iterables.getFirst(concat1, "A");

        System.out.println("===>"+first);
        System.out.println("====>"+a);

        //2.得到一个迭代器的最后一个元素
        //2.1如果为空则抛出异常
        Entity1 last = Iterables.getLast(concat);
//        String last1 = Iterables.getLast(concat1);
        //2.2带默认值的
        String last2 = Iterables.getLast(concat1, "Last");
        System.out.println(last2);


        //3.Iterable的比较iterate中的所有的元素相等且顺序一致，返回true
        boolean b = Iterables.elementsEqual(concat1, concat);
        System.out.println(b);//false
        boolean b1 = Iterables.elementsEqual(concat, concat);
        System.out.println(b1);//true

        //4.返回Iterables的不可变视图
        Iterable<Entity1> entity1s1 = Iterables.unmodifiableIterable(concat);
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        //生成的视图会跟着原始数据的改变而改变
        Iterable<Integer> integers1 = Iterables.unmodifiableIterable(integers);
        integers.add(10);
        //由视图构建的集合也可以改变
        ArrayList<Integer> umList = Lists.newArrayList(integers1);
        umList.add(11);

        //5。限定的Iterable的元,2素的个数不改变集合的大小只是改变迭代元素的个数
        Iterable<Integer> limit = Iterables.limit(integers, 2);


        //6。获取迭代器中唯一的元素，如果迭代器为空或有多个元素，则快速失败  还有一种带默认值的方法
        Entity1 onlyElement = Iterables.getOnlyElement(concat);



        //7.一些其他的常用方法
        Iterables.addAll(integers,integers);
        Iterables.contains(integers,1);
        Iterables.removeAll(integers,integers);
        Iterables.size(integers);
        Iterables.toArray(integers,Integer.class);
        Iterables.isEmpty(integers);
        Iterables.get(integers,1);
        Iterables.toString(integers);

        //List提供的一些方法
        //1.切割
        List<List<Integer>> partition = Lists.partition(integers, 2);
        ImmutableList<Integer> of = ImmutableList.of(1);

        System.out.println();



    }
}
