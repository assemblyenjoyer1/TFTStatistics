package com.assemblyenjoyer1.trajoga.tactictools.service;

import com.assemblyenjoyer1.trajoga.http.HttpClientt;
import com.assemblyenjoyer1.trajoga.http.HttpMethod;
import com.assemblyenjoyer1.trajoga.http.Urls;
import com.assemblyenjoyer1.trajoga.models.set11.Unit;
import com.assemblyenjoyer1.trajoga.models.set11.UnitsData;
import com.assemblyenjoyer1.trajoga.util.ProcessItems;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TacticToolsService {

    private static final Logger log = LoggerFactory.getLogger(TacticToolsService.class);
    private static final HttpClientt client = new HttpClientt();
    private static final Gson gson = new Gson();


    public TacticToolsService() {
    }

    public List<String> getBestItemsForUnit(String unitName){
        List<String> items = new ArrayList<>(5);
        String response = client.sendAsync(HttpMethod.GET, Urls.STATS.toString() + Urls.EMERALD.toString(), null);

        UnitsData data = gson.fromJson(response, UnitsData.class);

        Map<String, Unit> units = data.getUnits();
        for (Map.Entry<String, Unit> entry : units.entrySet()) {
            if(entry.getKey().toLowerCase().contains(unitName.toLowerCase())){
                items = entry.getValue().getTopItems();
            }
        }

        return ProcessItems.transformItemNames(items);
    }

}
