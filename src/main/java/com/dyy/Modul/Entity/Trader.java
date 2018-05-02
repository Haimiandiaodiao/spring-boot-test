package com.dyy.Modul.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Trader {
    private final String name;
    private final String city;

    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}
