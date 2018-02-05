package com.dyy.Configution.AutoConfigService;

import com.dyy.Configution.Properties.PersonInfoServiceProperties;
import com.dyy.Service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@Configuration
@EnableConfigurationProperties(PersonInfoServiceProperties.class)
@ConditionalOnClass(PersonInfoService.class)
@ConditionalOnProperty(value = "enable" ,prefix = "person",matchIfMissing = true)
public class PersonInfoAutoConfiguration {

    @Autowired
    private PersonInfoServiceProperties personInfoServiceProperties;

    @Bean
    @ConditionalOnMissingBean(PersonInfoService.class)
    public PersonInfoService createPersoninfoService(){
        PersonInfoService result = new PersonInfoService();
        result.setPersonInfoServiceProperties(personInfoServiceProperties);
        return result;
    }

}
