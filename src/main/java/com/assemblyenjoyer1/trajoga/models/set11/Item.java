package com.assemblyenjoyer1.trajoga.models.set11;

import lombok.Data;

import java.util.List;

@Data
public class Item {

    private String itemId;
    private int count;
    private double place;
    private double top4;
    private double won;
    private double adjDelta;
    private List<List<Object>> topUsers;

}
