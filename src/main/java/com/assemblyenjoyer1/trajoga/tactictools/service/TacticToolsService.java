package com.assemblyenjoyer1.trajoga.tactictools.service;

import com.assemblyenjoyer1.trajoga.http.HttpClientt;
import com.assemblyenjoyer1.trajoga.http.HttpMethod;
import com.assemblyenjoyer1.trajoga.http.Urls;
import com.assemblyenjoyer1.trajoga.models.explorer.ExplorerResponse;
import com.assemblyenjoyer1.trajoga.models.explorer.Items;
import com.assemblyenjoyer1.trajoga.models.set11.Unit;
import com.assemblyenjoyer1.trajoga.models.set11.UnitsData;
import com.assemblyenjoyer1.trajoga.models.set11units.ItemTrios;
import com.assemblyenjoyer1.trajoga.models.set11units.ResponseObject;
import com.assemblyenjoyer1.trajoga.util.ProcessItems;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TacticToolsService {

    private static final Logger log = LoggerFactory.getLogger(TacticToolsService.class);
    private static final HttpClientt client = new HttpClientt();
    private static final Gson gson = new Gson();


    public TacticToolsService() {
    }

    public List<String> getBestItemsForUnit(String unitName){
        List<String> items = new ArrayList<>(5);
        String response = client.sendAsync(Urls.BASE_TOOLS_URL.toString(), HttpMethod.GET, Urls.STATS.toString() + Urls.EMERALD.toString(), null);

        UnitsData data = gson.fromJson(response, UnitsData.class);

        Map<String, Unit> units = data.getUnits();
        for (Map.Entry<String, Unit> entry : units.entrySet()) {
            if(entry.getKey().toLowerCase().contains(unitName.toLowerCase())){
                items = entry.getValue().getTopItems();
            }
        }

        return ProcessItems.transformItemNames(items);
    }

    public List<String> getBestTrioForUnit(String unitName) {
        //https://tactics.tools/units

        String buildId = extractBuildId();
        String buildUrl = Urls.BASE_STATS_URL.toString().replaceAll("champname", unitName.toLowerCase());
        buildUrl = buildUrl.replace("nextjsstuff", buildId);

        String response = client.sendAsync(buildUrl,  HttpMethod.GET, "", null);
        ResponseObject data = gson.fromJson(response, ResponseObject.class);
        ItemTrios itemTrios = data.getPageProps().getUnitData().getItemTrios().get(0);
        return ProcessItems.transformItemNames(itemTrios.getItems());
    }

    public static String getItemByDelta(Urls url, String unitName, String firstItem, String secondItem) {
        if (unitName != null && !unitName.isEmpty()) {
            unitName = unitName.substring(0, 1).toUpperCase() + unitName.substring(1);
        }

        String buildId = extractBuildId();

        String buildUrl = url.toString().replaceAll("champname", unitName);

        if(!firstItem.equals("")){
            buildUrl = url.toString().replaceAll("firstitem", firstItem).replaceAll("champname", unitName);
        }

        if(!secondItem.equals("")){
            buildUrl = url.toString().replaceAll("firstitem", firstItem).replaceAll("seconditem", secondItem).replaceAll("champname", unitName);
        }

        buildUrl = buildUrl.replace("nextjsstuff", buildId);

        String response = client.sendAsync(buildUrl,  HttpMethod.GET, "", null);
        ExplorerResponse data = gson.fromJson(response, ExplorerResponse.class);
        ArrayList<ArrayList<Object>> rawData = data.getItems();

        double currentLowestDelta = Double.MAX_VALUE;
        Items itemWithLowestDelta = null;

        for (int i = 0; i < rawData.size(); i++) {
            Items item = new Items();
            boolean eligible = true;
            item.setName((String) rawData.get(i).get(0));
            item.setType((String) rawData.get(i).get(1));
            if(
                    item.getType().toLowerCase().contains("radiant") ||
                    item.getType().toLowerCase().contains("ornn") ||
                    item.getType().toLowerCase().contains("emblem") ||
                    item.getType().toLowerCase().contains("thiefs") ||
                    item.getType().toLowerCase().contains("inkshadow"))
            {
                eligible = false;
            }

            Type type = new TypeToken<Map<String, Double>>() {}.getType();
            Map<String, Double> dataMap = gson.fromJson(rawData.get(i).get(2).toString(), type);

            if (dataMap.containsKey("delta") && eligible && dataMap.containsKey("count")) {
                double games = dataMap.get("count");
                if (games < 100.0) continue;
                double delta = dataMap.get("delta");
                if (delta < currentLowestDelta) {
                    currentLowestDelta = delta;
                    itemWithLowestDelta = item;
                }
            }
        }

        if(itemWithLowestDelta == null){
            itemWithLowestDelta = new Items("none", "Not enough Stats", null);
        }
        return itemWithLowestDelta.getType();
    }

    public List<String> getBestItemsForUnitByDelta(String unitName){
        List<String> items = new ArrayList<>(3);

        String firstItem = getItemByDelta(Urls.BASE_EXPLORER_URL_3_COST, unitName.toLowerCase(), "", "");
        items.add(firstItem);
        if(firstItem.equals("Not enough Stats")) return ProcessItems.transformItemNames(items);

        String secondItem = getItemByDelta(Urls.BASE_EXPLORER_URL_3_COST_2ND_ITEM, unitName.toLowerCase(), firstItem, "");
        items.add(secondItem);
        if(secondItem.equals("Not enough Stats")) return ProcessItems.transformItemNames(items);

        String thirdItem = getItemByDelta(Urls.BASE_EXPLORER_URL_3_COST_3RD_ITEM, unitName.toLowerCase(), firstItem, secondItem);
        items.add(thirdItem);

        return ProcessItems.transformItemNames(items);
    }

    private static String extractBuildId() {
        String response = client.sendAsync("https://tactics.tools/units",  HttpMethod.GET, "", null);

        Pattern pattern = Pattern.compile("buildId\":\"(.*?)\"");

        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

}
