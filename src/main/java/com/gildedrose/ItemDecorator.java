package com.gildedrose;

//Creating decorator as it is not allowed to touch Item class
public abstract class ItemDecorator extends Item {

    protected static final int QUALITY_CAP = 50;

    public static ItemDecorator create(String name, int sellIn, int quality) {
        switch (name) {
            case "Aged Brie":
                return new AgedBrie(name, sellIn, quality);
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(name, sellIn, quality);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new ConcertPass(name, sellIn, quality);
            case "Conjured":
                return new Conjured(name, sellIn, quality);
            default:
                return new NormalItem(name, sellIn, quality);
        }
    }

    public ItemDecorator(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void update();
}
