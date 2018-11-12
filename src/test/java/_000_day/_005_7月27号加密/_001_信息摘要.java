package _000_day._005_7月27号加密;

import com.dyy.Jdk8_Package.Modul.Dish;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/27 下午10:26<br/>
 */
public class _001_信息摘要 {


    @Test
    public void Base64编码的操作() {
        String str = "Dyy正在学习base64加密==================================================";
        byte[] bytes = Base64.encodeBase64(str.getBytes());//第二个参数是进不进行换行的处理
        byte[] bytes1 = Base64.decodeBase64(bytes);
        System.out.println("编码的信息:"+new String(bytes));
        System.out.println("解码之后的信息:"+new String(bytes1));

        String QQ = "MTc3Mzg4ODYyNQ==";
        String s = Base64.encodeBase64String(str.getBytes());
        System.out.println("直接使用的:"+s);
        byte[] bytes2 = Base64.decodeBase64(QQ);
        System.out.println(new String(bytes2));


    }


    /**
     * @Author:Dyy
     * 使用BigInteger对Md5加密进行输出
     * @Description:
     * @Date: Created in 12:32 2018/11/10
     * @param
     */
    @Test
    public void MD信息摘要算法() throws NoSuchAlgorithmException {
        String str = "Dyy正在学习";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] digest = md5.digest();
        System.out.println(Base64.encodeBase64String(digest));
        System.out.println("直接输出md5加密内容=====>"+new String(digest));
        BigInteger bigInteger = new BigInteger(digest);
        System.out.println("16进制输出内容==>"+bigInteger.toString(16));

        //========SHA编码
        MessageDigest instance = MessageDigest.getInstance("SHA-512");
        instance.update(str.getBytes());
        byte[] digest1 = instance.digest();
        System.out.println("SHA-512摘要之后的:"+Base64.encodeBase64String(digest1));
    }
}


