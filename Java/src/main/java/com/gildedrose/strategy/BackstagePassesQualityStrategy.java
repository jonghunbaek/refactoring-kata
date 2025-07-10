package com.gildedrose.strategy;

import com.gildedrose.Item;

public class BackstagePassesQualityStrategy extends QualityStrategy {

    @Override
    protected void updateQualityByItemType(Item item) {
        if (isSellInOver(item)) {
            item.quality = MINIMUM;
        } else if (isSellInLessThanSixDays(item)) {
            increaseQualityBy(3, item);
        } else if (isSellInLessThanElevenDays(item)) {
            increaseQualityBy(2, item);
        } else {
            increaseQualityBy(1, item);
        }
    }

    private boolean isSellInLessThanSixDays(Item item) {
        return item.sellIn < 6;
    }

    private boolean isSellInLessThanElevenDays(Item item) {
        return item.sellIn < 11;
    }

    @Override
    public void decreaseSellIn(Item item) {
        item.sellIn--;
    }
}
