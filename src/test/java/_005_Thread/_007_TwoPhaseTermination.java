package _005_Thread;

import org.junit.Test;
import org.springframework.boot.env.YamlPropertySourceLoader;

/**
 * 分两阶段终止
 * 它是一种先执行终止处理在终止线程的模式
 */
public class _007_TwoPhaseTermination {

    /**
     * 1.设置未捕获异常和退出钩子程序。
     */
    @Test
    public void baseUse2(){

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("*********");
                System.out.println("UncaughtException -----Start---");
                System.out.println("currentThread ==>" + Thread.currentThread());
                System.out.println("Thread ==>" + t);
                System.out.println("exception==>" + e);
                System.out.println("UncaughtException -----End---");

            }
        };


        System.out.println("main线程开始");
        //设置未捕获异常
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        //设置退出钩子
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("*********");
                System.out.println("shutdownHoot start");
                System.out.println("thread==>"+Thread.currentThread());
                System.out.println("shutdownHook end");
            }
        });


        new Thread("MyThread"){
            @Override
            public void run() {
                
                System.out.println("MyThread ==> start");
                System.out.println("MyTHread ==> sleeping");

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                System.out.println("Mythread ==> aliver");
                int i = 1 / 0;
                System.out.println("MyThread ==> end");
            }
        }.start();

        System.out.println("Main  end");
        while (true);

    }



    @Test
    public void baseUse1(){

        try {
            System.out.println("主线程开始运行");
            CountupThread countupThread = new CountupThread();
            countupThread.start();
            Thread.sleep(10000);


            //主线程终止
            System.out.println("主线程关闭运行");
            countupThread.shutdown();

            countupThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程结束");
    }
}

class CountupThread extends Thread{
    private int count ;
    private volatile boolean shutdownFlag = true;

    //1.检测关闭状态
    public boolean isShutdown(){
        return shutdownFlag;
    }

    //2.关闭线程
    public void shutdown(){
        shutdownFlag = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            while (shutdownFlag){
                doWork();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            doShutdown();
        }

    }

    public void doWork() throws InterruptedException {
        count++;
        System.out.println("当前的记录号是===>"+count);
        Thread.sleep(1000);
    }

    public void doShutdown(){
        System.out.println("统计器被终止");
    }}
