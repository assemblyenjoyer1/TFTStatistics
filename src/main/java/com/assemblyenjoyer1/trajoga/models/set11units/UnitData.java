package com.assemblyenjoyer1.trajoga.models.set11units;

import lombok.Data;

import java.util.List;

@Data
public class UnitData {

    private String unitId;
    private int starLevel;
    private Base base;
    private List<Integer> placeDistribution;
    private List<RegionStats> regionStats;
    private List<DateStats> dateStats;
    private List<RegionDateStats> regionDateStats;
    private List<PortalStats> portalStats;
    private List<Units> units;
    private List<Traits> traits;
    private List<Aug1s> aug1s;
    private List<Aug2s> aug2s;
    private List<Aug3s> aug3s;
    List<Object> unitMatchups;
    List<Object> traitMatchups;
    private double baseAdjDelta;
    List<Items> items;
    List<ItemPairs> itemPairs;
    List<ItemTrios> itemTrios;
    List<StarLevelData> starLevelData;
    List<ItemCountData> itemCountData;
}
