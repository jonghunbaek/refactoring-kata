package com.gildedrose;

import com.gildedrose.strategy.QualityStrategyFactory;

class GildedRose {

    public static final int MAXIMUM = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityBySellIn(item);
        }
    }

    public void updateQualityBySellIn(Item item) {
        QualityStrategyFactory.createByName(item.name)
            .updateQualityByItemType(item);
        updateQualityByItemType(item);
        decreaseSellInExceptSulfuras(item);
    }

    private void updateQualityByItemType(Item item) {
        if (!isNormalItem(item.name)) {
            updateSpecialItemQuality(item);
        }
    }

    private boolean isNormalItem(String name) {
        return !isAgedBrie(name) && !isBackstagePasses(name) && !isSulfuras(name);
    }

    private void decreaseSellInExceptSulfuras(Item item) {
        if (isSulfuras(item.name)) {
            return;
        }

        item.sellIn = item.sellIn - 1;
    }

    private void updateSpecialItemQuality(Item item) {
        if (!isAgedBrie(item.name) && !isBackstagePasses(item.name)) {
            increaseQualityBy(1, item);
        }
    }

    private void increaseQualityBy(int qualityToAdd, Item item) {
        if (isIncreasable(item.quality, qualityToAdd)) {
            item.quality = item.quality + qualityToAdd;
        } else if (!isSulfuras(item.name)) {
            item.quality = MAXIMUM;
        }
    }

    private boolean isIncreasable(int quality, int qualityToAdd) {
        return quality + qualityToAdd <= MAXIMUM;
    }

    private boolean isSulfuras(String name) {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePasses(String name) {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie(String name) {
        return name.equals("Aged Brie");
    }
}
