package _003_JavaNetwork;

import lombok.Data;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther Dyy
 * @create 2018/3/8
 */
public class _002_ServerSocket {

    /**
     * 1.文件服务器
     */
    @Test
    public void baseUse1(){
        byte[] bytes = ("adfdsd\r\n").getBytes();
        String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor("abc.png");
        SingleFileHTTPServer server = new SingleFileHTTPServer(bytes, "US-ASCII", contentTypeFor, 8123);
        server.start();
    }

    @Test
    public void baseUse2() throws Exception{
        ServerSocket svrSocket = new ServerSocket(8089);
        while(true){
            Socket socket = svrSocket.accept();
            //足够大的一个缓冲区
            InputStream in = socket.getInputStream();
            int read = in.read();
            if(read != -1) {
                //跑去嗅探测试链接 option请求
                BufferedInputStream bf = new BufferedInputStream(in);
                BufferedReader bw = new BufferedReader(new InputStreamReader(bf));
                String s = (char)read+bw.readLine();
                System.out.println(s);
                String tmp;
                int contentLength = 0;
                while ((tmp = bw.readLine()) != null){
                    if (tmp.equals("")){
                       //找到请求空行
                        System.out.println();
                        char[] datetmp = new char[contentLength];
                        int read1 = bw.read(datetmp);
                        System.out.println(new String(datetmp));
                        break;
                    }
                    //找到请求体的长度
                    int position;

                    if((position = tmp.toUpperCase().indexOf("LENGTH")) != -1){
                        //得到长度相应的长度
                        String substring = tmp.substring(position + 7, tmp.length()).trim();
                        contentLength = Integer.parseInt(substring);
                        //读取请求内容
                    }
                    System.out.println(tmp);
                }

                OutputStream outputStream = socket.getOutputStream();
                String header = "HTTP/1.0 200 OK\r\n"+
                        "Server: OneFile 2.0\r\n"+
                        "Content-length: " + 10+ "\r\n"+
                        "Content-type:application/json " + "; charst=utf-8"+"\r\n"+
                        "Host-name: Dyy\r\n\r\n{dddd:dsd}";
                outputStream.write(header.getBytes());
                socket.close();
            }
        }
    }
}





/**
 * 单文件服务器
 */
@Data
class SingleFileHTTPServer{

    private byte[] content;
    private byte[] header;
    private int port;
    private String encoding;

    public SingleFileHTTPServer(String data , String encoding,String mimeType,int port) throws UnsupportedEncodingException {
        this(data.getBytes(encoding),encoding,mimeType,port);

    }

    public SingleFileHTTPServer(byte[] data,String encoding,String mimeType,int port){
        this.content = data;
        this.port = port;
        this.encoding = encoding;
        String header = "HTTP/1.0 200 OK\r\n"+
                "Server: OneFile 2.0\r\n"+
                "Content-length: " + this.content.length + "\r\n"+
                "Content-type: "+mimeType + "; charst="+encoding +"\r\n"+
                "Host-name: Dyy\r\n\r\n";
        this.header = header.getBytes(Charset.forName("US-ASCII"));
    }

    public void start(){
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try(ServerSocket server = new ServerSocket(this.port)){
            while(true){
                Socket connection = server.accept();
                pool.submit(new HTTTPHandler(connection));
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }



    class HTTTPHandler implements Callable<Void>{
        private Socket connection;

        public HTTTPHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws Exception {
        try(OutputStream out =  connection.getOutputStream();
            InputStream in = connection.getInputStream();
            ){
//            StringBuffer request = new StringBuffer(80);
            while(true){
                int c = in.read();
                System.out.print(c);
//                request.append((char) c);
            }
            //System.out.println("请求的参数==>"+request);
            //如果是HTTP1.0之后的版本 则发送一个MIME首部
//            if(request.toString().indexOf("HTTP/") != -1){
//                out.write(header);
//            }
//            out.write(1);
//            out.flush();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            content.clone();
        }
            return null;
        }
    }
}
//====================================================功能完备的HTTP服务器=============================
class JHTTP{
    private File rootDirectory;
    private int port;


}