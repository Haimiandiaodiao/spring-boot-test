package com.dyy.Jdk8_Package.Modul;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 交易
 * @auther Dyy
 * @create 2018/2/5
 */
@Getter
@ToString
@AllArgsConstructor
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
}
