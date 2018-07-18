package _000_day._003_7月18日注解;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/18 下午11:13<br/>
 */
public class 测试注解 {

    @Test
    public void 获得类上注解的值() {
        TestClass testClass = new TestClass();
        Class<? extends TestClass> aClass = testClass.getClass();
        boolean annotationPresent = aClass.isAnnotationPresent(AnnotaionTest.class);
        System.out.println("是不是存在AnnotaionTest字段"+annotationPresent);

        AnnotaionTest annotation = aClass.getAnnotation(AnnotaionTest.class);
        System.out.println(annotation.age());
        System.out.println(annotation.name());
        System.out.println(annotation.shoole());
    }
}
