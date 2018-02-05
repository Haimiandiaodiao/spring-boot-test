package com.dyy.Configution;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@Configuration
@ConditionalOnClass({JSON.class})
public class FastJsonHttpMessageConvertersConfiguration {

    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //1.设置FastJson支持对的请求类型
        List<MediaType> types = new ArrayList<>();
        types.add(MediaType.APPLICATION_JSON);
        types.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(types);

        //2.设置fastJson输出的一些配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.forName("UTF-8"));//设置返回字符集
        //SerializerFeature.WriteClassName  打印出类名的格式
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        HttpMessageConverter<?> converter = fastConverter;
        return converter;
    }
}
