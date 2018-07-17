package _011_SpringTest._001_base;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.SessionScope;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/6/12 上午11:12<br/>
 */
public class _001_BeanFactory_ApplicationContext {

    @Test
    @Bean()
    @DependsOn()
    @Lazy()
    @SessionScope
    public void baseUse1(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
    }
}
