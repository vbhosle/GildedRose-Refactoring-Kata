package com.gildedrose;

import org.junit.jupiter.api.Test;

import static com.gildedrose.ItemDecorator.QUALITY_CAP;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void normalItemDecreasesInQualityBy1() {
        Item[] items = new Item[] { new Item("Normal", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(19, app.items[0].quality, "quality of normal item decreases by 1");
        assertEquals(9, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void normalItemDecreasesInQualityBy2PostSellIn() {
        Item[] items = new Item[] { new Item("Normal", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality, "quality of normal item decreases by 2 post sellIn");
        assertEquals(-1, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void normalItemQualityNeverGoesNegative() {
        Item[] items = new Item[] { new Item("Normal", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never -ve");
        assertEquals(9, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void normalItemQualityNeverGoesNegativePostSellIn() {
        Item[] items = new Item[] {
                new Item("Normal", -1, 0),
                new Item("Normal", -1, 1)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never -ve");
        assertEquals(-2, app.items[0].sellIn, "sellIn decreases by 1");

        assertEquals(0, app.items[1].quality, "quality is never -ve");
        assertEquals(-2, app.items[1].sellIn, "sellIn decreases by 1");
    }

    @Test
    void agedBrieIncreasesInQualityBy1CappedAt50() {
        Item[] items = new Item[] {
                new Item("Aged Brie", 2, 0),
                new Item("Aged Brie", 4, 50),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality, "quality of Aged Brie by 1");
        assertEquals(1, app.items[0].sellIn, "sellIn decreases by 1");

        assertEquals(QUALITY_CAP, app.items[1].quality, "quality of Aged Brie capped at 50");
        assertEquals(3, app.items[1].sellIn, "sellIn decreases by 1");
    }

    @Test
    void sulfrasNeverToBeSoldOrDecreaseInQuality() {
        Item[] items = new Item[] {
                new Item("Sulfuras, Hand of Ragnaros", 2, 80),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality, "quality of Sulfuras unchanged");
        assertEquals(2, app.items[0].sellIn, "sellIn of Sulfuras unchanged");

        assertEquals(80, app.items[1].quality, "quality of Sulfuras unchanged");
        assertEquals(0, app.items[1].sellIn, "sellIn of Sulfuras unchanged");
    }

    @Test
    void backStagePassesIncreaseInQualityBy1WhenMoreThan10DaysLeftCappedAt50() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 12, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(21, app.items[0].quality, "quality of back stage pass increases by 1");
        assertEquals(14, app.items[0].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(21, app.items[1].quality, "quality of back stage pass increases by 1");
        assertEquals(11, app.items[1].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(QUALITY_CAP, app.items[2].quality, "quality of back stage pass capped at 50");
        assertEquals(14, app.items[2].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(21, app.items[3].quality, "quality of back stage pass increases by 1");
        assertEquals(10, app.items[3].sellIn, "sellIn of back stage pass decreases by 1");
    }

    @Test
    void backStagePassesIncreaseInQualityBy2When6To10DaysLeftCappedAt50() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 2, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        assertEquals(22, app.items[0].quality, "quality of back stage pass increases by 2");
        assertEquals(9, app.items[0].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(23, app.items[1].quality, "quality of back stage pass increases by 2");
        assertEquals(4, app.items[1].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(QUALITY_CAP, app.items[2].quality, "quality of back stage pass capped at 50");
        assertEquals(4, app.items[2].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(23, app.items[3].quality, "quality of back stage pass increases by 2");
        assertEquals(1, app.items[3].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(22, app.items[4].quality, "quality of back stage pass increases by 2");
        assertEquals(5, app.items[4].sellIn, "sellIn of back stage pass decreases by 1");
    }

    @Test
    void backStagePassesIncreaseInQualityBy3When1To5DaysLeftCappedAt50() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50),
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality, "quality of back stage pass increases by 3");
        assertEquals(4, app.items[0].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(23, app.items[1].quality, "quality of back stage pass increases by 3");
        assertEquals(0, app.items[1].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(QUALITY_CAP, app.items[2].quality, "quality of back stage pass capped at 50");
        assertEquals(0, app.items[2].sellIn, "sellIn of back stage pass decreases by 1");
    }

    @Test
    void backStagePassesQualityDropsTo0PostConcert() {
        Item[] items = new Item[] {
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(23, app.items[0].quality, "quality of back stage pass drops to 0");
        assertEquals(0, app.items[0].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(0, app.items[1].quality, "quality of back stage pass drops to 0");
        assertEquals(-1, app.items[1].sellIn, "sellIn of back stage pass decreases by 1");

        assertEquals(0, app.items[2].quality, "quality of back stage pass drops to 0");
        assertEquals(-2, app.items[2].sellIn, "sellIn of back stage pass decreases by 1");
    }

    @Test
    void conjuredDecreasesInQualityBy2() {
        Item[] items = new Item[] { new Item("Conjured", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality, "quality of normal item decreases by 1");
        assertEquals(9, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void conjuredDecreasesInQualityBy2PostSellIn() {
        Item[] items = new Item[] { new Item("Conjured", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(18, app.items[0].quality, "quality of normal item decreases by 2 post sellIn");
        assertEquals(-1, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void conjuredQualityNeverGoesNegative() {
        Item[] items = new Item[] { new Item("Conjured", 10, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never -ve");
        assertEquals(9, app.items[0].sellIn, "sellIn decreases by 1");
    }

    @Test
    void conjuredQualityNeverGoesNegativePostSellIn() {
        Item[] items = new Item[] {
                new Item("Conjured", -1, 0),
                new Item("Conjured", -1, 1)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality, "quality is never -ve");
        assertEquals(-2, app.items[0].sellIn, "sellIn decreases by 1");

        assertEquals(0, app.items[1].quality, "quality is never -ve");
        assertEquals(-2, app.items[1].sellIn, "sellIn decreases by 1");
    }
}
