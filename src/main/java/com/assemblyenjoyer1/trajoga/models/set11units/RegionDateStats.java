package com.assemblyenjoyer1.trajoga.models.set11units;

import lombok.Data;

import java.util.List;

@Data
public class RegionDateStats {

    private List<String> id;
    private int count;
    private double rate;
    private double place;
    private double top4;
    private double won;

}
