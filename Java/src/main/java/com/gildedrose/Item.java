package com.gildedrose;

public class Item {

    public static final int MINIMUM = 0;
    public static final int MAXIMUM = 50;

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

   @Override
   public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }

    public void updateQualityBySellIn() {
        updateQualityByItemTypeAndSellIn();
        decreaseSellInExceptSulfuras();

        if (hasRemainingSellIn()) {
            return;
        }

        if (isAgedBrie()) {
            increaseQualityBy(1);
        }

        if (isBackstagePasses()) {
            quality = MINIMUM;
        }

        if (isDecreasable() && !isSulfuras()) {
            decreaseQuality();
        }
    }

    private boolean hasRemainingSellIn() {
        return sellIn >= MINIMUM;
    }

    private void decreaseSellInExceptSulfuras() {
        if (isSulfuras()) {
            return;
        }

        sellIn = sellIn - 1;
    }

    private void updateQualityByItemTypeAndSellIn() {
        if (isNormalItem()) {
            decreaseQuality();
        } else {
            increaseQuality();
        }
    }

    private boolean isNormalItem() {
        return !isAgedBrie() && !isBackstagePasses() && !isSulfuras();
    }

    private void decreaseQuality() {
        if (isDecreasable()) {
            quality = quality - 1;
        }
    }

    private boolean isDecreasable() {
        return quality > MINIMUM;
    }

    private void increaseQuality() {
        if (isBackstagePasses()) {
            increaseBackstagePassesQuality();
            return;
        }

        increaseQualityBy(1);
    }

    private void increaseBackstagePassesQuality() {
        if (sellIn < 6) {
            increaseQualityBy(3);
        } else if (sellIn < 11) {
            increaseQualityBy(2);
        } else {
            increaseQualityBy(1);
        }
    }

    private void increaseQualityBy(int qualityToAdd) {
        if (isIncreasable(qualityToAdd)) {
            quality = quality + qualityToAdd;
        }
    }

    private boolean isIncreasable(int qualityToAdd) {
        return quality + qualityToAdd <= MAXIMUM;
    }

    private boolean isSulfuras() {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePasses() {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie() {
        return name.equals("Aged Brie");
    }
}
