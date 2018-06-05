package _005_Thread;

import com.sun.corba.se.impl.orbutil.closure.Future;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class _006_JDKWithFuture {

    /**
     * 和_005_Future是一样的只不过是JDK给提供了Futer的实现
     */
    @Test
    public void baseUse1(){
        //1.创建请求1，2，3，4，5
        //1.1创建处理器
        HostB host = new HostB();
        FutureDataB data1 = host.requestExectu("Dyy请求", 1);
        FutureDataB data2 = host.requestExectu("WD请求", 2);
        FutureDataB data3 = host.requestExectu("LCH请求", 3);
        FutureDataB data4 = host.requestExectu("ZKC请求", 4);
        FutureDataB data5 = host.requestExectu("SNN请求", 5);

        //主线程会阻塞因为还没有处理完数据。
        System.out.println("处理数据1"+data1.getData());
        System.out.println("处理数据2"+data2.getData());
        System.out.println("处理数据3"+data3.getData());
        System.out.println("处理数据4"+data4.getData());
        System.out.println("处理数据5"+data5.getData());
    }
}





class HostB{

    public FutureDataB requestExectu(String name , Integer Id){
        System.out.println("处理请求start==>"+Id+"===名字===>"+name);
        FutureDataB future = new FutureDataB(new Callable<DataB>() {
            @Override
            public DataB call() throws Exception {
                return new RealDataB(name, Id);
            }
        });
        //启动一个线程用于事例化对象
        new Thread(future).start();
        System.out.println("处理请求end==>"+Id+"===名字===>"+name);
        return future;
    }
}






interface DataB{
    DataB getData();
}

class RealDataB implements  DataB{


    private String name;
    private Integer id;

    public RealDataB(String name, Integer id) {
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


    @Override
    public String toString() {
        return "RealData{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public DataB getData() {
        return this;
    }
}

class FutureDataB extends FutureTask<DataB> implements  DataB{

    private DataB realData;

    public FutureDataB(Callable<DataB> callable) {
        super(callable);
    }

    // 其中的get方法是FutureTask所提供的
    @Override
    public DataB getData() {
        DataB dataB= null;
        try {
            dataB = get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dataB;
    }
}