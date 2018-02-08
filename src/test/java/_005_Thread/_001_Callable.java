package _005_Thread;

import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * 新的多线程的应用
 * @auther Dyy
 * @create 2018/2/8
 */
public class _001_Callable {

    /**
     * 1.多线程的应用
     */
    @Test
    public void baseUse1() throws ExecutionException, InterruptedException {

        int[] data = {1, 2, 3, 5343, 3423, 52, 34, 5, 2, 123, 1};

        FindMaxTask task1 = new FindMaxTask(data, 0, data.length / 2);
        FindMaxTask task2 = new FindMaxTask(data, data.length / 2, data.length);


        ExecutorService service = Executors.newFixedThreadPool(2);
        Future<Integer> submit = service.submit(task1);
        Future<Integer> submit1 = service.submit(task2);
        System.out.println(Integer.max(submit1.get(), submit.get()));
    }


    //多线程的查找最大值的方法
    @AllArgsConstructor
     class FindMaxTask  implements Callable<Integer>{
        private int[] data;
        private int start;
        private int end;

        @Override
        public Integer call() throws Exception {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < start; i++) {
                if(data[i] > max) max = data[i];
            }
            return max;
        }
    }
}
