package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackstagePassesQualityStrategy extends QualityStrategy {

    @Override
    public void updateQualityByItemType(Item item) {
        if (item.sellIn <= MINIMUM) {
            item.quality = MINIMUM;
        } else if (item.sellIn < 6) {
            increaseQualityBy(3, item);
        } else if (item.sellIn < 11) {
            increaseQualityBy(2, item);
        } else {
            increaseQualityBy(1, item);
        }
    }
}
