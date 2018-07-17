package _000_day._001_7月17日反射;

import java.lang.reflect.ParameterizedType;

/**
 * @Author Dyy <br/>
 * @Description <br/>
 * @Date 2018/7/17 下午2:57<br/>
 */
public abstract class Father<T> implements showInter<T> {
    private Class clazz;

    public Father(){
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class<T>) type.getActualTypeArguments()[0];
        System.out.println("=======子类上面的范型是"+clazz.getName());
    }

    public T doGeneric(){
        if(clazz == String.class){
            return (T) "我是字符";
        }

        if(clazz == Integer.class) {
            return (T) new Integer(1);
        }
        return null;
    }
}
