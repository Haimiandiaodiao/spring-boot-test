package _000_day._004_7月20号安全认证;

import com.dyy.Modul.Entity.Father;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import sun.security.ssl.ProtocolVersion;

import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/2 下午10:31<br/>
 */
public class _002_自己理解安全体系 {

    @Test
    public void baseUse1(){
        Provider[] providers = Security.getProviders();
        for (Provider provider : providers) {
            System.out.println(provider.getName()+"===>"+provider.getVersion());
        }

        //追加一个自定义的provider通过代码的方式或者是通过/Java_Home/java.security
        Provider my = new Provider("Dyy", 1.0, "6666") {
        };
        Security.addProvider(my);
        Security.insertProviderAt(my,0);
        System.out.println("===================");
        Provider[] providers2 = Security.getProviders();
        for (Provider provider : providers2) {
            System.out.println(provider.getName()+"===>"+provider.getVersion());
        }
        System.out.println("====>"+Security.getProvider("Dyy"));
    }

    @Test
    public void baseUse2() throws NoSuchAlgorithmException, IOException {
        byte[] bytes = "MD5 Digest".getBytes();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        MessageDigest sha = MessageDigest.getInstance("SHA");
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        md5.update(bytes);
        sha.update(bytes);
        sha256.update(bytes);
        System.out.println(new String(md5.digest()));
        System.out.println(new String(sha.digest()));
        System.out.println(new String(sha256.digest()));
    }


    @Test
    public void 密钥对的生成和重构() throws Exception {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(1024);//设置秘钥的长短
        KeyPair keyPair = rsa.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();


        //根据二进制的生成公钥
        X509EncodedKeySpec publicKey = new X509EncodedKeySpec(aPublic.getEncoded());
        PKCS8EncodedKeySpec privateKey = new PKCS8EncodedKeySpec(aPrivate.getEncoded());

        KeyFactory rsa1 = KeyFactory.getInstance("RSA");
        PrivateKey privateKey1 = rsa1.generatePrivate(privateKey);

        PublicKey publicKey1 = rsa1.generatePublic(publicKey);


        System.out.println("私钥1==》"+ Base64.encodeBase64String(aPrivate.getEncoded()));
        System.out.println("私钥2==》"+ Base64.encodeBase64String(privateKey1.getEncoded()));
        System.out.println("公钥1==》"+Base64.encodeBase64String(aPublic.getEncoded()));
        System.out.println("公钥2==》"+Base64.encodeBase64String(publicKey1.getEncoded()));
        System.out.println("类型==>"+aPublic.getAlgorithm());
        System.out.println("提供者==》"+rsa.getProvider());


        //查看安全控件中第一个是谁提供的
        Provider[] providers = Security.getProviders();
        System.out.println("第一个提供者"+providers[0]);
        for (Provider provider : providers) {
            System.out.println(provider);
        }

        //============sig===进行加签操作和验签操作
        Signature sig = Signature.getInstance("SHA512withRSA");
        byte[] infoByte = "It's my message".getBytes();

        sig.initSign(privateKey1);//设置私钥  都是私钥加签公钥验签证
        sig.update(infoByte);
        //===加签
        byte[] sigMessage = sig.sign();


        //===验签
        Signature sig2 = Signature.getInstance("SHA512withRSA");
        sig2.initVerify(publicKey1);
        infoByte[0]=1;
        sig2.update(infoByte);
        boolean verify = sig2.verify(sigMessage);
        System.out.println("______________>验证"+verify);//验证为false的



    }


    @Test
    public void DigestInputStream数据流输入() throws NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException {
        KeyManagerFactory sunX509 = KeyManagerFactory.getInstance("SunX509");
        KeyStore jks = KeyStore.getInstance("JKS");
        jks.load(new FileInputStream("/Users/dyy/Documents/MyZone/aKey111.keystore"),"123456".toCharArray());

        KeyManagerFactory sunX5091 = KeyManagerFactory.getInstance("SunX509");
        sunX5091.init(jks,"123456".toCharArray());
        KeyManager[] keyManagers = sunX5091.getKeyManagers();

        TrustManagerFactory sunX5092 = TrustManagerFactory.getInstance("SunX509");
        sunX5092.init(jks);
        TrustManager[] trustManagers = sunX5092.getTrustManagers();


        Certificate baidu = jks.getCertificate("baidu");

        SocketFactory d = SSLSocketFactory.getDefault();
    }


