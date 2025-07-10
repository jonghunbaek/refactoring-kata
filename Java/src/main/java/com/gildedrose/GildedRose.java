package com.gildedrose;

import com.gildedrose.strategy.QualityStrategyFactory;

class GildedRose {

    public static final int MAXIMUM = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityBySellIn(item);
        }
    }

    public void updateQualityBySellIn(Item item) {
        QualityStrategyFactory.createByName(item.name)
            .updateQualityByItemType(item);
        decreaseSellInExceptSulfuras(item);
    }

    private void decreaseSellInExceptSulfuras(Item item) {
        if (isSulfuras(item.name)) {
            return;
        }

        item.sellIn = item.sellIn - 1;
    }

    private boolean isSulfuras(String name) {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }
}
