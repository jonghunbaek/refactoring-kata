package com.gildedrose.strategy;

import com.gildedrose.Item;

public abstract class QualityStrategy {

    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 50;

    public abstract void updateQualityByItemType(Item item);

    protected abstract void decreaseSellIn(Item item);

    public void updateQualityByStrategy(Item item) {
        updateQualityByItemType(item);
        decreaseSellIn(item);
    }

    protected void increaseQualityBy(int qualityToAdd, Item item) {
        if (isIncreasable(item.quality, qualityToAdd)) {
            item.quality = item.quality + qualityToAdd;
        } else {
            item.quality = MAXIMUM;
        }
    }

    protected boolean isIncreasable(int quality, int qualityToAdd) {
        return quality + qualityToAdd <= MAXIMUM;
    }

    protected void decreaseQualityBy(int qualityToSubtract, Item item) {
        if (isDecreasable(item.quality, qualityToSubtract)) {
            item.quality = item.quality - qualityToSubtract;
        } else {
            item.quality = MINIMUM;
        }
    }

    protected boolean isDecreasable(int quality, int qualityToSubtract) {
        return quality - qualityToSubtract >= MINIMUM;
    }
}
