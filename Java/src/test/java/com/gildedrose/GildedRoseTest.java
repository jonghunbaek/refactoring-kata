package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @DisplayName("일반 아이템은 하루가 지날때마다 판매기간, 가치가 1씩 감소한다.")
    @Test
    void updateQualityWhenNormalItem() {
        Item[] items = new Item[] {new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("+5 Dexterity Vest", app.items[0].name),
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(19, app.items[0].quality)
        );
    }

    @DisplayName("일반 아이템은 판매일수가 0미만이면 Quality의 값은 2배로 떨어진다.")
    @Test
    void updateQualityWhenNotRemainSellIn() {
        Item[] items = new Item[] {new Item("+5 Dexterity Vest", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("+5 Dexterity Vest", app.items[0].name),
            () -> assertEquals(-1, app.items[0].sellIn),
            () -> assertEquals(18, app.items[0].quality)
        );

        app.updateQuality();

        assertAll(
            () -> assertEquals("+5 Dexterity Vest", app.items[0].name),
            () -> assertEquals(-2, app.items[0].sellIn),
            () -> assertEquals(16, app.items[0].quality)
        );
    }

    @DisplayName("아이템의 가치는 음수의 값이 되지 않는다.")
    @Test
    void updateQualityWhenNormalItem2() {
        Item[] items = new Item[] {new Item("+5 Dexterity Vest", 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("+5 Dexterity Vest", app.items[0].name),
            () -> assertEquals(0, app.items[0].sellIn),
            () -> assertEquals(0, app.items[0].quality)
        );
    }

    @DisplayName("Aged Brie 아이템은 하루가 지날때마다 가치가 1씩 증가한다.")
    @Test
    void updateQualityWhenAgedBrieItem() {
        Item[] items = new Item[] {new Item("Aged Brie", 2, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Aged Brie", app.items[0].name),
            () -> assertEquals(1, app.items[0].sellIn),
            () -> assertEquals(1, app.items[0].quality)
        );
    }

    @DisplayName("Sulfuras 아이템은 시간이 지나도 판매기간과 가치가 감소/증가하지 않는다.")
    @Test
    void updateQualityWhenSulfurasItem() {
        Item[] items = new Item[] {
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name),
            () -> assertEquals(0, app.items[0].sellIn),
            () -> assertEquals(80, app.items[0].quality),

            () -> assertEquals("Sulfuras, Hand of Ragnaros", app.items[1].name),
            () -> assertEquals(-1, app.items[1].sellIn),
            () -> assertEquals(80, app.items[1].quality)
        );
    }

    @DisplayName("Backstage passes 아이템은 판매기간이 11일 이상일 경우 하루가 지나면 가치가 1증가한다.")
    @Test
    void updateQualityWhenBackstagePassesItemPhase1() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name),
            () -> assertEquals(10, app.items[0].sellIn),
            () -> assertEquals(21, app.items[0].quality)
        );
    }

    @DisplayName("Backstage passes 아이템은 판매기간이 10일 이하, 6일 이상일 경우 하루가 지나면 가치가 2증가한다.")
    @Test
    void updateQualityWhenBackstagePassesItemPhase2() {
        Item[] items = new Item[] {
            new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name),
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(22, app.items[0].quality),

            () -> assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[1].name),
            () -> assertEquals(5, app.items[1].sellIn),
            () -> assertEquals(22, app.items[1].quality)
        );
    }

    @DisplayName("Backstage passes 아이템은 판매기간이 5일 이하일 경우 하루가 지나면 가치가 3증가한다.")
    @Test
    void updateQualityWhenBackstagePassesItemPhase3() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name),
            () -> assertEquals(4, app.items[0].sellIn),
            () -> assertEquals(23, app.items[0].quality)
        );
    }

    @DisplayName("Backstage passes 아이템은 판매기간이 0일 경우 가치는 0으로 감소한다.")
    @Test
    void updateQualityWhenBackstagePassesItemPhase4() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name),
            () -> assertEquals(-1, app.items[0].sellIn),
            () -> assertEquals(0, app.items[0].quality)
        );
    }

    @DisplayName("Conjured 아이템은 하루가 지날때마다 판매기간이 1, 가치가 2씩 감소한다.")
    @Test
    void updateQualityWhenConjuredItem() {
        Item[] items = new Item[] {new Item("Conjured Mana Cake", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertAll(
            () -> assertEquals("Conjured Mana Cake", app.items[0].name),
            () -> assertEquals(9, app.items[0].sellIn),
            () -> assertEquals(18, app.items[0].quality)
        );
    }
}
