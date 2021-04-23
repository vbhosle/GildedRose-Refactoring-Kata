package com.gildedrose;

public class NormalItem extends ItemDecorator {
    public NormalItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        this.sellIn = this.sellIn - 1;
        if(this.quality == 0) return;

        this.quality = this.quality - 1;
        if(this.quality == 0) return;

        if(this.sellIn <= 0)
            this.quality = this.quality - 1;
    }
}
