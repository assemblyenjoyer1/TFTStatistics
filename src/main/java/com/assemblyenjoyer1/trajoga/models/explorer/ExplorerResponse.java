package com.assemblyenjoyer1.trajoga.models.explorer;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ExplorerResponse {

    public Base base;
    public ArrayList<ArrayList<Object>> units;
    public ArrayList<ArrayList<Object>> star_units;
    public ArrayList<ArrayList<Object>> traits;
    public ArrayList<ArrayList<Object>> augments;
    public ArrayList<ArrayList<Object>> items;
}
