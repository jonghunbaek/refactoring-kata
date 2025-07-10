package com.gildedrose;

import com.gildedrose.strategy.QualityStrategyFactory;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            QualityStrategyFactory.createByName(item.name)
                .updateQualityByItemType(item);
        }
    }

}
