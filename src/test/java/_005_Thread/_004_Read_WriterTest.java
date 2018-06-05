package _005_Thread;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的操作
 */
public class _004_Read_WriterTest {

   @Test
   public void baseUse1(){

       Data data = new Data(10);
       Writer writer = new Writer("Dyy写线程", data, "ABCDEFJHIGK");
       Writer writer1 = new Writer("LCH写线程", data, "abcdefjhigk");
       Writer writer2 = new Writer("WD写线程", data, "1234567890");

       Reader dcy读线程 = new Reader("Dcy读线程", data);
       Reader dcy读线程1 = new Reader("DDD读线程", data);
       Reader dcy读线程2 = new Reader("Ddy读线程", data);

       dcy读线程.start();
       dcy读线程1.start();
       dcy读线程2.start();
       writer.start();
       writer1.start();
       writer2.start();

       while (true);
   }


   @Test
    public void baseUse2(){

   }
}


class Data{
    private final char[] buffer;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public Data(int capcity) {
        this.buffer = new char[capcity];
        for (char c : buffer) {
            c='*';
        }
    }

    //1.读锁的操作
    public char[] read() throws InterruptedException {
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        readLock.lock();
        try {
            char[] chars = new char[buffer.length];
            for (int i = 0; i < chars.length; i++) {
                chars[i] = buffer[i];
            }
            Thread.sleep(50);
            return chars;
        }finally {
            readLock.unlock();
        }
    }

    //2.写的操作
    public void  write(char chars) throws InterruptedException {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try{
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = chars;
                Thread.sleep(50);
            }

        }finally {
            writeLock.unlock();
        }
    }
}
//读的线程
class Reader extends Thread{
    private final Data data;

    private  static  int number;
    public Reader(String name, Data data) {
        super(name);
        this.data = data;
    }


    @Override
    public void run() {
        while (true) {
            try {
                char[] read = data.read();
                number++;
                System.out.println(Thread.currentThread().getName() + "第" + number + "次" + "读出来的参数是" + Arrays.toString(read));
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
//写的线程
class Writer extends Thread{
    private final Data data;
    private final String filter;
    private int index;
    private static int number;

    public Writer(String name, Data data, String filter) {
        super(name);
        this.data = data;
        this.filter = filter;
    }

    @Override
    public void run() {
        while (true) {
            try {
                char aChar = getChar();
                data.write(aChar);
                number++;
                System.out.println(Thread.currentThread().getName() + "第" + number + "次" + "写入的字符是" + aChar);
                Thread.sleep(new Random().nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public char getChar(){
        char c = filter.charAt(index++);
        if(index >= filter.length()){
            index = 0;
        }
        return c;
    }
}