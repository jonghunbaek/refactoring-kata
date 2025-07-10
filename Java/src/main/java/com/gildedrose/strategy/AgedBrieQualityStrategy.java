package com.gildedrose.strategy;

import com.gildedrose.Item;

public class AgedBrieQualityStrategy extends QualityStrategy {

    @Override
    public void updateQualityByItemType(Item item) {
        if (isSellInOver(item)) {
            increaseQualityBy(2, item);
        } else {
            increaseQualityBy(1, item);
        }

        decreaseSellIn(item);
    }

    private boolean isSellInOver(Item item) {
        return item.sellIn <= MINIMUM;
    }

    @Override
    public void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
