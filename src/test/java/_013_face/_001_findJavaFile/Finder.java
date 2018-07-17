package _013_face._001_findJavaFile;

import com.rabbitmq.client.AMQP;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/13 下午3:53<br/>
 */
public class Finder {
    //1.计算空行数
    //2.计算行注解数
    //3.计算文档注解数
    //4.计算代码的行数
    public static AtomicLong spaceLines = new AtomicLong(0);
    public static AtomicLong commonsLineLines = new AtomicLong(0);
    public static AtomicLong documentLineLines = new AtomicLong(0);
    public static AtomicLong codeLines = new AtomicLong(0);


    public static void main(String[] args) throws InterruptedException {
        File file = new File("/Users/dyy/IdeaProjects/spring-boot-test");
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 8,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        fileOpes(file,pool);

        while (true) {
            System.out.println("空行数=====>" + spaceLines.longValue());
            System.out.println("行注释=====>" + commonsLineLines.longValue());
            System.out.println("文档注释=====>" + documentLineLines.longValue());
            System.out.println("代码行数=====>" + codeLines.longValue());
        }
    }


    public static void fileOpes(File file,ThreadPoolExecutor pool){

        if(file.isFile()){
            if(file.getName().endsWith(".java")){
                System.out.println("找到的java=====>"+file.getName());
                Computer computer = new Computer(file);
                pool.execute(computer);
            }
        }else{
            File[] files = file.listFiles();
            for (File file1 : files) {
                fileOpes(file1,pool);
            }
        }
    }


    //多消费者单生产者
}

//class Containter<T> extends ArrayList{
//
//    private int containterSize;
//
//    private synchronized void addSource(T entity) throws InterruptedException {
//        if (this.size() >= containterSize){
//            this.wait();
//        }
//        this.add(entity);
//    }
//
//    private synchronized T  getSource(){
//        if(this.size() == 0){
//            this.notifyAll();
//        }
//    }
//}