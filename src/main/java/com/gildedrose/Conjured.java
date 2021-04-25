package com.gildedrose;

public class Conjured extends GuildedRoseItem {
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        this.sellIn = this.sellIn - 1;
        this.quality = Math.max(0, this.quality - 2);
    }
}
