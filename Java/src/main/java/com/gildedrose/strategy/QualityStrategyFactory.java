package com.gildedrose.strategy;

public class QualityStrategyFactory {

    public static QualityStrategy createByName(String name) {
        switch (name) {
            case "Backstage passes to a TAFKAL80ETC concert" :
                return new BackstagePassesQualityStrategy();
            case "Sulfuras, Hand of Ragnaros" :
                return new SulfurasQualityStrategy();
            case "Aged Brie" :
                return new AgedBrieQualityStrategy();
            default:
                return new DefaultQualityStrategy();
        }
    }
}
