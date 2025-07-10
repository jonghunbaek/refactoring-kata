package com.gildedrose.strategy;

import com.gildedrose.Item;

public class SulfurasQualityStrategy extends QualityStrategy {

    @Override
    public void updateQualityByItemType(Item item) {
        return;
    }

    @Override
    public void decreaseSellIn(Item item) {
        return;
    }
}
