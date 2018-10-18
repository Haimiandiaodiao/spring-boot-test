package _000_day._008_8月07号Concurrent包下;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/7 下午1:20<br/>
 */

public class _001_延时队列 {

    @Test
    public void name() throws InterruptedException {
        //创建三个元素过期时间为 30秒之后
        long thrid = 30*1000000000l;
        long ten = 60*1000000000l;
        long night = 90*1000000000l;
        long l = System.nanoTime();
        DelayedItem delayedItem1 = new DelayedItem("30秒延迟", 1, l + thrid);
        DelayedItem delayedItem2 = new DelayedItem("60秒延迟", 3, l + ten);
        DelayedItem delayedItem3 = new DelayedItem("90秒延迟", 2, l + night);

        DelayQueue<Delayed> queue = new DelayQueue<>();
        queue.put(delayedItem1);
        queue.put(delayedItem2);
        queue.put(delayedItem3);

        Delayed take = queue.take();
        System.out.println("最一开始的take方法："+take);


        Thread.sleep(5000);
        Delayed take1 = queue.take();
        System.out.println("5秒延迟:"+take1);
        Thread.sleep(35000);
        Delayed take2 = queue.take();
        System.out.println("35秒延迟:"+take2);
        Delayed take3 = queue.take();
        Thread.sleep(95000);
        System.out.println();
        System.out.println("95秒延迟:"+take3);

    }


    @Test
    public void 优先级队列() throws InterruptedException {
        //创建三个元素过期时间为 30秒之后
        long thrid = 30*1000000000l;
        long ten = 60*1000000000l;
        long night = 90*1000000000l;
        long l = System.nanoTime();
        DelayedItem delayedItem1 = new DelayedItem("30秒延迟", 1, l + thrid);
        DelayedItem delayedItem2 = new DelayedItem("60秒延迟", 3, l + ten);
        DelayedItem delayedItem3 = new DelayedItem("90秒延迟", 2, l + night);


        PriorityBlockingQueue<DelayedItem> objects = new PriorityBlockingQueue<>(3);
        objects.put(delayedItem1);
        objects.put(delayedItem2);
        objects.put(delayedItem3);

        DelayedItem take = objects.take();
        System.out.println("第一次拿出："+take);
        DelayedItem take1 = objects.take();
        System.out.println("第二次拿出："+take1);
        DelayedItem take2 = objects.take();
        System.out.println("第三次拿出："+take2);

    }
}

class DelayedItem implements Delayed{
    private String msg;
    private Integer pro;
    private Long liveTime; //到期时间的纳秒值

    public DelayedItem(String msg, Integer pro, Long liveTime) {
        this.msg = msg;
        this.pro = pro;
        this.liveTime = liveTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println(toString());
       return liveTime - System.nanoTime();
    }

    @Override
    public int compareTo(Delayed o) {
        if ( o instanceof DelayedItem){
            DelayedItem tmp = (DelayedItem) o;
            if(pro > ((DelayedItem) o).pro) return 1;
            if(pro < ((DelayedItem) o).pro) return -1;
            if(pro == ((DelayedItem) o).pro) return 0;

        }
        return -1;
    }

    @Override
    public String toString() {
        return "DelayedItem{" +
                "msg='" + msg + '\'' +
                ", pro=" + pro +
                ", liveTime=" + liveTime +
                '}';
    }
}