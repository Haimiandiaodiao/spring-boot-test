import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dyy.Modul.Entity.Father;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    @Test
    public void show(){
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tenDayAgo = now.plusMinutes(-1*10);

        System.out.println("当前的时间"+now.toString());
        System.out.println("十分钟之前"+tenDayAgo.toString());
    }

    //4.反转一个英文句子
    public static void main(String[] args) {
        String term = "StudentNameAge";
        StringBuffer sb = new StringBuffer(term);

    }

    @Test
    public void show2(){
        List<String> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        l.add("c");
        l.add("d");

        String s = JSON.toJSONString(l);

        List<String> parse = (List<String>) JSONArray.parse(s);
        List<Integer> list = JSON.parseObject(s, List.class);
        System.out.println(parse);

    }


    @Test
    public void formatJson(){
//        {"code":"${CODE}"}
        Map info = new HashMap();
        info.put("code","dYY");

        String s = JSON.toJSONString(info);
        System.out.println(s);
        String s1 = JSON.toJSONString(s);
        System.out.println(s1);

    }

    @Test
    public void split(){
        String phone = "16612345678";
            String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
            if (phone.length() != 11) {
                System.out.println(false);
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();

                if (!isMatch) {
                   System.out.println(false);
                }
                   System.out.println(true);

            }

    }

    @Test
    public void replace(){
        String ss="\"{\\\"code\\\":\\\"${CODE}\\\"}\"";
        String s = ss.replaceAll("\\$\\{CODE\\}", "13456789");
        System.out.println(s);
    }
}
