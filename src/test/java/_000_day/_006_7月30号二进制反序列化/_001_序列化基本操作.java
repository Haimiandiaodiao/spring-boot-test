package _000_day._006_7月30号二进制反序列化;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.dyy.Modul.Entity.Father;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/30 下午8:53<br/>
 */
public class _001_序列化基本操作 {

    @Test
    public void 对象序列化和反序列化() throws IOException, ClassNotFoundException {
        Father father = new Father();
        father.setAge(11);
        father.setName("Dyy");

        ByteArrayOutputStream buffer1 = new ByteArrayOutputStream(1024);

        ObjectOutputStream obj = new ObjectOutputStream(buffer1);
        obj.writeObject(father);

        byte[] bytes1 = buffer1.toByteArray();//debug查看内容

        //重新构造对象
        ByteArrayInputStream in = new ByteArrayInputStream(bytes1);
        ObjectInputStream objin = new ObjectInputStream(in);
        Father father1 = (Father) objin.readObject();
    }


    /**
     * Debug进行相应参数的查看
     * 注意 编译时的顺序和读取时的顺序必须是一致的
     *
     * 而且要 实现序列化接口 serializable接口
     * @throws IOException
     */
    @Test
    public void Session协议操作() throws IOException {
        Father father = new Father();
        father.setAge(11);
        father.setName("Dyy");

        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(bo);
        hessian2Output.startMessage();
        hessian2Output.writeObject(father);
        hessian2Output.writeString("OMG It's very carry");
        hessian2Output.completeMessage();
        hessian2Output.close();

        byte[] bytes = bo.toByteArray();
        System.out.println(Arrays.toString(bytes));


        //对序列化的内容进行反序列化的操作
        Hessian2Input in = new Hessian2Input(new ByteArrayInputStream(bytes));
        in.startMessage();
        Father father1 = (Father) in.readObject();//注意读取消息的顺序和写时的顺序必须时一致的才行
        String s = in.readString();
        in.completeMessage();
        System.out.println(father1);
        System.out.println(s);
    }





}
