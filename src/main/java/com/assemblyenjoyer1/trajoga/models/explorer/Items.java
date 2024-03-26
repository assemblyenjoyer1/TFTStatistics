package com.assemblyenjoyer1.trajoga.models.explorer;

import lombok.Data;

@Data
public class Items {

    public String name;
    public String type;
    public Stats stats;

    public Items(String name, String type, Stats stats) {
        this.name = name;
        this.type = type;
        this.stats = stats;
    }

    public Items() {
    }
}
