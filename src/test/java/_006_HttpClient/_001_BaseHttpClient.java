package _006_HttpClient;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class _001_BaseHttpClient {

    /**
     * 1.最简单的请求执行过程
     */
    @Test
    public void baseUse1() throws IOException {
        //生成一个默认的客户端
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //生成一个Get请求给客户端使用
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //客户端发送get请求
        CloseableHttpResponse response = httpclient.execute(httpGet);
        HttpClientContext httpClientContext = HttpClientContext.create();
    }

    /**
     * 2.URI的创建
     */
    @Test
    public void baseUse2() throws URISyntaxException {
        HttpGet httpGet = new HttpGet("http://www.baidu.com/search?h1=ne&b2=df");
        //URIBuilder简化操作
        URI build = new URIBuilder().setScheme("HTTP")
                .setHost("www.baidu.com")
                .setPath("/search")
                .setParameter("h1", "ss")
                .setParameter("b2", "dd")
                .build();
    }

    /**
     * 3.Http响应
     */
    @Test
    public void baseUse3(){
        BasicHttpResponse res = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK,"已经Ok");
        System.out.println(res.getProtocolVersion());
        System.out.println(res.getStatusLine().getStatusCode());
        System.out.println(res.getStatusLine().getProtocolVersion());
        //得到响应头上的信息
        System.out.println(res.getStatusLine().getReasonPhrase());
        System.out.println(res.getStatusLine().toString());
    }

    /**
     * 4.处理响应头的操作
     */
    @Test
    public void baseUse4(){
        BasicHttpResponse res = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        res.addHeader("Set-Cookie","c1=a; path=/; domain=locahost");
        res.addHeader("Set-Cookie","c2=b; path=\"/\", c3=a; domain=\"locahost\"");
//        Header h1 = res.getFirstHeader("Set-Cookie");
//        System.out.println(h1);
//        Header h2 = res.getLastHeader("set-Cookie");
//        System.out.println(h2);
//        System.out.println(res.toString());
        //不区分大小写
        HeaderIterator headerIterator = res.headerIterator("set-cookie");
        while (headerIterator.hasNext()){
            HeaderElement[] elements = headerIterator.nextHeader().getElements();
            for (HeaderElement element : elements) {
                //得到第一个分号之后的数据
                System.out.println(element.getName()+"===>"+element.getValue());
                //得到除了第一个之外的其他数据
                NameValuePair[] params = element.getParameters();
                for (int i = 0; i < params.length; i++) {
                    System.out.println(" "+params[i]);
                }
            }
        }
    }

    /**
     * 5.Http实体
     */
    @Test
    public void baseUse5() throws IOException {
        StringEntity entity = new StringEntity("important message", ContentType.create("text/plain", "UTF-8"));
        System.out.println(entity.getContentType());
        System.out.println(entity.getContentLength());
        System.out.println(EntityUtils.toString(entity));
        System.out.println(EntityUtils.toByteArray(entity).length);
    }

    /**
     * 6.通过文件来生成实体类
     */
    @Test
    public void baseUse6() throws IOException {
        File file = new File("test.txt");
        HttpEntity inputStreamEntity = new InputStreamEntity(new FileInputStream(file));
        inputStreamEntity = new BufferedHttpEntity(inputStreamEntity);
        System.out.println(EntityUtils.toString(inputStreamEntity));
        System.out.println(EntityUtils.toString(inputStreamEntity));
        System.out.println(inputStreamEntity.getContentLength()+"===>"+inputStreamEntity.getContentEncoding());
    }

    /**
     * 8.表单提交
     */
    @Test
    public void baseUse8() throws IOException {
        ArrayList<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("param1","value1"));
        formParams.add(new BasicNameValuePair("param2","value2"));
        UrlEncodedFormEntity enrity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
        System.out.println(EntityUtils.toString(enrity));

    }

    /**
     * 9.执行上下文
     */
    @Test
    public void baseUse9(){
        HttpClientContext cont = HttpClientContext.create();

        HttpHost target = cont.getTargetHost();
        HttpRequest request = cont.getRequest();
        HttpResponse response = cont.getResponse();
        RequestConfig config = cont.getRequestConfig();
        HttpRequestRetryHandler standardHttpRequestRetryHandler = new StandardHttpRequestRetryHandler();
        System.out.println(target);
        System.out.println(request);
        System.out.println(response);
        System.out.println(config);
    }

    /**
     * 9.连接管理器
     */
    @Test
    public void baseUse10() throws InterruptedException, ExecutionException, IOException {
        HttpClientContext context = HttpClientContext.create();
        BasicHttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();
        HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));
        ConnectionRequest connRequest = connMrg.requestConnection(route, null);
        HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);

        if(!conn.isOpen()){
            connMrg.connect(conn,route,1000,context);
            connMrg.routeComplete(conn,route,context);
        }
    }
}
