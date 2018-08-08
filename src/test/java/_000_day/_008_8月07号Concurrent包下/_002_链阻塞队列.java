package _000_day._008_8月07号Concurrent包下;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/7 下午11:05<br/>
 */
public class _002_链阻塞队列 {

    @Test
    public void name() throws InterruptedException {
        LinkedBlockingQueue<Integer> list = new LinkedBlockingQueue<>();

        for (int i = 0; i < 100000; i++) {
            list.put(i);
        }

        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(15);
        System.out.println(list);
    }
}
