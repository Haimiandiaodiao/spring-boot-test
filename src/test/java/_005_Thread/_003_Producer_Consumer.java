package _005_Thread;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 1.桌子
 * 2。生产者
 * 3。消费者
 */
public class _003_Producer_Consumer {

    //1.单生产者单消费者
   /* @Test
    public void baseUse1(){
        Table table = new Table(5);
        Produce produce = new Produce(table, 1000, "工厂1", "董阳阳");
        Cusomer cusomer = new Cusomer("饭店1", table, 1000, "小风消费");
        produce.start();
        cusomer.start();
        while (true);//如果使用的main方法的话就不用加这一句话。程序就会主动的退出。
    }*/

    //2.多生产者多消费者的例子
   /* @Test
    public void baseUse2(){
        Table table = new Table(5);
        Produce produce = new Produce(table, 1000, "工厂1", "董阳阳");
        Produce produce1 = new Produce(table, 1000, "工厂2", "王东");
        Cusomer cusomer = new Cusomer("饭店1", table, 1000, "小风消费");

        produce.start();
        produce1.start();
        cusomer.start();
        while (true);
    }*/


    @Test
    public void baseUse3() throws InterruptedException {
        //对ArrayBlockingQueue的操作
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        String take1 = queue.take();
        String take2 = queue.take();
        String take3 = queue.take();


        queue.put("1");
        queue.put("2");
        queue.put("3");
        queue.put("4");
        queue.put("5");
        queue.put("6");
        queue.put("7");
        queue.put("8");
        //大于或者是小于指定的长度者需要崇勋


    }

    @Test
    public void baseUse5() throws InterruptedException {
        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>();
        String take = strings.take();

        String take1 = strings.take();

    }

    @Test
    public void baseUse4(){
        NewTable table = new NewTable(5);
        Produce produce = new Produce(table, 1000, "工厂1", "董阳阳");
        Cusomer cusomer = new Cusomer("饭店1", table, 1000, "小风消费");
        produce.start();
        cusomer.start();
        while (true);
    }

}

class NewTable extends ArrayBlockingQueue<String> {
    public NewTable(int capacity) {
        super(capacity);
    }

    public String take() throws InterruptedException {
        String take = super.take();
        System.out.println("从新桌子中拿"+take);
        return take;
    }

    public void put(String cake) throws InterruptedException {
        super.put(cake);
        System.out.println("从新桌子中放"+cake);
    }

}


//1.桌子
class Table{
    private String[] cakes;
    private int tails;//下次存放的位置
    private int head;//下次取的位置
    private int count;//当前可消费的蛋糕数量

    public Table(int size) {
        this.count = 0;
        cakes = new String[size];
    }

    //1.method 将蛋糕放入到的桌子的方法 锁是这个Table
    public synchronized void put(String cake) throws InterruptedException {
        if(count >= cakes.length){
            System.out.println("桌子已经满了等待客户去消费");
            wait();
        }

        System.out.println(Thread.currentThread().getName()+"==>放入蛋糕==>"+cake);
        cakes[tails++] = cake;
        tails = (tails)%cakes.length;
        count++;
        notifyAll();//唤醒所有的等待队列
    }


    //2.method 将蛋糕从桌子上拿走
    public synchronized String get() throws InterruptedException {
        if(count == 0 ){
            System.out.println("没有蛋糕等待被吃了，需要等待");
            wait();
        }
        String cake = cakes[head++];
        head = (head) % cakes.length;

        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"==>拿走蛋糕==>"+cake);
        return cake;
    }
}

//2.生产者
class Produce extends Thread{
    private  static int id ;
//    private final Table table ;
    private final NewTable table;
    private final Random random;
    private final String produceName;
    public Produce(NewTable table,long seed,String name,String produceName) {
        super(name);
        this.table = table;
        this.random = new Random(seed);
        this.produceName = produceName;
    }

    @Override
    public void run() {
        while (true) {
            int i = nextId();
            String s = "蛋糕的Id==>" + i + "生成者==>" + this.produceName + "==生产的线程==>" + currentThread().getName();
            try {
                Thread.sleep(200);
                table.put(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized  int nextId(){
        return ++id;
    }
}

//3.消费者
class Cusomer extends Thread{
//    private final Table table;
    private final NewTable table;
    private final Random random;
    private final String consumerName;

    public Cusomer(String name, NewTable table, int random,String consumer) {
        super(name);
        this.table = table;
        this.consumerName = consumer;
        this.random = new Random(random);
    }


    @Override
    public void run() {
        while (true) {
            try {
                String s = table.take();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}