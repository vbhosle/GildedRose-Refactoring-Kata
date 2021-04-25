package com.gildedrose;

import static com.gildedrose.GuildedRoseItem.QUALITY_CAP;

class GildedRose {
    GuildedRoseItem[] items;

    public GildedRose(Item[] items) {
        this.items = new GuildedRoseItem[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = GuildedRoseItem.create(items[i].name, items[i].sellIn, items[i].quality);
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].update();
        }
    }

    private void updatePasses(Item item) {
        item.sellIn = item.sellIn - 1;
        if(item.sellIn < 0) {
            item.quality = 0;
            return;
        }
        if(item.quality == QUALITY_CAP) return;

        item.quality = item.quality + 1;
        if(item.quality == QUALITY_CAP) return;

        if(item.sellIn < 10) item.quality = item.quality + 1;
        if(item.quality == QUALITY_CAP) return;

        if(item.sellIn < 5) item.quality = item.quality + 1;
    }

    private void updateSulfuras(Item item) {

    }

    private void updateAgedBrie(Item item) {
        item.sellIn = item.sellIn - 1;
        if(item.quality == QUALITY_CAP) return;
        item.quality = item.quality + 1;
    }
}