package com.dyy.Modul.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @auther Dyy
 * @create 2018/1/25
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Father  implements Serializable {
    private String name;
    private Integer age;

    private Son son;
    private List<String> hobbies;

    public void setName(String name) {
        this.name = "_____"+name;
    }

    public String getName() {
        return name+"____________";
    }
}
