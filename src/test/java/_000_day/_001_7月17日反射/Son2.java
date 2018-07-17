package _000_day._001_7月17日反射;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午3:26<br/>
 */
public class Son2 extends Father<Integer> implements showInter<Integer> {
    @Override
    public void show() {
        Integer integer = this.doGeneric();
        System.out.println(integer);
    }
}
