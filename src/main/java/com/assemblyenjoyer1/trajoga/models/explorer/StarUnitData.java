package com.assemblyenjoyer1.trajoga.models.explorer;

import lombok.Data;

import java.util.List;

@Data
public class StarUnitData {

    public String name;
    public int id;
    public List<Stats> stats;
}
