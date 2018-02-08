package _002_ThirdProjectTestPackage._001_HttpComponents;

import org.apache.http.*;
import org.apache.http.entity.*;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.*;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * HttpClients 的使用
 * @auther Dyy
 * @create 2018/2/7
 */
public class _001_HttpComponents_01 {

    /**
     * 1.获得协议版本
     * http请求头包含请求行和一个
     */
    @Test
    public void baseUse1(){
        BasicHttpRequest get = new BasicHttpRequest("GET", "wwww.baidu.com", HttpVersion.HTTP_1_1);
        System.out.println("获得请求行中的方法==>"+get.getRequestLine().getMethod());//获得请求行
        System.out.println("获得请求的路径==>"+get.getRequestLine().getUri());
        System.out.println("获得协议版本号==>"+get.getProtocolVersion());
        System.out.println("获得请求===>"+get.toString());

    }

    /**
     * 2.获得响应体结构
     */
    @Test
    public void baseUse2(){
        BasicHttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "我擦擦成功了");
        System.out.println("响应的版本号==>"+response.getProtocolVersion());
        System.out.println("相应的状态码==>"+response.getStatusLine().getStatusCode());
        System.out.println("响应返回的信息==>"+response.getStatusLine().getReasonPhrase());
        System.out.println("获得响应==>"+response.toString());
    }


    /**
     * 3.Http消息的通用属性和方法
     */
    @Test
    public void baseUse3(){
        BasicHttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "ok");
        response.addHeader("Set-Cookie","c1=a;path=/;domain=localhost");
        response.addHeader("Set-Cookie","c2=b;path\"/\",c3=c;c3=c;domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        //1.获得的是指定头的数组
        Header[] headers = response.getHeaders("Set-Cookie");
        System.out.println(headers.length);
        //2.使用headerIterator获得迭代器
        HeaderIterator headerIterator = response.headerIterator("Set-Cookie");
    }

    /**
     * 4.使用Htttp实体
     */
    @Test
    public void baseUse4() throws IOException {
        StringEntity stringEntity = new StringEntity("important message", Consts.UTF_8);
        System.out.println("请求的内容类型-->"+stringEntity.getContentType());
        System.out.println("请求的内容的长度-->"+stringEntity.getContentLength());
        System.out.println(EntityUtils.toString(stringEntity));
        System.out.println(EntityUtils.toByteArray(stringEntity).length);
    }


    /**
     * 5.使用提供的请求实体
     */
    @Test
    public void baseUse5() throws Exception {
        //1.基础的可以设置输入流
        BasicHttpEntity myEntity = new BasicHttpEntity();
//      myEntity.setContent(input);

        //2.传输二进制数
        //自包含可以重复的实体
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(new byte[]{1, 2, 3}, ContentType.APPLICATION_OCTET_STREAM);

        //3.stringEntity是一个独立的可重复的实体重一个String对象中获得内容，
        StringEntity dyy = new StringEntity("Dyy",Consts.UTF_8.displayName());//三个参数的不推荐使用了

        //4.inputStreamEntity是一个流式的不可重复的实体，从输入流中获取其中的内容
        InputStream io = new FileInputStream("dd");
        InputStreamEntity inputStreamEntity = new InputStreamEntity(io);

        //5.FileEntity是一个独立的可重复的实体，从文件中获得其中的内容主要用于传输不容类型的大文件
        FileEntity fileEntity = new FileEntity(new File(""), ContentType.APPLICATION_ATOM_XML);
        //6.HttpEntityWrapper这个是创建包装实体的基类。
        //7.BufferedHttpEntity 是HttpEntityWrapper通过另外一个实体来构建它，并将其缓存在内存中。可以从一个不可重复的实体构建一个可以重复的实体

    }

    /**
     * 6.拦截器的适用
     */
    @Test
    public void baseUse6() throws IOException, HttpException {
        HttpProcessor build = HttpProcessorBuilder.create()
                .add(new RequestContent())//必须的协议拦截器
                .add(new RequestTargetHost())//拦截器是客户端协议处理器所必需的
                .add(new RequestConnControl())//这对于管理HTTP/1.0连接的持久性是非常重要的
                .add(new RequestUserAgent("MyAgent -HTTP / 1.1"))//设置用户代理
                .add(new RequestExpectContinue(true))
                .build();

        //创建请求的内容
        HttpCoreContext context = HttpCoreContext.create();
        //创建请求地址
        BasicHttpRequest get = new BasicHttpRequest("GET", "www.baidu.com", HttpVersion.HTTP_1_1);
        //通过拦截器来处理请求
        build.process(get,context);
    }


    /**
     * 7.创建Http服务端程序
     */
    @Test
    public void baseUse7() throws IOException, HttpException {
        HttpProcessor build = HttpProcessorBuilder.create()
                .add(new ResponseDate())
                .add(new ResponseServer("MyAgent -HTTP / 1.1 "))
                .add(new ResponseContent())
                .add(new ResponseConnControl())
                .build();

        //http请求处理器
        HttpRequestHandler httpRequestHandler = new HttpRequestHandler() {
            @Override
            public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
                response.setStatusCode(HttpStatus.SC_OK);
                response.setEntity(new StringEntity("some important message", ContentType.TEXT_PLAIN));
            }
        };


        //映射器
        UriHttpRequestHandlerMapper handlerMappe = new UriHttpRequestHandlerMapper();
        handlerMappe.register("*",httpRequestHandler);
        BasicHttpRequest get = new BasicHttpRequest("GET", "/");


        HttpRequestExecutor httpRequestExecutor = new HttpRequestExecutor();
        HttpCoreContext httpCoreContext = HttpCoreContext.create();

        httpRequestExecutor.preProcess(get,build,httpCoreContext);
//        httpRequestExecutor.execute(get,)

        HttpService httpService = new HttpService(build, handlerMappe);
    }

}
