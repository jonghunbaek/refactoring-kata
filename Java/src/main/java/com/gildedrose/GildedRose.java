package com.gildedrose;

class GildedRose {

    public static final int MINIMUM = 0;
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
        updateQualityByItemType(item);
        decreaseSellInExceptSulfuras(item);
        updateQualityWhenNotRemainSellIn(item);
    }

    private void updateQualityByItemType(Item item) {
        if (isNormalItem(item.name)) {
            updateNormalItemQuality(item);
        } else {
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

    private void updateQualityWhenNotRemainSellIn(Item item) {
        if (hasRemainingSellIn(item.sellIn) || isSulfuras(item.name)) {
            return;
        }

        if (isAgedBrie(item.name)) {
            increaseQualityBy(1, item);
        } else if (isDecreasable(item.quality)) {
            updateNormalItemQuality(item);
        }
    }

    private boolean hasRemainingSellIn(int sellIn) {
        return sellIn >= MINIMUM;
    }

    private void updateSpecialItemQuality(Item item) {
        if (isBackstagePasses(item.name)) {
            updateBackstagePassesQuality(item);
            return;
        }

        increaseQualityBy(1, item);
    }

    private void updateBackstagePassesQuality(Item item) {
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

    private void increaseQualityBy(int qualityToAdd, Item item) {
        if (isIncreasable(item.quality, qualityToAdd)) {
            item.quality = item.quality + qualityToAdd;
        }
    }

    private boolean isIncreasable(int quality, int qualityToAdd) {
        return quality + qualityToAdd <= MAXIMUM;
    }

    private void updateNormalItemQuality(Item item) {
        if (isDecreasable(item.quality)) {
            item.quality = item.quality - 1;
        }
    }

    private boolean isDecreasable(int quality) {
        return quality > MINIMUM;
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
