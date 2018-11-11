package _000_day._010_10月23号Guava的请求操作;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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
        BigInteger bigInteger = new BigInteger("1111111111111111111111111");
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
}
