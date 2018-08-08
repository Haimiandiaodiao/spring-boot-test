package _000_day._007_8月02号线程;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/2 下午4:04<br/>
 */
public class _001_线程的可见性测试 {
    private static boolean flag;
    private static int number;

    public static class readThread extends Thread{
        @Override
        public void run() {
            while (!flag) {
                System.out.println("程序还不可读");
                Thread.yield();
            }
            System.out.println("numbner可以读取了值是=======>:"+number);
        }
    }

    public static void main(String[] args){
        readThread readThread = new readThread();
        readThread.start();
        number = 100;
        flag =true;
    }
}
