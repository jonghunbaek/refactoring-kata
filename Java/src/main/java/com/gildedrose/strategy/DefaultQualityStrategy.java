package com.gildedrose.strategy;

import com.gildedrose.Item;

public class DefaultQualityStrategy extends QualityStrategy {

    @Override
    public void updateQualityByItemType(Item item) {
        if (item.sellIn <= MINIMUM) {
            decreaseQualityBy(2, item);
        } else {
            decreaseQualityBy(1, item);
        }
    }
}
