package _003_JavaNetwork;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;

public class _002_ServerSocket {

    /**
     * 1.使用ServerSocket创建echo服务器
     */
    @Test
    public void baseUse1(){

    }

}

class EchoServer{
    public static int DEFAULT_PORT=7;

    public static void main(String[] args){
        int port;
        port = DEFAULT_PORT;
        System.out.println("监听连接端口:"+port);



    }
}