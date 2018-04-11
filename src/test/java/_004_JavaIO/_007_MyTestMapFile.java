package _004_JavaIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class _007_MyTestMapFile {

    public static void main (String[] args) throws IOException {
        RandomAccessFile rw = new RandomAccessFile("Dyy.txt", "rw");
        FileChannel chan = rw.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
        buffer.put("Dyy First Test MappedByteBuffer".getBytes());
        //装换成读模式，来进行写入通道内
        buffer.flip();
        chan.write(buffer);
        //清除缓冲区状态再次使用
        buffer.clear();

        //测试文件空洞
        buffer.put("Dyy Second Write".getBytes());
        buffer.flip();
        chan.write(buffer,1024*8*2);//确实产生了文件空洞中间空了很多的空间
        buffer.clear();

        //输出一下文件的大小
        System.out.println("文件的大小："+chan.size());
        //现在测试一下使用mappedByteBuffer来读取文件内容
        MappedByteBuffer map = chan.map(FileChannel.MapMode.READ_WRITE, 0, chan.size());
        MappedByteBuffer map1 = chan.map(FileChannel.MapMode.PRIVATE, 0, chan.size());

        //修改第二个分页的内容
        map.position(1024*8);
        map.put("使用".getBytes("utf-8"));
        map.force();
        //查看各各的状态
        int limit = map.limit();

        System.out.println("写通道");
        for (int i = 0; i < limit; i++) {
            char b = (char)map.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }

        System.out.println("私有通道");
        int limit1 = map1.limit();
        for (int i = 0; i < limit1; i++) {
            char b = (char)map1.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }

        //修改私有区的内容
        map1.position(0);
        map1.put("OMG".getBytes());
        System.out.println("写通道");
        for (int i = 0; i < limit; i++) {
            char b = (char)map.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }

        System.out.println("私有通道");
        for (int i = 0; i < limit1; i++) {
            char b = (char)map1.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }

        //在修改0的位置  私有区位置的分页内容应该是不会被修改
        map.position(0);
        map.put("我想写入".getBytes());

        System.out.println("写通道");
        for (int i = 0; i < limit; i++) {
            char b = (char)map.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }

        System.out.println("私有通道");
        for (int i = 0; i < limit1; i++) {
            char b = (char)map1.get(i);
            if( b != '\u0000'){
                System.out.print(b);
            }
        }
//
//
//        MappedByteBuffer map = chan.map(FileChannel.MapMode.READ_WRITE, 0, chan.size());
//        map.put("Dyy First Test MappedByteBuffer".getBytes());
//        map.force();
//        chan.close();
//        rw.close();
    }
}
