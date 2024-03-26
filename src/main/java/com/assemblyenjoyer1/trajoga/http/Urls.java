package com.assemblyenjoyer1.trajoga.http;

public enum Urls {
    BASE_URL("https://tactics.tools"),
    BASE_STATS_URL("https://tactics.tools/_next/data/nextjsstuff/en/units/champname.json?slug=champname"), // bad URL to gather items, pairs and trios per unit (sadly I used this one)
    BASE_TOOLS_URL("https://d2.tft.tools/"),
    BASE_UNIT_STATS_URL("https://d2.tft.tools/stats2/unit/1100/TFT11_champname/14060/3"), // URL to gather items, pairs and trios per unit

    BASE_EXPLORER_URL_3_COST("https://d2.tft.tools/explorer-data/14060/1/u-TFT11_champname-3"),
    BASE_EXPLORER_URL_3_COST_2ND_ITEM("https://d2.tft.tools/explorer-data/14060/1/u-TFT11_champname-3/i-firstitem-TFT11_champname-3"),
    BASE_EXPLORER_URL_3_COST_3RD_ITEM("https://d2.tft.tools/explorer-data/14060/1/u-TFT11_champname-3/i-firstitem-TFT11_champname-3/i-seconditem-TFT11_champname-3"),

    STATS("stats2/general/1100/14060/"),
    GOLD("2"),
    EMERALD("1"),
    DIAMOND("0")
    ;

    private String text;

    Urls(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return text;
    }
}
