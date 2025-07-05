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

    public void updateQuantityBySellIn() {
        if (isNormalItem()) {
            decreaseQuality();
        } else {
            if (isIncreasable()) {
                increaseQualityByOne();

                if (isBackstagePasses()) {
                    if (sellIn < 11) {
                        if (isIncreasable()) {
                            increaseQualityByOne();
                        }
                    }

                    if (sellIn < 6) {
                        if (isIncreasable()) {
                            increaseQualityByOne();
                        }
                    }
                }
            }
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
                if (isIncreasable()) {
                    increaseQualityByOne();
                }
            }
        }
    }

    private void increaseQualityByOne() {
        quality = quality + 1;
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
