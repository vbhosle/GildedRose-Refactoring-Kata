package com.gildedrose;

public class ConcertPass extends ItemDecorator {
    public ConcertPass(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        this.sellIn = this.sellIn - 1;
        if(this.sellIn < 0) {
            this.quality = 0;
            return;
        }
        if(this.quality == ItemDecorator.QUALITY_CAP) return;

        this.quality = this.quality + 1;
        if(this.quality == ItemDecorator.QUALITY_CAP) return;

        if(this.sellIn < 10) this.quality = this.quality + 1;
        if(this.quality == ItemDecorator.QUALITY_CAP) return;

        if(this.sellIn < 5) this.quality = this.quality + 1;
    }
}
