package _007_Authentication;

import org.apache.commons.codec.digest.DigestUtils;
//import org.bouncycastle.util.encoders.Base64;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Properties;

public class _001_安全提供者体系结果 {

    /**
     * 1.常规属性的显示
     */
    @Test
    public void baseTest1(){

        Properties properties = System.getProperties();
        properties.entrySet().stream().filter(e->((String)e.getKey()).indexOf("security")!= -1).forEach(e->{
            System.out.println(e.getKey()+"--->"+e.getValue());
        });
    }

    /**
     * 2.打印当前系统所配置的全部的安全提供者
     */
    @Test
    public void baseUse2(){

        Arrays.stream(Security.getProviders()).forEach(e->{
            System.out.println(e.getName());
        });
        
    }

    /**
     * 3.做一个简单的摘要处理
     */
    @Test
    public void baseUse3() throws NoSuchAlgorithmException {
        byte[] bytes = "Dyy".getBytes();
        MessageDigest sha = MessageDigest.getInstance("SHA");
        sha.update(bytes);

        byte[] digest = sha.digest();
        System.out.println(Arrays.toString(digest));
    }

    /**
     * 4.MD5算法摘要输入流处理
     */
    @Test
    public void baseUse4() throws NoSuchAlgorithmException, IOException {
        byte[] input = "Dyy".getBytes();
        MessageDigest md = MessageDigest.getInstance("MD5");
        //构建DigestInputStream对象
        DigestInputStream dis = new DigestInputStream(new ByteArrayInputStream(input), md);

        dis.read(input,0,input.length);
        byte[] digest = dis.getMessageDigest().digest();
        dis.close();
        System.out.println(Arrays.toString(digest));
    }

    /**
     * 5.使用Bouncy castle 完成Base64加密解密
     */
    @Test
    public void baseUse5() throws UnsupportedEncodingException {
//        String inputStr= "Java加密与解密的艺术";
//        byte[] encode = Base64.encode(inputStr.getBytes("utf-8"));
//        System.out.println(new String(encode,"utf-8"));
//        byte[] decode = Base64.decode(encode);
//        System.out.println(new String(decode,"utf-8"));
    }


    /**
     * 6.使用Apache Commons 完成Base64的加解密 支持一般的和 RFC2045的标准
     */
    @Test
    public void baseUse6() throws UnsupportedEncodingException {
        String coding = "UTF-8";
        String inputStr= "Java加密与解密的艺术";
        byte[] bytes = org.apache.commons.codec.binary.Base64.encodeBase64(inputStr.getBytes(coding));
        System.out.println(new String(bytes,coding));
        //使用RFC2045标准执行 对输出结果没76个字符追加一个回车换行符
        byte[] bytes1 = org.apache.commons.codec.binary.Base64.encodeBase64(inputStr.getBytes(coding), true);
        System.out.println(new String(bytes1,coding));

        byte[] bytes2 = org.apache.commons.codec.binary.Base64.encodeBase64(inputStr.getBytes(coding), true, true);
        System.out.println(new String(bytes2,coding));


        byte[] bytes3 = org.apache.commons.codec.binary.Base64.decodeBase64(bytes);
        byte[] bytes4 = org.apache.commons.codec.binary.Base64.decodeBase64(bytes1);
        byte[] bytes5 = org.apache.commons.codec.binary.Base64.decodeBase64(bytes2);
        System.out.println(new String(bytes3,coding));
        System.out.println(new String(bytes4,coding));
        System.out.println(new String(bytes5,coding));

        String s = org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(inputStr.getBytes(coding));
        System.out.println(s);

    }

    /**
     * 7.MD5相关算法
     */
    @Test
    public void baseUse7() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String s = DigestUtils.md5Hex("Dyy".getBytes("UTF-8"));
        System.out.println(s);
        DigestUtils.sha1Hex("6666");
    }


    /**
     * 8.对称加密算法DES的使用
     */
    @Test
    public void baseUse8() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        //1.设置使用算法
        String algorithm = "DES";
        //2.设置加密解密算法/工作模式/填充方式
        String cipher = "DES/ECB/PKCS5Padding";
        //3.要加密的文字
        String inputStr = "DES";

        //初始化秘钥
        //1.生成秘钥
        KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            //设置秘钥长度
        kg.init(56);
            //生成秘钥
        SecretKey secretKey = kg.generateKey();
            //返回秘钥的数组
        byte[] encoded = secretKey.getEncoded();

        //2.转化秘钥 根据数组生成响应的key
       /* DESKeySpec desKeySpec = new DESKeySpec(encoded);
        SecretKeyFactory fac = SecretKeyFactory.getInstance(algorithm);*/
