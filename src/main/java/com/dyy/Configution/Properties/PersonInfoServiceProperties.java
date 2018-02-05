package com.dyy.Configution.Properties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "person")
public class PersonInfoServiceProperties {

    private final  String  NAME = "阿西吧";
    private final   Integer AGE = 1;
    private final String SCHOOL = "一年級";

    private String name = NAME;
    private Integer age = AGE;
    private String school = SCHOOL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
