package _000_day._003_7月18日注解;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/18 下午10:38<br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AnnotaionTest {
    String name();
    int age();
    String shoole() default "Tj";

}
