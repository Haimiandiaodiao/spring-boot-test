package _000_day._010_10月23号Guava的请求操作;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;

import java.util.Objects;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 17:36 2018/10/26
 */
public class _004_Objects公用类 {

    /**
     * 基本没有什么可用的方法
     *
     *
     * */

    @Test
    public void 比较两个对象是否相等(){
        Integer a = new Integer(1111111);
        Integer b = new Integer(1111111);
        System.out.println(a==b);
        System.out.println(a.equals(b));
        //false
        //true

        //1.调用的是equals方法
        boolean equals = Objects.equals(a, b);
        System.out.println(equals);

        //2.生成多个值的HasCode
        int hash = Objects.hash("1", 2, 3);


    }
}
