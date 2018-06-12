package _005_Thread;


import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Active-Object 接受异步消息的主动对象
 */
public class _010_Active_ObjectTest {


    @Test
    public void baseUse1(){
        String word = "spring $50  world";

        Pattern pattern = Pattern.compile(".*(?<=\\$)\\d+.*");
        Matcher matcher = pattern.matcher(word);
        System.out.println(matcher.matches());
    }

}


/**
 * 1。结果类
 */
abstract class Result<T>{
    abstract T getResultValue();
}

/**
 * 1.2 异步的结果
 */
class FutureResult<T> extends  Result<T>{
    private Result<T> result;
    private boolean realy;

    public synchronized void setResult(Result<T> result){
        if(realy){
            return;
        }
        this.result = result;
        this.realy = true;
        notifyAll();

    }

    @Override
   public synchronized T getResultValue() {
        if (!realy){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result.getResultValue();

    }
}

/**
 * 1.3 真实的数据
 */
class RealResult<T> extends Result<T>{

    private final T resutValue;

    public RealResult(T resutValue) {
        this.resutValue = resutValue;
    }

    @Override
     public  T getResultValue() {
        return null;
    }
}


/**
 * 2.制作和现实数据的接口
 */
interface  ActiveObject{
    Result<String> makeResult(int count, char filter);
    void displayString(String string);
}

/**
 * 2.1 直接类
 */
class Servant implements ActiveObject{
    @Override
    public Result<String> makeResult(int count, char filter) {
        char[] chars = new char[count];
        for (int i = 0; i < count; i++) {
            chars[i] = filter;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult<String>(new String(chars));

    }

    @Override
    public void displayString(String string) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(string);
    }

}

/**
 * 2.2代理的实现 TODO
 */

class Proxy implements ActiveObject{

    @Override
    public Result<String> makeResult(int count, char filter) {
        return null;
    }

    @Override
    public void displayString(String string) {

    }
}

/**
 * 3.主动消费方
 */
class SchulerThread extends Thread{
    private ArrayBlockingQueue queue;
}


/**
 * 4.请求体
 */
abstract class MethodRequest{

}