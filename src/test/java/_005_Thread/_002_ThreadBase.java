package _005_Thread;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class _002_ThreadBase {
    @Test
    public void baseUse1(){
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(new Printer("Good")).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("Nice");
        }
        synchronized (this){
        }
    }
}


class Printer implements Runnable{
    private String message;

    public Printer(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(message);
        }
    }
}