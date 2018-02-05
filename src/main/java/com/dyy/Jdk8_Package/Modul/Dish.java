package com.dyy.Jdk8_Package.Modul;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @auther Dyy
 * @create 2018/2/2
 */
@Getter
@AllArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    @Override
    public String toString() {
        return name;
    }
    public enum Type { MEAT, FISH, OTHER }
}
