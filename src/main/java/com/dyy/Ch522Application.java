package com.dyy;

import com.dyy.Configution.BookSettings;
import com.dyy.Service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther Dyy
 * @create 2018/1/22
 */
@ComponentScan("com.dyy")
@RestController
@SpringBootApplication
@PropertySource({"classpath:person.properties"})
public class Ch522Application {
   // @Value("${person.name}")
    private String group;
    @Value("${server.port}")
    private Integer port;

    @Autowired
    private BookSettings book;
    @Value("${book.name}")
    private String bookName;

    @Autowired
    private PersonInfoService personInfoService;

    @RequestMapping("/")
    public String index(){
        return "Hello World Boot"+book.getAuthor()+"====>"+bookName ;
    }

    @ResponseBody
    @RequestMapping("/info")
    public Object indexInfo(){
        return  personInfoService.getPersonInfoServiceProperties();
    }


    public static void main(String[] args){
        SpringApplication app = new SpringApplication(Ch522Application.class);
        app.run(args);
    }
}
