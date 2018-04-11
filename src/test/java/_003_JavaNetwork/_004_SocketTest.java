package _003_JavaNetwork;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class _004_SocketTest {

    @Test
    public void baseUser1() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8000, 2);//设置请求队列的长度
//        Thread.sleep(360000);
        Socket accept = serverSocket.accept();
        System.out.println(accept);
        Socket accept1 = serverSocket.accept();
        System.out.println(accept1);
    }
    
    @Test
    public void baseUse2() throws IOException {
        Socket localhost1 = new Socket("localhost", 8000);
        System.out.println("第一次连接成功");
        Socket localhost2 = new Socket("localhost", 8000);
        System.out.println("第二次连接成功");
        Socket localhost3 = new Socket("localhost", 8000);
        System.out.println("第三次连接成功");
    }


    /**
     * 3.获得Http请求的
     *
     */
//    GET / HTTP/1.1
//    Host: www.baidu.com
//    Connection: keep-alive
//    Pragma: no-cache
//    Cache-Control: no-cache
//    Upgrade-Insecure-Requests: 1
//    User-Agent: Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
//    Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
//    Accept-Encoding: gzip, deflate, sdch, br
//    Accept-Language: zh-CN,zh;q=0.8

    @Test
    public void baseUse3() throws IOException {
        String host = "hc.apache.org";
        int port = 80;

        StringBuffer sb = new StringBuffer();
        sb.append("GET / HTTP/1.1\r\n");
        sb.append("Host: www.baidu.com\r\n");
        sb.append("Accept: */*\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("\r\n");

        //创建相应的Socket
        Socket socket = new Socket(host, port);

        //发送HTTP请求
        OutputStream out = socket.getOutputStream();
        out.write(sb.toString().getBytes());
        socket.shutdownOutput();

        //接受相应结果
        InputStream in = socket.getInputStream();
        //数据量很大的情况下都不推荐使用byteArrayOutputSteam()
//        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        byte[] buff = new byte[1024];
//        int len = -1;
//        while((len = in.read(buff)) != -1){
//            buffer.write(buff,0,len);
//        }
//        System.out.println(new String(buffer.toByteArray()));

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String data;
        while ((data = br.readLine()) != null) {
            System.out.println(data);
        }
        socket.close();
    }
}
