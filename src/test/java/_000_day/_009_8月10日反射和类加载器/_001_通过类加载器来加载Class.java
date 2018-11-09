package _000_day._009_8月10日反射和类加载器;

import com.dyy.Modul.Entity.Father;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.ResultSet;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/8/10 上午9:50<br/>
 */
public class _001_通过类加载器来加载Class {

    @Test
    public void 类加载器数实例对象() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> aClass = loader.loadClass("com.dyy.Modul.Entity.Father");
        Constructor<?> constructor = aClass.getDeclaredConstructor(null);
        Object o = constructor.newInstance(null);
        Method setName = aClass.getMethod("setName", String.class);
        setName.invoke(o,"Dyy");
        System.out.println(o);

    }


    @Test
    public void 默认使用AppClassLoader类加载器() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println("loader1-->"+loader);
        ClassLoader parent = loader.getParent();
        System.out.println("loader2-->"+parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println("loader3-->"+parent1);
        ClassLoader parent2 = parent1.getParent();
        System.out.println("loader4-->"+parent2);

    }


    @Test
    public void 查看基础类型的Class() {
        Class<Integer> intc = int.class;
        Class<Void> voidClass = void.class;
        System.out.println(intc);
        System.out.println(voidClass);
    }


    @Test
    public void 使用资源加载器() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("test.txt");
        System.out.println(fileInputStream.read());

        ClassPathResource resource = new ClassPathResource("BaseTest.class");//不需要家classpath前缀
        URL url = resource.getURL();
        System.out.println(url.getFile());
    }

    @Test
    public void 使用路径匹配资源匹配器() throws IOException {

        PathMatchingResourcePatternResolver resourced = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourced.getResources("classpath:**/*.class");
        for (Resource resource : resources) {
            System.out.println(resource.getFilename());
        }

    }
}



