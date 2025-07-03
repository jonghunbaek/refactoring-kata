package com.gildedrose;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item[] items;

    @BeforeEach
    void setUp() {
        items = new Item[] {
            new Item("+5 Dexterity Vest", 10, 20),
            new Item("Aged Brie", 2, 0),
            new Item("Elixir of the Mongoose", 5, 7),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
            new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
            new Item("Conjured Mana Cake", 3, 6)
        };
    }

    @DisplayName("일부 아이템을 제외한 모든 아이템은 하루가 지날때마다 판매기간, 가치가 1씩 감소한다.")
    @Test
    void updateQualityWhenNormalItem() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("+5 Dexterity Vest", app.items[0].name),
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(19, app.items[0].quality)
        );
    }

    @DisplayName("Aged Brie 아이템은 하루가 지날때마다 가치가 1씩 증가한다.")
    @Test
    void updateQualityWhenAgedBrieItem() {
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Aged Brie", app.items[1].name),
            () -> assertEquals(1, app.items[1].sellIn),
            () -> assertEquals(1, app.items[1].quality)
        );
    }
}
