package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Optional;
import org.junit.Test;

/**
 * @Author:Dyy
 * @Description:
 * @Date: Created in 13:59 2018/10/25
 */
public class _001_Optional的使用 {

    /**
     * 对Optional的使用描述
     * 使用Optional<T>除了简化粗鲁的if(null == object)、降低函数的复杂度、增加可读性之外，它是一种傻瓜式的防护，
     * Optional<T>引导编码人员主动的思考引用为null的情况。
     * */

    @Test
    public void 创建实例(){
        Integer a = 1;
        Integer b = null;
        //Optional.of 引用为空快速失效
        Optional<Integer> of = Optional.of(a);
        //异常出错因为
//        Optional<Integer> of1 = Optional.of(b);
        //Optonal.absent 创建引用缺失的实例
        Optional<Object> absent = Optional.absent();
        //Optional.fromNullable可以是引用缺失的方法
        Optional<Integer> aa1 = Optional.fromNullable(a);
        Optional<Integer> aa2 = Optional.fromNullable(b);

        System.out.println(aa1.isPresent());
        System.out.println(aa2.isPresent());

        Integer integer = aa1.get();
        Integer or = aa2.or(111);

        //引用确实抛出异常
        Integer integer1 = aa2.get();

    }
}
