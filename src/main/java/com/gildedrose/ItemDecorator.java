package com.gildedrose;

//Creating decorator as it is not allowed to touch Item class
public abstract class ItemDecorator extends Item {

    protected static final int QUALITY_CAP = 50;

    public ItemDecorator(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public abstract void update();
}
