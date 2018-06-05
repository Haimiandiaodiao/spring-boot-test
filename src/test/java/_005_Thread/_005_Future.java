package _005_Thread;

import org.junit.Test;

import java.util.concurrent.Executor;

/**
 * 使用Future模式
 */
public class _005_Future {

    /**
     * 有限的避免了入口的瓶颈，增加的入口的请求量，这样做就加大了数据的吞吐量，因为处理数据有很大部分是IO操作
     * cpu是空闲的，这个时候就可以将这部分cpu时间让cpu还来接受请求。
     *
     * 但是这个时候当在处理的时间之内的话住线程会   阻塞
     */
    @Test
    public void baseUse1(){
        //1.创建请求1，2，3，4，5
        //1.1创建处理器
        Host host = new Host();
        DataA data1 = host.execut("Dyy请求", 1);
        DataA data2 = host.execut("WD请求", 2);
        DataA data3 = host.execut("LCH请求", 3);
        DataA data4 = host.execut("ZKC请求", 4);
        DataA data5 = host.execut("SNN请求", 5);

        //主线程会阻塞因为还没有处理完数据。
        System.out.println("处理数据1"+data1.getData());
        System.out.println("处理数据2"+data2.getData());
        System.out.println("处理数据3"+data3.getData());
        System.out.println("处理数据4"+data4.getData());
        System.out.println("处理数据5"+data5.getData());
    }

}

//处理请求的类
class Host{

    public DataA execut(String name ,Integer id){
        System.out.println("处理请求start==>"+id+"===名字===>"+name);
        Future future = new Future();
        new Thread(){
            @Override
            public void run() {
                RealData realData = new RealData(name, id);
                future.setData(realData);
            }
        }.start();
        System.out.println("处理请求end==>"+id+"===名字===>"+name);
        return future;
    }
}


//统一真实和未来的数据
interface DataA{
    DataA getData();
}
//真实的数据
class RealData implements DataA{

    private String name;
    private Integer id;

    public RealData(String name, Integer id) {
        //表示耗时的操作
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name+"已处理";
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DataA getData(){
        return this;
    }

    @Override
    public String toString() {
        return "RealData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

//未来的数据
class Future implements DataA{
    private boolean real; //保证只赋值一次
    private DataA realData;

    //1.设置真是的Data
    public synchronized void setData(DataA data){
        if(real == true){
            return ;
        }

        realData = data;
        real=true;
        notifyAll();//唤醒等待队列
    }

    //2.获得真实的数据
    public synchronized  DataA getData(){
        if(real == false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData;
    }
}