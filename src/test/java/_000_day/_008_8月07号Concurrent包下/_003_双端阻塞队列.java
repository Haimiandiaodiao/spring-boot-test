package _000_day._008_8月07号Concurrent包下;

import org.junit.Test;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/8 下午3:57<br/>
 */
public class _003_双端阻塞队列 {


    @Test
    public void 基本的操作() throws InterruptedException {
        LinkedBlockingDeque<Object> list = new LinkedBlockingDeque<>();
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        list.addLast(4);

        Object take = list.takeLast();
        Object take1 = list.takeLast();
        Object take2 = list.takeLast();
        Object take3 = list.takeLast();

        ConcurrentHashMap map = new ConcurrentHashMap(16,1.0f,8);

    }


    @Test
    public void name() {

        Vector vector = new Vector();
        boolean add = vector.add("1");
        boolean add1 = vector.add("1");
        boolean add2 = vector.add("2");

    }


    @Test
    public void name1() {
        try{

            Object aa = null;
            aa.toString();
        }catch(Exception ex){
            System.out.println("2222222");
        }finally{
            System.out.println("111111111");

        }

    }
}
