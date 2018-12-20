package _000_day._010_10月23号Guava的请求操作;

import com.google.common.base.Ticker;
import com.google.common.cache.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Author:Dyy
 * @Description:
 * @Date: Created in 13:55 2018/12/20
 */
public class _017_Guava对缓存的支持 {

    /**
     * 参考的文档是http://ifeve.com/google-guava-cachesexplained/
     * Guava Cache与ConcurrentMap很相似，但也不完全一样。
     * 最基本的区别是ConcurrentMap会一直保存所有添加的元素，直到显式地移除。相对地，Guava Cache为了限制内存占用，
     * 通常都设定为自动回收元素。在某些场景下，尽管LoadingCache 不回收元素，它也是很有用的，因为它会自动加载缓存。
     */

    @Test
    public void  使用CacheLoader的操作(){
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
                return "Dyy";
            }

        };

        //======================【1】验证cacheLoader加载默认值 和 是否重复加载默认值
        //======================【6】maximumSize是设置缓存中最大允许存放缓存的数量  后添加的会覆盖早添加的
        LoadingCache<String, String> cacher = CacheBuilder.newBuilder().maximumSize(100).build(cacheLoader);
        //1.CacheLoader当获得的key不存在的时候获得默认的缓存值的操作
        String a = cacher.getUnchecked("a");
        System.out.println(a);
        //1.1如果已经当用了默认 获得的方法，那么就不会再次调用  cacherLoader的Loader加载默认值了 ，因为默认值已经被加载过一次了
        String a1 = cacher.getUnchecked("a");
        System.out.println(a1);

        //=================【2】验证callable方法=============
        //2.获得的时候如果不存在则设置默认值的操作
        //2.1 在获得key值没有的时候就调用callable来设置默认值
        Callable<String> getBKeyValue = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "返回使用Callable设置的b的默认的值";
            }
        };

        try {
            //这样就不调用cacheLoader的加载默认配置的load方法了
            String b = cacher.get("b", getBKeyValue);
            System.out.println(b);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //【3】使用get 会抛出异常   使用getUnchecked  不会抛出异常
        //=================【4】 使用getAll 会单批次的调用cacherLoader的loader方法 ，如果可以可以重写cacheLoader的loadAll方法

        ArrayList<String> keys = Lists.newArrayList("A", "B", "C", "D");
        try {
            //因为缓存中没有上面那4个Key所以 循环四次调用了上面的caheLoader 的loader方法
            ImmutableMap<String, String> all = cacher.getAll(keys);
            System.out.println(all);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //==================【5】 显式的插入值
        //使用Debug 查看Map中的值
        //【注意】 使用Map视图的这种形式查看缓中的值的方法  会存在问题因为asMap视图的任何方法都不能保证缓存项被原子地加载到缓存中
        //        进一步说，asMap视图的原子运算在Guava Cache的原子加载范畴之外应该优先使用  Cache.asMap().putIfAbsent(K,V)，Cache.get(K, Callable<V>) 应该总是优先使用。
        //        或者使用cacher.put方法也是可以的
        ConcurrentMap<String, String> asMap = cacher.asMap();
        asMap.put("A","使用Map不推荐的方法");
        //下面这个是更新不成功的 因为B的值已经存在了
        asMap.putIfAbsent("B","使用并发包的put方法");

        System.out.println("");
    }



    @Test
    public void 缓存的回收机制(){
        //Gauava的cache  支持三种回收机制 1.基于容量回收,基于权重的回收  2.定时回收  3.基于引用回收 这种方法一般使用的比较少


        //【注意】
        //  【！1】 在缓存项的数目达到限定值之前，缓存就可能进行回收操作——通常来说，这种情况发生在缓存项的数目逼近限定值时
        //====================【1】测试 基于容量的回收============
        //【1.1】使用的是基于权重的， 但是要写获得权重的计算方法
        //Cache<Object, Object> build = CacheBuilder.newBuilder().maximumWeight(2).build();
        //【1.2】基于条数的回收
        //Cache<Object, Object> build = CacheBuilder.newBuilder().maximumSize(2).build();


        //=================【2】测试 基于时间的回收==========
        //【2.1】 指定时间内没有被读/写访问  就回收
        //Cache<Object, Object> build = CacheBuilder.newBuilder().expireAfterAccess(1000, TimeUnit.SECONDS).build();
        //【2.2】 指定时间内没有写 就回收
//        Cache<Object, Object> build = CacheBuilder.newBuilder().expireAfterWrite(1000, TimeUnit.SECONDS).build();

        //而可以设置缓存总的 时间也就是Ticker  Ticker 设置的其实是纳秒值
        Cache<Object, Object> build = CacheBuilder.newBuilder().expireAfterWrite(1000, TimeUnit.SECONDS).ticker(Ticker.systemTicker()).build();

        //==================【3】测试 基于引用的回收
        //CacheBuilder.weakKeys()： 弱引用 key   CacheBuilder.weakValues()： 弱引用 value      CacheBuilder.softValues()  软引用 value


        //============【4.1】 显示的清除缓存
        build.invalidate(1);
        //============【4.2】 显示的批量清除
        build.invalidateAll(new ArrayList<>());
        //============【4.1】 显示的清除所有的缓存
        build.invalidateAll();

    }

    @Test
    public void 移除监听器(){
        //=========== 【1】设置移除监听器，在数据移除的时候 做一些操作
        RemovalListener listener = new RemovalListener<String,String>() {

            @Override
            public void onRemoval(RemovalNotification<String, String> removalNotification) {
                String key = removalNotification.getKey();
                if(key.equals("A")){
                    System.out.println("========莫名其妙被删除=======");
                }
            }
        };

        Cache<String,String> build = CacheBuilder.newBuilder().removalListener(listener).build();
        build.put("A","一个值");
        build.put("B","两个值");
        build.put("C","三个值");


        //输出了莫名其妙被调用的字段  说明了调用删除 监听器的
        build.invalidate("A");
        build.invalidate("AA");

        //===========【2】 刷新的操作，使用的是 CacheLoader的 中的 reload的方法 和refreshWrite这个设置来设置value的刷新时间
    }
}
