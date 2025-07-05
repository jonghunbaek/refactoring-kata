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
        if (!isAgedBrie() && !isBackstagePasses()) {
            if (!isSulfuras()) {
                if (isDecreasable()) {
                    decreaseQuality();
                }
            }
        } else {
            if (quality < MAXIMUM) {
                quality = quality + 1;

                if (isBackstagePasses()) {
                    if (sellIn < 11) {
                        if (quality < MAXIMUM) {
                            quality = quality + 1;
                        }
                    }

                    if (sellIn < 6) {
                        if (quality < MAXIMUM) {
                            quality = quality + 1;
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
                if (quality < MAXIMUM) {
                    quality = quality + 1;
                }
            }
        }
    }

    private void decreaseQuality() {
        quality = quality - 1;
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
