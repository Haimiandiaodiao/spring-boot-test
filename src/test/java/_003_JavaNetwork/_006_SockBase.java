package _003_JavaNetwork;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.*;
import java.nio.Buffer;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/5/28 15:52<br/>
 */
public class _006_SockBase {


    /**
     * 1.设置服务器的地址和客户端的地址
     *  构造Socket和ServerSocket的方法
     */
    @Test
    public void  baseUse1() throws IOException, InterruptedException {
//        Socket socket = new Socket();
        //设置Socket的连接地址和等待时间
//        InetSocketAddress localhost = new InetSocketAddress("localhost", 1050);
//        socket.connect(localhost,60000);
//【1】构造方法
//       new Socket(InetAddress address,int port);构建Socket的方法
//       new Socket(String host,int port);  主机名也就是域名，端口号

//【2】静态方法
//        InetAddress localHost = InetAddress.getLocalHost();
//        System.out.println(localHost);
//        InetAddress byName = InetAddress.getByName("220.181.111.188");
//        System.out.println(byName);
//        InetAddress byName1 = InetAddress.getByName("www.baidu.com");
//        System.out.println(byName1);
        /** 运行的结果
         * Dyy_/192.168.25.1
         * /220.181.111.188
         * www.baidu.com/220.181.111.188
         */

        //====================================================================
        //【3】设置本地的端口号和地址
//        new  Socket(InetAddress address, int port, InetAddress localAddr,int localPort);


        //【4】设置服务器进程拒接连接
        //第二个参数是设置服务器的连接请求队列的长度。如果队列中的连接请求已经满，服务器就会拒绝其余的连接请求

        ServerSocket serverSocket = new ServerSocket(8124, 50);
        Thread.sleep(360000);

    }


    /**
     * 1.1设置连接服务器的客户端 超过了服务器的连接请求队列的长度 就会报异常
     * 并且服务器响应客户端的请求也要另外开一个端口来进行 其实就是端口对端口的响应
     *
     *  TCP    0.0.0.0:8124           0.0.0.0:0              LISTENING       12384
     *  TCP    192.168.25.1:8124      192.168.25.1:9565      CLOSE_WAIT      12384
     *  TCP    192.168.25.1:8124      192.168.25.1:9566      CLOSE_WAIT      12384
     *  TCP    192.168.25.1:9565      192.168.25.1:8124      FIN_WAIT_2      13720
     *  TCP    192.168.25.1:9566      192.168.25.1:8124      FIN_WAIT_2      13720
     *  TCP    [::]:8124              [::]:0                 LISTENING       12384
     */
    @Test
    public void baseUse11() throws IOException {
/*        InetAddress localHost = InetAddress.getLocalHost();
        Socket socket = new Socket(localHost, 8124);
        System.out.println("first connection success");
        Socket socket1 = new Socket(localHost, 8124);
        System.out.println("seconde connection success");
        Socket socket2 = new Socket(localHost, 8124);
        System.out.println("thrid  connection success");*/
        //【2】设置连接Socket的连接等待时间
        InetAddress serverHost = InetAddress.getLocalHost();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(serverHost,8124);
        //检测连接超时的异常
        Socket socket = new Socket();
        socket.connect(inetSocketAddress,100000);

        //【3】获得Socket的相关信息
        /*
         * getInetAddress() 获得远程服务器的IP地址
         * getPort() 获得远程服务器的端口
         * getLocalAddress() 获得本地的
         * getLocalPort() 获得本地的
         * getInputStream() 获得输入流，如果没有连接或者关闭则出现异常
         * getOutputSteam()
         */

    }


    /**
     * 3.通过Socket来进行网页的请求
     * 使用ByteArrayOutputStream是不推荐的，因为其会暂用大量的内存
     */
    @Test
    public void baseUse3() throws Exception {
        InetAddress byName = InetAddress.getByName("47.104.206.145");
        InetSocketAddress inetSocketAddress = new InetSocketAddress(byName, 15672);
        Socket socket = new Socket();
        socket.setTcpNoDelay(true);
        socket.connect(inetSocketAddress,5000);

        StringBuffer sb = new StringBuffer();
        sb.append("GET / HTTP/1.1\r\n");
        sb.append("Host: 47.104.206.145:15672\r\n");
        sb.append("Connection: keep-alive\r\n");
        sb.append("User-Agent: Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36\r\n");
        sb.append("Cookie: m=2258:YWRtaW46cmNjbDEyMzMyMQ%253D%253D\r\n");
        sb.append("Accept-Language: zh-CN,zh;q=0.8\r\n\r\n");
        //发送请求
        System.out.println(sb.toString());
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(sb.toString().getBytes());


        //这里我们不关闭输出流
        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();
      /*  ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        while ((len=inputStream.read(buff)) !=-1){
            buffer.write(buff,0,len);
        }

        System.out.println(new String(buffer.toByteArray()));*/

      //【3.1】网页内容说输出升级
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String data;
        while ((data=bufferedReader.readLine()) != null){
            System.out.println(data);
        }


        socket.close();

    }


    /**
     * 4.Socket的设置
     * tcp_nodelay 表示立即发送数据
     *              默认下使用的是Negale算法。发送放发送的数据不会立即发出，而是先放在缓冲区
     *              等缓冲区满了之在发出。发送完这一批数据之后会等待接受对这一批数据的回应。然后再发送下一批。
     *              ===适合使用发送大批量数据，并且接收方会及时做出回应的。 这个是通过减少通讯次数来提高通信。
     *                由于采用了缓冲，大大降低了实时响应速度，导致客户程序运行的很慢。
     *                 setTcpNoDelay()false == 有缓冲   true没有缓冲
     *
     * so_resuseaddr 表示是否允许重用Socket所绑定的地址
     *
     *                   当接受方通过Socket的close方法时，如果网络上还有发送到这个Socket的数据，那么地城的Socket不会立刻释放本地端口
     *                   而是会等待接受数据。socket接受延时数据的目的是确保这些延迟数据不会被其他碰巧绑定到这恶鬼端口的新进程接受。
     *
     *                   为了确保服务器一个进程关闭Socket后，既使他的端口没有及时释放，同一个主机的其他进程还可以立即重用改端口。
     *                   必须要在Socket还没有绑定到一个端口的时候调用这个方法。
     * so_timeout 表示接受数据时的等待时间
     * so_linger 表示当执行Socket的close()方法时，是否立即关闭底层对的Socket
     * so_snfbuf 表示发送数据的缓冲区的大小
     * so_revbuf 表示接受数据的缓冲区大小
     * so_keepalive 表示对于长时间处于空闲状态的soceket 是否要自动关闭
     * oobinline 表示是否支持发送一个字节的Tcp紧急数据
     */
    @Test
    public void  baseUse4() throws IOException, InterruptedException {
    //发送端
        Socket s = new Socket("localhost", 9000);
        OutputStream out = s.getOutputStream();
        out.write("hello ".getBytes());
        out.write("everyone".getBytes());
        Thread.sleep(60000);
        s.close();
    }

    @Test
    public void baseUse5() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        Socket s = serverSocket.accept();
        InputStream in = s.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = -1;
        do{
             len = in.read(buff);
             if(len != -1 )buffer.write(buff,0,len);
        }while (len != -1);
            System.out.println(new String (buffer.toByteArray()));
    }
}
