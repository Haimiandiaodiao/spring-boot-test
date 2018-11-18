package _000_day._011_11月18号JDK基础;

import org.junit.Test;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 10:23 AM<br/>
 */
public class _001_String注意点 {

    /**
     *
     * 1.注意string在使用eqauls的时候没有使用hashcode的方法
     * 2.string的hashcode方法中有  h = 31 * h + val[i]; 以31为权制
     *    主要是因为31是一个奇质数，所以31*i=32*i-i=(i<<5)-i，这种位移与减法结合的计算相比一般的运算快很多
     * 3.字符串哈希可以做很多事情，通常是类似于字符串判等，判回文之类的
     *   字符串哈希可以做很多事情，通常是类似于字符串判等，判回文之类的。
     *   但是仅仅依赖于哈希值来判断其实是不严谨的，除非能够保证不会有哈希冲突，通常这一点很难做到
     *
     */

    @Test
    public void 使用HashCode判断String的回文() {

        String a =new String("abc");
        String a1 =new String("cba");
        String a2 =new String("bca");

        int i = a.hashCode();
        int i1 = a1.hashCode();
        int i2 = a2.hashCode();

        System.out.println(i);
        System.out.println(i1);
        System.out.println(i2);
        //96354
        //98274
        //97344

        //注意下面这个特殊的

        String a5 =new String("gdejicbegh");
        String a6 =new String("hgebcijedg");


        int i5 = a5.hashCode();
        int i6 = a6.hashCode();

        System.out.println(i5);
        System.out.println(i6);

        //竟然奇迹的相等
        //-801038016
        //-801038016·
        //这个例子说明了用jdk中默认的hashCode方法判断字符串相等或者字符串回文，都存在反例。


        String a9 =new String("abcdefgfi");
        String a10 =new String("ifgfedcba");

        int i9 = a9.hashCode();
        int i10 = a10.hashCode();

        System.out.println(i9);
        System.out.println(i10);
    }
}

