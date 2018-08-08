package _000_day._007_8月02号线程;

import org.junit.runner.notification.RunListener;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/3 下午2:26<br/>
 */
public class 阻塞队列的使用 {

    private static ArrayBlockingQueue<Integer> number = new ArrayBlockingQueue<>(2);//大小为3的阻塞队列

    private static class IncrNumnber extends Thread{
        private int number1 = 1;

        @Override
        public void run() {
            try {
                while (true) {
                    Thread.sleep(200);//1秒生产一个
                    int i = number1++;
                    number.put(i);
                    System.out.println("生产了数字-------》" + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        new IncrNumnber().start();


        //1秒生产一个2秒消费一个
        while (true){
            try {
                Thread.sleep(2000);//2秒消耗一个
                Integer poll = number.take();
                System.out.println("消耗了数字=====》"+poll);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
