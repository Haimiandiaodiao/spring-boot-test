package com.dyy.Service;

import com.dyy.Configution.Properties.PersonInfoServiceProperties;
import lombok.Data;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@Data
public class PersonInfoService {

    private PersonInfoServiceProperties personInfoServiceProperties;

    public String getPersonInfo(){
        return personInfoServiceProperties.getName() +"===>"+personInfoServiceProperties.getAge()+"==>"+personInfoServiceProperties.getSchool();
    }
}
