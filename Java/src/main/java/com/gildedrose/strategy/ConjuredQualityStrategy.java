package com.gildedrose.strategy;

import com.gildedrose.Item;

public class ConjuredQualityStrategy extends QualityStrategy {

    @Override
    protected void updateQualityByItemType(Item item) {
        decreaseQualityBy(2, item);
    }

    @Override
    protected void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
