package _004_JavaIO;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class _009_ChannelToChannel {

        public static void main (String [] args) throws Exception {
            WritableByteChannel out = Channels.newChannel(System.out);
            RandomAccessFile file = new RandomAccessFile("pom.xml","r");
            FileChannel channel = file.getChannel();

            channel.transferTo(0, channel.size(), out);
            channel.close();
            file.close();
            out.close();
        }
}
