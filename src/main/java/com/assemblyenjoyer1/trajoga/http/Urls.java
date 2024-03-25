package com.assemblyenjoyer1.trajoga.http;

public enum Urls {
    BASE_URL("https://tactics.tools"),
    BASE_TOOLS_URL("https://d2.tft.tools/"),
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
