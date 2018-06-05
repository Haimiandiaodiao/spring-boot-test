package _005_Thread;

import org.junit.Test;

import javax.naming.Name;
import java.util.concurrent.*;

/**
 *  1确保自任务都完成的一种机制 保证制约的任务都执行完
 *  使工作步调一致
 */
public class _008_CountDownLatchTest {

    @Test
    public void baseUse1() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        //使用固定的线程池创建开启任务
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyTask(countDownLatch,"任务"+i));
        }

        System.out.println("等待工作结束");
        countDownLatch.await();//阻塞  等待所有的任务执行完
//        long count = countDownLatch.getCount();//获得没有结束运行的 任务书；任务书

        System.out.println("main 运行结束");
    }


    /**
     * 使用这种可重复使用的CyclicBarrier的同步不能让执行的线程数小于任务量，因为在每个任务中都在等待
     * 否则就阻塞死了 因为线程池就5个线程都在等待另外5个
     */
    @Test
    public void baseUse2() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("CyclicBarrier Action!");
            }
        };


        ExecutorService executor = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,runnable);
        //使用固定的线程池创建开启任务
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyTaskB(countDownLatch,cyclicBarrier,"任务"+i));
        }

        System.out.println("等待工作结束");
        countDownLatch.await();//阻塞  等待所有的任务执行完
//        long count = countDownLatch.getCount();//获得没有结束运行的 任务书；任务书

        System.out.println("main 运行结束");
    }
}


class MyTask implements Runnable{
    private CountDownLatch countDownLatch;
    private String name;

    public MyTask(CountDownLatch countDownLatch, String name) {
        this.countDownLatch = countDownLatch;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("任务 start ==》"+name+"==》开始执行了" +"===>线程==》"+Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();
        System.out.println("任务 end ==》"+name+"==》开始执行了"+"===>线程==》"+Thread.currentThread().getName());
    }

}


class MyTaskB implements Runnable{
    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;
    private String name;
    private int phase = 5;

    public MyTaskB(CountDownLatch countDownLatch, CyclicBarrier cyclicBarrier, String name) {
        this.countDownLatch = countDownLatch;
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < phase; i++) {
                doWork();
                int numberWaiting = cyclicBarrier.getNumberWaiting();
                System.out.println("阶段"+i+"===》等待的个数"+numberWaiting);
                cyclicBarrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }finally {
            countDownLatch.countDown();
        }

    }

    public void doWork(){
        try {
            System.out.println("任务 start ==》"+name+"==》开始执行了" +"===>线程==》"+Thread.currentThread().getName());

            Thread.sleep(3000);

            System.out.println("任务 end ==》"+name+"==》开始执行了"+"===>线程==》"+Thread.currentThread().getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