//        SecretKey secretKey1 = fac.generateSecret(desKeySpec);

        SecretKey secretKey1 = new SecretKeySpec(encoded, algorithm);
        //算法执行器
        Cipher instance = Cipher.getInstance(cipher);
        //3.加密 也就是算数加密
        instance.init(Cipher.ENCRYPT_MODE,secretKey1);
        byte[] bytes1 = instance.doFinal(inputStr.getBytes("UTF-8"));
        System.out.println("加密的密文是:"+ org.apache.commons.codec.binary.Base64.encodeBase64String(bytes1));

        //4.解密 也就是做算数
        //设置为解密模式
        instance.init(Cipher.DECRYPT_MODE, secretKey1);
        byte[] bytes = instance.doFinal(bytes1);
        System.out.println("解密的密文是:"+new String(bytes));
        
        //5.秘钥的显示
        System.out.println("密钥匙:"+ org.apache.commons.codec.binary.Base64.encodeBase64String(encoded));


    }

    /**
     * 9.使用AES对数据进行对称加密
     */
    @Test
    public void baseUse9() throws Exception {
        String keyAlgorithm =  "AES";
        String cipher = "AES/ECB/PKCS5Padding";
        String date = "Dyy";
        byte[] bytes = date.getBytes("UTF-8");

        //1.生成秘钥
        KeyGenerator keyGenerator = KeyGenerator.getInstance(keyAlgorithm);
        keyGenerator.init(new SecureRandom());
        //生成的秘钥和返回的数组
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] encoded = secretKey.getEncoded();


        //2.加密
        Cipher instance = Cipher.getInstance(cipher);
        instance.init(Cipher.ENCRYPT_MODE,secretKey);
        //密文
        byte[] bytes1 = instance.doFinal(bytes);


        //3.解密
        instance.init(Cipher.DECRYPT_MODE,secretKey);
        //解密之后的明文
        byte[] bytes2 = instance.doFinal(bytes1);

        //4.根据数组构建秘钥的操作
   /*     SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, keyAlgorithm);
        SecretKeyFactory instance1 = SecretKeyFactory.getInstance(keyAlgorithm);
        SecretKey secretKey1 = instance1.generateSecret(secretKeySpec);//产生错误*/
        SecretKey secretKey1 = new SecretKeySpec(encoded, keyAlgorithm);
        byte[] encoded1 = secretKey1.getEncoded();

        System.out.println("明文："+date);
        System.out.println("密文："+ org.apache.commons.codec.binary.Base64.encodeBase64String(bytes1));
        System.out.println("解密之后："+new String (bytes2));
        System.out.println("秘钥1："+ new String(org.apache.commons.codec.binary.Base64.encodeBase64(encoded)));
        System.out.println("秘钥2："+ org.apache.commons.codec.binary.Base64.encodeBase64String(encoded1));


    }


    /**
     * 11.非对称加密
     */
    @Test
    public void baseUse11() throws Exception {
        //甲方
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        //乙方 乙方要根据甲方的公钥生成密钥对
        //甲方的共要参数
        DHParameterSpec dhParamSpec = ((DHPublicKey) publicKey).getParams();
        //初始化秘钥生成器
        keyPairGenerator.initialize(dhParamSpec);
        //生成秘钥对
        KeyPair keyPair1 = keyPairGenerator.generateKeyPair();

        PublicKey aPublic = keyPair.getPublic();
        PrivateKey aPrivate = keyPair.getPrivate();

        //2.如果我们要将秘钥材料转换成秘钥对象。 材料 ---> 对象

        //将密钥数组构造为公钥私钥对象
        KeyFactory dh = KeyFactory.getInstance("DH");
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(aPublic.getEncoded());
        //产生公钥
        PublicKey publicKey1 = dh.generatePublic(x509EncodedKeySpec);
        //初始化私钥私钥 私钥密钥材料转换
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(aPrivate.getEncoded());
        PrivateKey privateKey1 = dh.generatePrivate(pkcs8EncodedKeySpec);

        //3.构建本地密钥
        //本地密钥就是对称加密算法中的秘密密钥
        KeyAgreement dh1 = KeyAgreement.getInstance("DH");
        //初始化
        dh1.init(privateKey1);
        dh1.doPhase(publicKey1,true);
        SecretKey aes = dh1.generateSecret("AES");

        dh1.init(privateKey);
        dh1.doPhase(publicKey,true);
        SecretKey aes1 = dh1.generateSecret("AES");

        //最终通过这个本地密钥来传递信息  这个很厉害  ，最终他们两个私钥都是一样的
        System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(aes.getEncoded()));
        System.out.println(org.apache.commons.codec.binary.Base64.encodeBase64String(aes1.getEncoded()));
    }


    /**
     * 12.非对称加密算法
     */
    @Test
    public void baseUse12() throws Exception {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
        rsa.initialize(1024);
        KeyPair keyPair = rsa.generateKeyPair();

        RSAPrivateKey aPrivate = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey aPublic = (RSAPublicKey)keyPair.getPublic();

        //私钥加密公钥解密
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(aPrivate.getEncoded());
        KeyFactory rsa1 = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = rsa1.generatePrivate(pkcs8EncodedKeySpec);
        //对数据进行解密的操作
        Cipher rsa2 = Cipher.getInstance("RSA");
        rsa2.init(Cipher.ENCRYPT_MODE,privateKey);
        byte[] bytes = rsa2.doFinal("Dyy".getBytes("UTF-8"));


        rsa2.init(Cipher.DECRYPT_MODE,aPublic);
        byte[] bytes1 = rsa2.doFinal(bytes);
        System.out.println(new String (bytes1));

        SSLContext context = SSLContext.getInstance("SSL");

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
    }


    /**
     * 13.密钥的导入
     */
    @Test
    public void  baseUse13() throws Exception {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream is = new FileInputStream("C:\\Users\\Dyy\\Dyy1.keystore");
        ks.load(is,"123456".toCharArray());
        is.close();
        PrivateKey key = (PrivateKey) ks.getKey("www.Dyy1.org", "123456".toCharArray());
        System.out.println("私钥是："+ org.apache.commons.codec.binary.Base64.encodeBase64String(key.getEncoded()));

        //获得证书来获得公钥
        X509Certificate certificate = (X509Certificate)ks.getCertificate("www.Dyy1.org");
        PublicKey publicKey = certificate.getPublicKey();
        System.out.println("公钥是："+ org.apache.commons.codec.binary.Base64.encodeBase64String(publicKey.getEncoded()));


        KeyStore ks1 = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream is1 = new FileInputStream("C:\\Users\\Dyy\\Dyy2.keystore");
        ks1.load(is1,"1234567".toCharArray());
        is1.close();
        PrivateKey key1 = (PrivateKey) ks1.getKey("www.Dyy2.org", "1234567".toCharArray());
        System.out.println("私钥是："+ org.apache.commons.codec.binary.Base64.encodeBase64String(key1.getEncoded()));

        //获得证书来获得公钥
        X509Certificate certificate1 = (X509Certificate)ks1.getCertificate("www.Dyy2.org");
        PublicKey publicKey1 = certificate1.getPublicKey();
        System.out.println("公钥是："+ org.apache.commons.codec.binary.Base64.encodeBase64String(publicKey1.getEncoded()));



        //获得密钥管理工厂
        KeyManagerFactory instance = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        instance.init(ks,"123456".toCharArray());
        KeyManager[] keyManagers1 = instance.getKeyManagers();
        instance.init(ks1,"1234567".toCharArray());
        KeyManager[] keyManagers = instance.getKeyManagers();

        KeyManager[] dest = new KeyManager[keyManagers.length+keyManagers1.length];
        System.arraycopy(keyManagers,0,dest,0,keyManagers.length);
        System.arraycopy(keyManagers1,0,dest,keyManagers.length,keyManagers1.length);



        SSLContext ssl = SSLContext.getInstance("SSL");

    }
}
