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
            updateQualityBySellIn(item);
        }
    }

    public void updateQualityBySellIn(Item item) {
        QualityStrategy strategy = QualityStrategyFactory.createByName(item.name);
        strategy.updateQualityByItemType(item);
        strategy.decreaseSellIn(item);
    }
}
