package _005_Thread;

import org.junit.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 1.使用ThreadLocal来完线程日志的错误
 * 是用ThreadLocal来完成对每个线程特有对象的记录的工作
 * ThreadLocal的key对象的就是线程的。
 *
 */
public class _009_Thread_Specaific_StorageTest {

    @Test
    public void baseUse1(){
        Thread thr1 = new Thread(new TestLoger(), "Dyy的日志");
        Thread thr2 = new Thread(new TestLoger(), "WD的日志");
        Thread thr3 = new Thread(new TestLoger(), "ZZQ的日志");
        Thread thr4 = new Thread(new TestLoger(), "LCH的日志");

        thr1.start();
        thr2.start();
        thr3.start();
        thr4.start();

        while (true);
    }


}

/**
 * 1.实体日志的记录者
 */
class  TSLog{
    private PrintWriter print;

    public TSLog(String name) {
        try {
            this.print = new PrintWriter(new FileWriter(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //1.写日志
    public void log(String name){
        print.println(name);
    }

    //2.关闭日志
    public void close(){

        System.out.println("==========>关闭日志记录着"+"<=========");
        print.close();
    }
}

/**
 * 2.日志的操作工具类
 */
class Log{
    //这个ThreadLocal对象的拥有者是属于Main的
    private static final ThreadLocal<TSLog> log = new ThreadLocal();

    //1。得到每个线程自己的日志类
    private static TSLog getlog(){
        TSLog tsLog = log.get();
        if(tsLog == null){
            TSLog tsLog1 = new TSLog(Thread.currentThread().getName() + "-log.txt");
            log.set(tsLog1);
            tsLog = tsLog1;
        }
        return tsLog;
    }

    //2.记录日志
    public static void log(String log){
        TSLog getlog = getlog();
        getlog.log(log);
    }

    //3.关闭日志
    public static void close(){
        TSLog getlog = getlog();
        getlog.close();

    }
}

/**
 * 3.向其中写入信息的线程
 */
class TestLoger implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Log.log("66..===>"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
    }
}