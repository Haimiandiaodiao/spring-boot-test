package _000_day._001_7月17日反射;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午3:25<br/>
 */
public class Son1 extends Father<String> implements showInter<String>{
    @Override
    public void show() {
        String s = this.doGeneric();
        System.out.println(s);
    }
}
