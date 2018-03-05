package _003_JavaNetwork;

import org.junit.Test;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 网络地址
 * @auther Dyy
 * @create 2018/2/8
 */
public class _001_InetAddress {

    /**
     * 1.获得网络地址描述
     */
    @Test
    public void baseUse1() throws UnknownHostException {
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        System.out.println(byName);
        InetAddress byName1 = InetAddress.getByName("61.135.169.121");
        System.out.println(byName1.getCanonicalHostName());

        //得到所有的地址
        InetAddress[] allByName = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress tmp : allByName) {
            System.out.println(tmp);
        }

        //返回一个本地主机的地址
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

        //根据数组来构建地址
        byte[] address ={(byte)192,(byte)168,11,1};
        InetAddress byAddress = InetAddress.getByAddress(address);
        System.out.println(byAddress.getAddress().length);
    }


    /**
     * 2.测试网络连接
     */
    @Test
    public void baseUse2() throws IOException {
//        InetAddress byName = InetAddress.getByName("192.168.11.1");
//        NetworkInterface byInetAddress = NetworkInterface.getByInetAddress(byName);
//        System.out.println(byInetAddress);


//        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
//        while (networkInterfaces.hasMoreElements()){
//            System.out.println(networkInterfaces.nextElement());
//        }

        NetworkInterface wlan0 = NetworkInterface.getByName("wlan0");
        System.out.println(wlan0.getName());
        System.out.println(wlan0.getDisplayName());
        Enumeration<InetAddress> inetAddresses = wlan0.getInetAddresses();
        while (inetAddresses.hasMoreElements()){
            System.out.println(inetAddresses.nextElement());
            System.out.println(inetAddresses.nextElement().getHostName());
        }
    }

    /**
     * 3.URL的构造函数
     */
    @Test
    public void baseUse3() throws IOException {
        URL url = new URL("http://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png");
        InputStream inputStream = url.openStream();
        BufferedReader bufferedWriter = new BufferedReader(new InputStreamReader(inputStream));
        String tmp ;
        while (null != (tmp = bufferedWriter.readLine())){
            System.out.println(tmp);
        }
    }

    /**
     * 4.将资源保存为文件
     */
    @Test
    public void baseUse4() throws IOException {
        URL url = new URL("http://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png");
        InputStream inputStream = url.openStream();

        FileOutputStream fileOutputStream = new FileOutputStream("baidu.png");

        byte[] tmp = new byte[1024];
        while (inputStream.read(tmp) != -1){
            fileOutputStream.write(tmp);
        }
    }


    /**
     * 5.获得一个URL的基本的信息
     */
    @Test
    public void baseUse5() throws Exception {
        URL url = new URL("http://map2:mamp3@www.baidu.com/a/b?name=%e5%8a%a8&age=11#%e9%94%9a%e7%82%b9锚点");

        //得到协议
        System.out.println("协议:"+url.getProtocol());
        //得到主机
        System.out.println("主机："+url.getHost());
        //得到端口没有明确指定的情况下返回   -1
        System.out.println("端口："+url.getPort());
        //得到默认的端口  没有指定端口时返回的是  默认的端口
        System.out.println("默认端口："+url.getDefaultPort());
        //得到文件  从主机后的第一个/ 一直到#号的部分都被认定为文件部分   注意 注意
        System.out.println("文件："+url.getFile());
        //得到路径  和getFile返回的是相同的 区别是不包含 查询的字符串只有路径
        System.out.println("路径："+url.getPath());
        //得到相对路径 返回的是#后面的 也就是锚点
        System.out.println("相对路径："+url.getRef());
        //得到查询参数
        System.out.println("查询参数"+url.getQuery());
        //得到用户信息包含用户名和密码 这里是map2:mamp3
        System.out.println("用户信息："+url.getUserInfo());
        //得到资源的授权机构 只包含用户信息和主机信息   map2:mamp3@www.baidu.com
        System.out.println("认证信息："+url.getAuthority());


    }

    /**
     * 6.网址的比较
     */
    @Test
    public void baseUse6() throws Exception {
        //URL对象的equals方法和hashCode方法
        //equals方法会尝试调用DNS解析主机来，判断两个主机是否是相同的
        //注意：eqauls是阻塞的操作，所以我们应当尽量避免将URL存储在依赖equals方法的数据结构中，如HashMap
        //更好的选择是使用URI来进行比较
        URL url = new URL("http://www.baidu.com");
        URL url1 = new URL("http://baidu.com");
        if(url.equals(url1)){
            System.out.println("same");
        }else{
            System.out.println("No Same");
        }

        //toString实际上就是调用的toExternalForm这个方法
        System.out.println(url.toExternalForm());
        //toURI将URL转换成URI的操作
        URI uri = url.toURI();
        URL url2 = uri.toURL();
    }


    //===========================使用URI==================
    /**
     * 7.URI和URL的区别
     * [1]URI完全用于关于资源的标识和URI的解析，没有提供访问URI标识资源的方法
     * [2]URI与相关的规范更一致
     * [3]URI对象可以表示成相对URI。URL类在存储URI之前会将其绝对化
     * URI纯粹用于解析和处理字符串
     *
     */
    @Test
    public void baseUse7() throws Exception {
        //1.构造URI对象
        URI uri = new URI("http://%e6%a0%b7:mamp3@www.baidu.com/a/b?name=%e5%8a%a8&age=11#%e9%94%9a%e7%82%b9锚点");
        URI uri1 = URI.create("http://map2:mamp3@www.baidu.com/a/b?name=%e5%8a%a8&age=11#锚点");

        System.out.println("1.协议类型："+uri.getScheme());
        System.out.println("2.会对路径进行解码特殊的路径："+uri.getSchemeSpecificPart());
        System.out.println("3.不会进行解码的路径："+uri.getRawSchemeSpecificPart());
        System.out.println("4.解码后获得锚点的方法: "+uri.getFragment());
        System.out.println("5.不解码获得锚点的方法："+uri.getRawFragment());
        System.out.println("6.是否："+uri.isAbsolute());
        System.out.println("7.是否是透明的："+uri.isOpaque());
        System.out.println("8.得到编码之后的认证信息："+uri.getAuthority());
        System.out.println("9.得到没有编码之后的认证信息"+uri.getRawAuthority());
        System.out.println("10.得到主机地址："+uri.getHost());
        System.out.println("11.得到端口："+uri.getPort());//没有明确指出的话就是-1
        System.out.println("11.1得到默认的端口："+uri.toURL().getDefaultPort());
        System.out.println("12.得到路径："+uri.getPath());
        System.out.println("13.得到编码之后的参数："+uri.getQuery());
        System.out.println("14.得到没有编码的参数:"+uri.getRawQuery());
//        System.out.println(""+uri.parseServerAuthority());

    }

    /**
     * 8.URI的一些基本的操作
     */
    @Test
    public void baseUse8() throws URISyntaxException, UnsupportedEncodingException {

        //#1.注意主地址后面必须接/  这样才证明还有后面的地址
        // 并且相对的地址不是以/ 开头的地址
        URI absolute = new URI("www.baidu.com/");
        URI relative = new URI("image/eva");
        URI resolve = absolute.resolve(relative);
        System.out.println("1.使用相对地址："+resolve);


        //2.有绝对的URI地址变为相对的URI地址
        URI uri = new URI("http://www.example.com/image/logo.png");
        URI uri1 = new URI("http://www.example.com/");
        //得到相对的地址
        URI relativize = uri1.relativize(uri);
        System.out.println(relativize);

        //3.将URI地址转换为字符串 toString返回的是为编码的字符串形式 和 toASCIIString返回的是已经编码的字符产形式
        URI uri2 = new URI("http://www.baidu.com/imgae/我滴天.png");
        System.out.println("未编码的字符串："+uri2.toString());
        System.out.println("编码的字符串："+uri2.toASCIIString());

        //4.发明Web的时候Unicode还没有完全的普及，所以URL中的字符必须来自ASCII的一个固定的子集
        //4.1 大小写字母
        //4.2 数字
        //4.3 标点符号 -_.!~*;",
        //4.4 特殊用途的字符 / & ? @ # ; $ + = 和 %

        //4 使用URLncoder来进行转码
        String encode = URLEncoder.encode("-_!~,.;'你好", "utf-8");//编码除了字母下划线和折现
        System.out.println("编码之后的字符串："+encode);
        String decode = URLDecoder.decode(encode, "UTF-8");
        System.out.println("解码之后的字符串："+decode);
    }

    /**
     * 9.http的使用
     */
    @Test
    public void baseUse9() throws IOException {
        //1.请求头中的accept包含了可以接受的媒体类型
        //2.connection：Keep-alive  希望一个tcp链接上连续发送过个请求
        URL url = new URL("http://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png");
        URL url1 = new URL("http://www.baidu.com");

        URLConnection con = url.openConnection();
        con.setRequestProperty("UserId","Dyy");
        Map<String, List<String>> requestProperties = con.getRequestProperties();
        Set<Map.Entry<String, List<String>>> entries1 = requestProperties.entrySet();
        for (Map.Entry<String, List<String>> entry : entries1) {
            System.out.println(entry.getKey()+"====>>"+entry.getValue());
        }


        String contentType = con.getContentType();
        System.out.println("请求数据的类型:"+contentType);
        int contentLength = con.getContentLength();
        long contentLengthLong = con.getContentLengthLong();
        System.out.println("请求的数据的长度："+contentLength+"=="+contentLengthLong);
        System.out.println("请求的编码："+con.getContentEncoding());
        System.out.println("发送的时间："+con.getDate());
        System.out.println("获得文档的过期时间："+con.getExpiration());
        System.out.println("获得文档的最后修改时间："+con.getLastModified());

        String headerField = con.getHeaderField("content-type");
        System.out.println("获得任意的字段"+headerField);
        long date = con.getHeaderFieldDate("date", 0);
        System.out.println("任意字段的发送时间："+date);

//        Map<String, List<String>> headerFields = con.getHeaderFields();
//        Set<Map.Entry<String, List<String>>> entries = headerFields.entrySet();
//        for (Map.Entry<String, List<String>> entry : entries) {
//            System.out.println(entry.getKey()+"====>"+entry.getValue());
//        }
        String s = URLConnection.guessContentTypeFromName(con.getContentType());
        String s1 = URLConnection.guessContentTypeFromStream(con.getInputStream());
        System.out.println("猜测的文件的类型："+s+"==="+s1);
    }
    /**
     * 10.httpURLConnection的使用
     */
    @Test
    public void baseUse10(){
//        URL url = new URL("http://www.ibiblio.org/xml");
    }

}
