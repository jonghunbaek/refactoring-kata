package com.gildedrose.strategy;

import com.gildedrose.Item;

public abstract class QualityStrategy {

    public abstract void updateQualityByItemType(Item item);
}
