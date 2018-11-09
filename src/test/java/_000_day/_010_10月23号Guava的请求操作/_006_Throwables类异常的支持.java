package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Throwables;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Test;

import java.util.List;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 15:15 2018/11/5
 */
public class _006_Throwables类异常的支持 {
    @Test
    public void baseUser(){
        try {
            b();
        }catch (Exception e){
            e.printStackTrace();
            String localizedMessage = e.getLocalizedMessage();
            System.out.println("");
            //1.获得异常链，最深的异常在集合的最里面
            List<Throwable> causalChain = Throwables.getCausalChain(e);

            //2.得到抛出异常的根异常
            Throwable rootCause = Throwables.getRootCause(e);

            //3.堆栈信息转换成字符串
            String stackTraceAsString = Throwables.getStackTraceAsString(e);

            //4.包装成RuntimeException进行

            System.out.println(stackTraceAsString);
        }
    }


    //1.异常链就是这个异常包含另外一个异常的信息 用老的异常构建新的异常
    public void b() throws MyException2 {
        try {
            f();
        }catch (Exception e){
            throw  new MyException2(e);
        }
    }

    public void f() throws MyException2 {
        try{
            g();
        }catch (Exception e){
            throw new MyException2(e);
        }
    }

    public void g() throws MyException1 {
        throw  new MyException1();
    }

}
class MyException1 extends Exception{ }

class MyException2 extends Exception{
    MyException2(Throwable e){
        super(e);
    }

    MyException2(){
        super();
    }
}