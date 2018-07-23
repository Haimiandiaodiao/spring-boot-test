package _000_day._004_7月20号安全认证;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.jni.Proc;
import org.junit.Test;
import sun.security.rsa.RSAPrivateKeyImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

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
}
