package com.assemblyenjoyer1.trajoga.util;

import java.util.ArrayList;
import java.util.List;

public class ProcessItems {


    public static List<String> transformItemNames(List<String> items){
        List<String> newItems = new ArrayList<>();
        for(String item: items){
            switch(item){
                case "RapidFireCannon":
                    newItems.add("RedBuff");
                    break;
                case "Leviathan":
                    newItems.add("Nashors");
                    break;
                case "MadredsBloodrazor":
                    newItems.add("GiantSlayer");
                    break;
                case "UnstableConcoction":
                    newItems.add("HandOfJustice");
                    break;
                case "NightHarvester":
                    newItems.add("SteadfastHeart");
                    break;
                case "FrozenHeart":
                    newItems.add("ProtectorsVow");
                    break;
                default:
                    newItems.add(item);
                    break;
            }
        }
        return newItems;
    }

}
