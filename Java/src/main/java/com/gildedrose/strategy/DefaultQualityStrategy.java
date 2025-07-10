package com.gildedrose.strategy;

import com.gildedrose.Item;

public class DefaultQualityStrategy extends QualityStrategy {

    @Override
    protected void updateQualityByItemType(Item item) {
        if (item.sellIn <= MINIMUM) {
            decreaseQualityBy(2, item);
        } else {
            decreaseQualityBy(1, item);
        }
    }

    @Override
    public void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
