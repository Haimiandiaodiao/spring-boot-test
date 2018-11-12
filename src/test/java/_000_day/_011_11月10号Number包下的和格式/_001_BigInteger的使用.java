package _000_day._011_11月10号Number包下的和格式;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.math.BigInteger;

/**
 * 大的长整形的使用
 * @Author:Dyy
 * @Description:
 * @Date: Created in 12:15 2018/11/10
 */
public class _001_BigInteger的使用 {
    //可以将md5加密的数字以16进制的形式显示出来

    @Test
    public void baseUse(){
        BigInteger bigInteger = new BigInteger("100"); //默认时十进制
        BigInteger bigin1 = new BigInteger("8", 10);
        String s = bigin1.toString(2);
        byte[] bytes = Base64.encodeBase64("你好".getBytes());
        BigInteger bigInteger1 = new BigInteger(bytes);
        System.out.println(bigInteger1.toString(16).toUpperCase());
        String s1 = Base64.encodeBase64String("你好".getBytes());
        System.out.println(s1);
    }

}
