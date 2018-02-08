package _004_JavaIO;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * JavaIO
 * @auther Dyy
 * @create 2018/2/8
 */
public class _001_IOBaseUse {

    /**
     * 1.字符生成器循环输出而不是归零重新输出
     * 每行72个字
     * 生成字符的全量
     * 使用outputStream的write（int）方法
     */
    @Test
    public void baseUse1() throws  Exception{
        int firstPrintChar = 33;//开始字符
        int numberOfPrintChar =126;//结束的字符
        int numberOfChar= 72;//每行的个数

        int start = firstPrintChar;//每一次输出的长度

        OutputStream out = new FileOutputStream("chara.txt");
        int count =0;
        //循环无限的输出
        while (count <= 50){
            for (int i = start; i < start+numberOfChar; i++) {
                int i1 = ((i - firstPrintChar) % (numberOfPrintChar-firstPrintChar)) + firstPrintChar;
                out.write(i1);//写入一个字符
            }
            //写入换行
            out.write(13);//\r
            out.write(10);//\n

            //叠加总的次数
            count++;
            //重置start
            start = ((start+1) - firstPrintChar)% numberOfChar + firstPrintChar;
        }


    }


    /**
     * 2.查看换行的ASCII值
     * 10	LF (NL line feed, new line)	换行键
     * 3	CR (carriage return)	回车键
     */
    @Test
    public void baseUse2(){
        System.out.println((int)'\r');//13
        System.out.println((int)'\n');//10
        System.out.println(30%60);
    }
}
