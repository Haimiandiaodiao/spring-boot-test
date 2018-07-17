import com.dyy.Modul.Entity.Father;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Test
    public void baseUse3(){
        //终态的不让进行添加删除的操作
//        ObjectList<Object> objects = Collections.emptyList();
//        objects.add("1");
//        List<Object> objects1 = Collections.emptyList();
//        objects1.add("a");
//        List<Object> objects2 = Collections.emptyList();
//        objects2.add("#");
//        int i = new Random().nextInt(60);

//        System.out.println(i);
//
//        byte[] bytes = "abcdefg".getBytes();
//        ByteBuffer wrap = ByteBuffer.wrap(bytes);
//        bytes[0]=1;
        
        
        if('\u0020' == ' '){
            System.out.println("true");
        }else{
            System.out.println('\u0000');
        }
    }

    @Test
    public void baseUse15(){
        File file = new File("/");
        System.out.println(file);
    }


    @Test
    public void name() {
        for (int i = 0; i==0; i++) {
            System.out.println("是否进来了");
        }
        System.out.println("ss");
    }


    @Test
    public void memory() {
        System.out.println("TotalMemory：" + (Runtime.getRuntime().totalMemory()/(1024*1024) + "M"));

        System.out.println("Max Memory ：" + (Runtime.getRuntime().maxMemory()/(1024*1024) + "M"));

        System.out.println("Free Memory ：" + (Runtime.getRuntime().freeMemory()/(1024*1024) + "M"));
    }


    @Test
    public void aa(){
        String a= "      \r\n  \\\\\\\\\\\\\\";
        String commonsLineLines ="^\\s*(\\\\).*";
        System.out.println(a.matches(commonsLineLines));
    }




}
