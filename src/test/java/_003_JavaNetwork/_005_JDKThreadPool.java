package _003_JavaNetwork;

import org.junit.Test;
import org.springframework.util.SystemPropertyUtils;

import java.util.concurrent.*;

public class _005_JDKThreadPool {
    private class myTask implements Runnable{
        private  int  taskNum;

        public myTask(int num){
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"执行中==>"+taskNum);
            try {
//                Thread.currentThread().sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"执行结束==>"+taskNum);
        }
    }

    /**
     * 1.JDK线程池的使用
     */
    @Test
    public void baseUse1(){
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        ExecutorService pool = Executors.newFixedThreadPool(2);
//        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 25; i++) {
            myTask myTask = new myTask(i);
            pool.execute(myTask);
//            System.out.println("线程池中的线程数目："+pool.getPoolSize()+",队列中等待执行的任务数目:"+pool.getQueue().size()+"," +
//                    "已执行完的任务数目:"+pool.getCompletedTaskCount());

        }
        pool.shutdown();


    }

}
