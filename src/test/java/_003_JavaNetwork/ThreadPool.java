package _003_JavaNetwork;

import org.junit.Test;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup{
    private boolean isClosed = false;//线程池是否关闭
    private LinkedList<Runnable> workQueue;//表示工作队列
    private static int threadPoolID;//表示线程池ID
    private int threadID;//表示工作线程ID

    //内部类：工作线程
    private class WorkThread extends Thread{
        public WorkThread(){
            //加入到当前ThreadPool线程组中
            super(ThreadPool.this,"WorkThread-"+(threadID++));
        }

        @Override
        public void run(){
            while (!isInterrupted()){//判断线程是否被中断
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {}

                //如果getTask()返回njull或者线程执行getTask()时被中断，则结束此线程
                if(task == null)return;
                try {
                    //运行任务，异常在catch代码块中捕获
                    task.run();
                }catch (Throwable e){
                    e.printStackTrace();
                }
            }
        }
    }
//==============================================================
    //等待工作线程把所有任务执行完
    public void join(){
        synchronized (this){
            isClosed = true;
            notifyAll();//唤醒还在getTask方法中等待的工作线程
        }
        Thread[] threads = new Thread[activeCount()];
        //该方法继承自ThreadGroup类，获得线程组中当前所有或者的工作线程
        int count = enumerate(threads);
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();      //将该线程插队
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    //关闭线程池
    public synchronized void close(){
        if(!isClosed){
            isClosed = true;
            workQueue.clear();
            interrupt();//中断所有的工作线程，该方法继承自ThreadGroup类
        }
    }

    //向工作队列中加入一个新任务，由工作线程去执行该任务
    public synchronized void execute(Runnable task){
        if(isClosed){       //线程池被关闭则抛出IllegalStateException
            throw new IllegalStateException();
        }
        if(task != null){
            workQueue.add(task);
            notify();   //唤醒正在getTask（）方法中等待任务的工作线程
        }
    }


    //从工作队列中取出一个任务，工作线程会调用此方法
    protected synchronized Runnable getTask() throws InterruptedException {
        while (workQueue.size() == 0){
            if(isClosed)return null;
            wait();                 //如果工作队列中没有任务，就等待任务
        }
        return workQueue.removeFirst();
    }


    public ThreadPool(int poolSize){//指定线程池中的工作线程数目
        super("ThreaPool-"+(threadPoolID++));
        setDaemon(true);
        workQueue = new LinkedList<Runnable>();//创建工作队列
        for (int i = 0; i < poolSize; i++) {
            new WorkThread().start();
        }
    }

    public ThreadPool(String name) {
        super(name);
    }

    public ThreadPool(ThreadGroup parent, String name) {
        super(parent, name);
    }
}


class TheadPoolTester{

    public static void main(String[] args){
        ThreadPool threadPool = new ThreadPool(3);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(createTask(i));
        }
            threadPool.join();
    }

    private static Runnable createTask(final int taskID){
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Task"+taskID+":start");
                try {
                    Thread.sleep(500);//增加执行任务时间
                } catch (InterruptedException e) {}

                System.out.println("Task "+taskID+": end");
            }
        };
    }
}