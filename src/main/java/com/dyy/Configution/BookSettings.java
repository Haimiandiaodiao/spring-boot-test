package com.dyy.Configution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @auther Dyy
 * @create 2018/1/22
 */
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "book")
@PropertySource({"classpath:book.properties"})
public class BookSettings {
    private String name;
    private String author;
    private BigDecimal price;
}
