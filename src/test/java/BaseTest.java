import com.dyy.Modul.Entity.Father;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基础的一些测试
 * @auther Dyy
 * @create 2018/1/25
 */
public class BaseTest {

    /**
     * 1.查看不会不覆盖lombok的getset方法
     */
    @Test
    public void LombTest(){
        Father f = new Father();
        f.setName("dd");
        f.setAge(48);
        String name = f.getName();
        System.out.println(name);
        System.out.println(f.getAge());
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }

    /**
     * List转Set
     */
    @Test
    public void ListToSet(){
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("b");
        a.add("a");

        Set<String> b = new HashSet<>();
        b.addAll(a);
        System.out.println(b);

        BigDecimal aa = new BigDecimal("1.00");
        BigDecimal bb = new BigDecimal("3.00");

        BigDecimal divide = aa.divide(bb,4,BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);

    }


}
