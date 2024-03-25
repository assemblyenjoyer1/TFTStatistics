package com.assemblyenjoyer1.trajoga.models.set11;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UnitsData {
    private int totalEntries;
    private long lastUpdated;
    private Map<String, Unit> units;
    private Map<String, Trait> traits;
    private List<Item> items;
    private List<List<Object>> exalteds;

}
