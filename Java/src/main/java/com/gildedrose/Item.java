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
        if (isNormalItem()) {
            decreaseQuality();
        } else {
            increaseQuality();
        }

        if (!isSulfuras()) {
            sellIn = sellIn - 1;
        }

        if (sellIn < MINIMUM) {
            if (!isAgedBrie()) {
                if (!isBackstagePasses()) {
                    if (isDecreasable()) {
                        if (!isSulfuras()) {
                            decreaseQuality();
                        }
                    }
                } else {
                    quality = MINIMUM;
                }
            } else {
                increaseQualityBy();
            }
        }
    }

    private void increaseQuality() {
        increaseQualityBy();

        if (isBackstagePasses()) {
            increaseBackstagePassesQuality();
        }
    }

    private void increaseBackstagePassesQuality() {
        if (sellIn < 11) {
            increaseQualityBy();
        }

        if (sellIn < 6) {
            increaseQualityBy();
        }
    }

    private void increaseQualityBy() {
        if (isIncreasable()) {
            quality = quality + 1;
        }
    }

    private boolean isIncreasable() {
        return quality < MAXIMUM;
    }

    private boolean isNormalItem() {
        return !isAgedBrie() && !isBackstagePasses() && !isSulfuras();
    }

    private void decreaseQuality() {
        if (isDecreasable()) {
            quality = quality - 1;
        }
    }

    private boolean isSulfuras() {
        return name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isDecreasable() {
        return quality > MINIMUM;
    }

    private boolean isBackstagePasses() {
        return name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrie() {
        return name.equals("Aged Brie");
    }
}
