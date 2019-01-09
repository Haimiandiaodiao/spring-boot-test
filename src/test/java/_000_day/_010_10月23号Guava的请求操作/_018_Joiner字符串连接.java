package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:54 2018/12/20
 */
public class _018_Joiner字符串连接 {

    @Test
    public void 测试连接器(){
        //============【1】连接器里面不能存在为空的数据 否则会包错
        Joiner joiner = Joiner.on(",").skipNulls();
        String join = joiner.join(Lists.newArrayList("A", "B",null, "C", "D"));
        System.out.println(join);
    }

    @Test
    public void 测试拆分器(){
        //============【2】测试拆分器
       String str1 = ",a, ,b,,c,d,";
        String[] split = str1.split(",");
        //【注意】 末尾的分隔符被 默默的丢弃了
        System.out.println(Arrays.toString(split));

        //2.使用Guava的Splitter来进行操作
        Iterable<String> split1 = Splitter.on(",").trimResults().omitEmptyStrings().split(str1);
        System.out.println(split1);
    }

    @Test
    public void 字符匹配器的使用(){
        //字符匹配器可以解决的问题是：
        //1.怎么才算匹配字符
        //2.如果处理这些匹配的字符
        String str1 = "dsfDadyyfa2 123 1 23 123 1    1231 1 DD dsf  234  sdf ";
        String s = CharMatcher.DIGIT.replaceFrom(str1,"*");
        String s1 = CharMatcher.WHITESPACE.trimTrailingFrom(s);
        System.out.println(s1);


        //==========使用====匹配任何一个就行
        CharMatcher dyy = CharMatcher.anyOf("Dyy");
        String s2 = dyy.replaceFrom(str1, '-');
        System.out.println(s2);
        //================== 不在这其中之一
        CharMatcher dyy1 = CharMatcher.noneOf("Dyy");
        String s21 = dyy1.replaceFrom(str1, '-');
        System.out.println(s21);
        //================= 匹配一个 字符
        CharMatcher d = CharMatcher.is('D');
        String s3 = d.replaceFrom(str1, '-');
        String s4 = d.collapseFrom(str1, '=');
        System.out.println(s3);
        System.out.println(s4);
        //=================== 获得字符集
        str1.getBytes(Charsets.UTF_8);
    }






}
