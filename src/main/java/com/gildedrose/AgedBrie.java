package com.gildedrose;

public class AgedBrie extends GuildedRoseItem {
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        this.sellIn = this.sellIn - 1;
        if(this.quality == GuildedRoseItem.QUALITY_CAP) return;
        this.quality = this.quality + 1;
    }
}
