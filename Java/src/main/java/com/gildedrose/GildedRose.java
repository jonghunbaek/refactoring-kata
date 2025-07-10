package com.gildedrose;

import com.gildedrose.strategy.QualityStrategy;
import com.gildedrose.strategy.QualityStrategyFactory;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            QualityStrategy strategy = QualityStrategyFactory.createByName(item.name);
            strategy.updateQualityByItemType(item);
        }
    }

}
