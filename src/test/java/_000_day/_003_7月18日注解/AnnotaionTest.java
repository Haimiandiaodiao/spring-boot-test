package _000_day._003_7月18日注解;

import java.lang.annotation.*;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/18 下午10:38<br/>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
//@Inherited
public @interface AnnotaionTest {
    String name();
    int age();
    String shoole() default "Tj";

}
