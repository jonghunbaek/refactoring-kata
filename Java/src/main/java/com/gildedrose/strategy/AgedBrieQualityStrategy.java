package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieQualityStrategy extends QualityStrategy {

    @Override
    public void updateQualityByItemType(Item item) {
        if (item.sellIn <= MINIMUM) {
            increaseQualityBy(2, item);
        } else {
            increaseQualityBy(1, item);
        }
    }
}
