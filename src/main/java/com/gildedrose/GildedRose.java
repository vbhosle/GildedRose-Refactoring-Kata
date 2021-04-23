package com.gildedrose;

class GildedRose {
    public static final int QUALITY_CAP = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            final Item item = items[i];
            switch (item.name) {
                case "Aged Brie":
                    updateAgedBrie(item);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    updateSulfuras(item);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    updatePasses(item);
                    break;
                default:
                    updateNormal(item);
            }
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

    private void updateNormal(Item item) {
        item.sellIn = item.sellIn - 1;
        if(item.quality == 0) return;

        item.quality = item.quality - 1;
        if(item.quality == 0) return;

        if(item.sellIn <= 0)
            item.quality = item.quality - 1;

    }
}