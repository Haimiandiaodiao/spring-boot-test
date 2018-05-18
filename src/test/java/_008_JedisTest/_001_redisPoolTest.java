package _008_JedisTest;

import org.junit.Test;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.*;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.*;
import java.util.*;
import java.util.function.Consumer;

/**
 * @Author Dyy
 * @Description
 * @Date 2018/5/16 22:03
 */
public class _001_redisPoolTest {

    /**
     * 1.配置redisPool并且对其进行基础的使用的操作
     */
    @Test
    public void baseUse1() throws  Exception{
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(60000);

//        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.25.170", 7010,10000,"811595981");

        //配置集群连接点
        Set<HostAndPort> hosts = new HashSet();
        hosts.add(new HostAndPort("192.168.25.170",7010));
        hosts.add(new HostAndPort("192.168.25.170",7011));
        hosts.add(new HostAndPort("192.168.25.170",7012));
        hosts.add(new HostAndPort("192.168.25.170",7013));
        hosts.add(new HostAndPort("192.168.25.170",7014));
        hosts.add(new HostAndPort("192.168.25.170",7015));

        JedisCluster jedisCluster = new JedisCluster(hosts,10000,10000,10000,"811595981",jedisPoolConfig);

        String set = jedisCluster.set("shool", "TianjinSchool");
        System.out.println(set);

        String shool = jedisCluster.get("shool");
        System.out.println(shool);

        String set1 = jedisCluster.set("name", "dongyangyagn");
        System.out.println(set1);

        String name = jedisCluster.get("name");
        String shool1 = jedisCluster.get("shool");
        System.out.println("姓名："+name);
        System.out.println("学校："+shool);
    }


    /**
     * 2.使用redisTemplate来访问reids
     */
    @Test
    public void baseUse2(){
        Set<RedisNode> nodes= new HashSet<>();
        nodes.add(new RedisNode("192.168.25.170",7010));
        nodes.add(new RedisNode("192.168.25.170",7011));
        nodes.add(new RedisNode("192.168.25.170",7012));
        nodes.add(new RedisNode("192.168.25.170",7013));
        nodes.add(new RedisNode("192.168.25.170",7014));
        nodes.add(new RedisNode("192.168.25.170",7015));
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration((Collection)nodes);
        redisClusterConfiguration.setMaxRedirects(6);


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMaxWaitMillis(60000);

        JedisConnectionFactory  redisConnectionFactory = new JedisConnectionFactory (redisClusterConfiguration,jedisPoolConfig);
        redisConnectionFactory.setPassword("811595981");
        redisConnectionFactory.setUsePool(true);
        //很关键的一步
        redisConnectionFactory.afterPropertiesSet();


        RedisTemplate<String,String> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置序列化的操作这样可以保证你想要的序列化方法
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());

        //这一步很重要是配置之前设置的
        redisTemplate.afterPropertiesSet();


        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Object name = ops.get("name");
        System.out.println(name);
        ops.set("age","11");
        ops.set("age","12");
        ops.set("age","13");
        ops.set("age","14");
        Object age = ops.get("age");
        System.out.println(age);


    }

}