    @Test
    public void 获得百度的Https链接证书() throws Exception {
        KeyStore jks = KeyStore.getInstance("JKS");
        FileInputStream fileInputStream = new FileInputStream("/Users/dyy/Documents/MyZone/aKey.keystore");
        jks.load(fileInputStream,"123456".toCharArray());
        fileInputStream.close();
        SocketFactory aDefault = SSLSocketFactory.getDefault();
        SSLSocket socket = (SSLSocket) aDefault.createSocket("www.baidu.com", 443);
        socket.startHandshake();
        SSLSession session = socket.getSession();
        Certificate[] peerCertificates = session.getPeerCertificates();
        jks.setCertificateEntry("baidu",peerCertificates[0]);

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/dyy/Documents/MyZone/aKey111.keystore");
        jks.store(fileOutputStream,"123456".toCharArray());
        fileInputStream.close();
    }


    @Test
    public void 构建SSlContext上下文() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext sun = SSLContext.getInstance("TLSv1.2");
    X509TrustManager myTrust = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    };
        sun.init(null,new TrustManager[]{myTrust},null);

    String protocol = sun.getProtocol();
    Provider provider = sun.getProvider();
        System.out.println("==>协议："+protocol);
        System.out.println("==>提供者"+provider.getName());
    //===================================看一下默认提供的是什么
    SSLContext aDefault = SSLContext.getDefault();
        System.out.println("==>默认协议："+aDefault.getProtocol());
        System.out.println("==>默认提供者："+aDefault.getProvider().getName());

    String host ="book.douban.com";
    SSLSocketFactory socketFactory = sun.getSocketFactory();
    SSLSocket socket = (SSLSocket) socketFactory.createSocket(host, 443);

        socket.startHandshake();//握手
    String[] supportedProtocols = socket.getSupportedProtocols();
        System.out.println("==========服务器支持的协议");
        for (String tmp : supportedProtocols) {
        System.out.println(tmp);
    }

    SSLSession session = socket.getSession();
    String cipherSuite = session.getCipherSuite();
    Certificate[] peerCertificates = session.getPeerCertificates();//获得远程的证书
    Certificate[] localCertificates = session.getLocalCertificates();//获得本地证书


    OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
    //https在Get行中需要完成URL
        out.write("GET https://"+host+"/ HTTP/1.1\r\n");
        out.write("Host: "+host+ "\r\n");
        out.write("\r\n");
        out.flush();
    //获得网页的内容
    boolean connected = socket.isConnected();
    InputStream inputStream = socket.getInputStream();
    InputStreamReader in = new InputStreamReader(inputStream);
    BufferedReader reader = new BufferedReader(in);
    String tmp;
//        while ((tmp = reader.readLine()) != null){
//            System.out.println(tmp);
//        }





    //返回内容的长度
    int length = Integer.MAX_VALUE;
    //读取首部
    String s;
        while (!(s = reader.readLine()).equals("")){
        int postitn = s.toLowerCase().indexOf("length");
        if(postitn != -1){
            //获得返回内容的长度
            String substring = s.substring(postitn + 7, s.length()).trim();
            length = Integer.parseInt(substring);
        }

        System.out.println(s);
    }
        System.out.println();

        System.out.println("内容的长度:"+length);

    int c ;
    int i = 0;
        while ((c = inputStream.read()) != -1 && i++ < length){
        System.out.print((char) c);
        System.out.print("==》"+i);
    }
        socket.close();
}

    @Test
    public void 获得百度安全网页() throws IOException {
        URL url = new URL("https://www.baidu.com/");
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
        String cipherSuite = urlConnection.getCipherSuite();

    }


    @Test
    public void 使用HttpsurlConnection来进行链接的操作() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext sun = SSLContext.getInstance("TLSv1.2");
        X509TrustManager myTrust = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
        sun.init(null,new TrustManager[]{myTrust},null);


        SSLContext instance = SSLContext.getInstance("TLSv1.2");  //设置安全链接上下文
        instance.init(null,new TrustManager[]{myTrust},null);//初始化安全链接上下文
        SSLSocketFactory socketFactory = instance.getSocketFactory();//获得socketFactory工厂创建socket


        URL url = new URL("https://www.baidu.com/");
        HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();//获得链接实例
        urlConnection.setDoInput(true);//打开输出流
        urlConnection.setDoOutput(true);//打开输入流
        urlConnection.setSSLSocketFactory(socketFactory);//设置工厂信息
        urlConnection.connect();//连接到服务器


        Certificate[] serverCertificates = urlConnection.getServerCertificates();//获得服务器的证书
        Certificate[] localCertificates = urlConnection.getLocalCertificates();//获得发到服务器的证书
        

    }



}
