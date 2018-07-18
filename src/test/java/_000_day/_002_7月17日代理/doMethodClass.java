package _000_day._002_7月17日代理;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午3:47<br/>
 */
public class doMethodClass implements needMethodInteface {

    @Override
    public final String show1() {
        return "这个是show1方法";
    }

    @Override
    public void show2() {
        System.out.println("这个是show2方法");
    }

    @Override
    public Integer show3() {
        return 3;
    }
}
