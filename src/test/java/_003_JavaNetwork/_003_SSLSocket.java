package _003_JavaNetwork;

import org.junit.Test;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 安全链接的socket
 * @auther Dyy
 * @create 2018/3/9
 */
public class _003_SSLSocket {

    /**
     * 1.
     */
    @Test
    public void baseUse1() throws IOException {
        SocketFactory factory = SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) factory.createSocket("www.baidu.com", 80);

        //启动所有密码组
        String[] support = socket.getSupportedCipherSuites();
        socket.setEnabledCipherSuites(support);

        OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
        out.write("GET http://www.baidu.com/ HTTP/1.1\r\n");
        out.write("Host: www.baidu.com\r\n");
        out.write("\r\n");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s;
        while(!(s = in.readLine()).equals("")){
            System.out.println(s);
        }
        System.out.println();

        socket.close();

    }


    @Test
    public void  baseUse5(){
        boolean a= true;
        Boolean b = (Boolean) a;
        System.out.println(b);

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        BigDecimal aaa = new BigDecimal("0");
        String format = decimalFormat.format(aaa);
        System.out.println(format);

        int i = Integer.valueOf(1).compareTo(0);
        System.out.println(i);
    }
}
