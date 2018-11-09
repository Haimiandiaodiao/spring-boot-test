package _000_day._010_10月23号Guava的请求操作;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/9 11:34 PM<br/>
 */
public class _009_Iterabledie迭代器的操作 {


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
}
