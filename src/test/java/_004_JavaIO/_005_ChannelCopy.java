package _004_JavaIO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * 完成通道的copy
 */
public class _005_ChannelCopy {

    public static  void main (String[] args) throws IOException {
        //bufferedInputStream 有8K的缓冲区空间
//        ReadableByteChannel source = Channels.newChannel(System.in);
//        WritableByteChannel dest = Channels.newChannel(System.out);
//        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(5);
////        Thread.sleep(5000);
//        while (true){
//        //进入阻塞的模式
//        source.read(byteBuffer);
//        byteBuffer.flip();
//        dest.write(byteBuffer);
//        byteBuffer.compact();
//        }
//        RandomAccessFile file = new RandomAccessFile("test.txt","r");
        FileInputStream file = new FileInputStream("test.txt");
        FileChannel channel = file.getChannel();
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, 47);
        byte[] bytes = new byte[(int)channel.size()];
        ByteBuffer byteBuffer = map.get(bytes);

        WritableByteChannel dest = Channels.newChannel(System.out);
        byteBuffer.flip();
        dest.write(byteBuffer);
        byteBuffer.position(5);
        map.put((byte)97);
        System.out.println(map.isLoaded());

    }



    private static void channelCopy1(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1){
            //转换为读模式
            buffer.flip();
        }
    }

}
