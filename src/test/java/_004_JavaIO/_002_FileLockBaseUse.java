package _004_JavaIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

public class _002_FileLockBaseUse {
    private static final int SIZEOF_INT = 4;
    private static final int INDEX_START = 0;
    private static final int INDEX_COUNT = 10;
    private static final int INDEX_SIZE = INDEX_COUNT * SIZEOF_INT;

    private ByteBuffer buffer = ByteBuffer.allocate(INDEX_SIZE);
    private IntBuffer indexBuffer = buffer.asIntBuffer();
    private Random rand = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {
        boolean write =true;
        String filename = "test.txt";
        RandomAccessFile raf = new RandomAccessFile(filename, (write) ? "rw" : "r");
        FileChannel fc = raf.getChannel();
        _002_FileLockBaseUse lockTest = new _002_FileLockBaseUse();
        if(write){
            lockTest.doUpdates(fc);
        }else{
            lockTest.doQueries(fc);
        }

    }

    //读的操作
    private void doQueries(FileChannel fc) throws IOException, InterruptedException {
        while (true){
            System.out.println("尝试得到共享锁...");
            FileLock lock = fc.lock(INDEX_START, INDEX_SIZE, true);
            int reps = rand.nextInt(60) +20;
            for (int i = 0; i < reps; i++) {
                int n = rand.nextInt(INDEX_COUNT);
                int position = INDEX_START + (n * SIZEOF_INT);
                buffer.clear();
                fc.read(buffer,position);//随机读取的值
                int value = indexBuffer.get(n);
                System.out.println("索引实体的值"+n+"="+value);
                Thread.sleep(100);
            }
            lock.release();
            System.out.println("sleeping");
            Thread.sleep(rand.nextInt(30000)+500);
        }
    }


    //写操作
    private int idxval =46;
    private void doUpdates(FileChannel fc) throws InterruptedException, IOException {
        indexBuffer.clear();
        for (int i = 0; i < INDEX_COUNT; i++) {
            idxval++;
            System.out.println("Updating index "+i+"="+idxval);
            indexBuffer.put(idxval);
            //Thread.sleep(500);
        }
        buffer.clear();
        fc.write(buffer,INDEX_START);
    }
}
