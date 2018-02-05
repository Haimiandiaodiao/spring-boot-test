package com.dyy.Modul.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Son {
    @JSONField(name = "alias")
    private String  name;
    private Integer age;
    private List<String> hobbies;
    private Map<String,Object> addr;
}
