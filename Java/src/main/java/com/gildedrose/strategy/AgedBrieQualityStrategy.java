package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieQualityStrategy extends QualityStrategy {

    @Override
    protected void updateQualityByItemType(Item item) {
        if (isSellInOver(item)) {
            increaseQualityBy(2, item);
        } else {
            increaseQualityBy(1, item);
        }
    }

    @Override
    public void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
