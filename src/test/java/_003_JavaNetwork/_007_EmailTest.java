package _003_JavaNetwork;

import org.junit.Test;

import java.io.*;
import java.lang.annotation.Target;
import java.net.InetAddress;
import java.net.Socket;

public class _007_EmailTest {


    @Test
    public void baseUse1() throws Exception{
         String smtpServer = "smtp.sina.com";
         int port =25;
        Message message = new Message("dongyang_happy@sina.com", "dongyang_happy@sina.com", "发给自己的", "这个是Dyy发给自己的文件");


        Socket socket = new Socket(smtpServer, 25);
        InputStream inputStream = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(outputStream, true);

        System.out.println(br.readLine());

        //1.发送身份校验账户 有回传信息
        pw.println("auth login");
        System.out.println(br.readLine());
        //2.密码  有回传信息
        pw.println("ZG9uZ3lhbmdfaGFwcHlAc2luYS5jb20=");
        System.out.println(br.readLine());
        //3.发送给谁 有回传信息
        pw.println("ZG9uZzU3MzMzMjQ1MA==");
        System.out.println(br.readLine());

        for (int i = 0; i < 20; i++) {

        //4.接受者是谁 有回传信息
        pw.println("mail from:<"+message.getFrom()+">");
        System.out.println(br.readLine());
        //5.具体消息  有回传信息
        pw.println("rcpt to:<"+message.getTo()+">");
        System.out.println(br.readLine());
        //6.  data
        //6. from
        //7. to
        pw.println("data");
        System.out.println(br.readLine());
        pw.println("From:"+message.getFrom());
        pw.println("To:"+message.getTo());
        pw.println("Subject:test"+i);

        //8.结尾回车.回车 结尾 有回传指
        pw.println();
        pw.println("test");
        pw.println("abcdefghijklmn\r\n123456");

        pw.println();
        pw.println("\r\n.\r\n");
        pw.println();

        System.out.println(br.readLine());
        System.out.println(br.readLine());
        }
    }

}

class Message{
    private String from;
    private String to;
    private String subject;
    private String content;
    private String data;


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Message(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.data = "Subject:"+subject+"\r\n"+content;
    }
}

