package _001_Jdk8TestPackage;

import org.junit.Test;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 并行流的测试
 */
public class _008_ParallelStreamTest {

    /**
     * 1.将顺序流转换为并行流
     */
    @Test
    public void baseUse1(){
        Long reduce = Stream.iterate(1L, i -> i + 1)
                .limit(10)
                .parallel()             //将流转换为并行流
                .reduce(0L, Long::sum);
    }
}
