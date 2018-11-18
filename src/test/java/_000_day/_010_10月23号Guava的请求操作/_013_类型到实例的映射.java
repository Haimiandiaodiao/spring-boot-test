package _000_day._010_10月23号Guava的请求操作;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/11/18 12:35 PM<br/>
 */
public class _013_类型到实例的映射 {

    /**
     * ClassToInstanceMap是一种特殊的Map：它的键是类型，而值是符合键所指类型的对象。
     * 为了扩展Map接口，ClassToInstanceMap额外声明了两个方法：T getInstance(Class<T>) 和T putInstance(Class<T>, T)，从而避免强制类型转换，同时保证了类型安全。
     * ClassToInstanceMap有唯一的泛型参数，通常称为B，代表Map支持的所有类型的上界。
     *
     *
     * ClassToInstanceMap<Number> numberDefaults=MutableClassToInstanceMap.create();
     * numberDefaults.putInstance(Integer.class, Integer.valueOf(0));
     */



}
