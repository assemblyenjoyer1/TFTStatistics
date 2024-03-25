package com.assemblyenjoyer1.trajoga.models.set11;

import lombok.Data;

import java.util.List;

@Data
public class Unit {
    private int count;
    private double place;
    private double top4;
    private double won;
    private List<String> topItems;
    private int starCount;
    private double starPlace;
    private double starTop4;
    private double starWon;
}
