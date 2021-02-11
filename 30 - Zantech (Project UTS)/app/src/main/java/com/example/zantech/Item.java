package com.example.zantech;

public class Item {
    private String titleItem;
    private Integer imageItem;
    private Integer priceItem;
    private String deskripsiItem;

    public Item(String titleItem, Integer imageItem, Integer priceItem, String deskripsiItem) {
        setTitleItem(titleItem);
        setImageItem(imageItem);
        setPriceItem(priceItem);
        setDeskripsiItem(deskripsiItem);
    }

    public String getTitleItem() {
        return titleItem;
    }

    public void setTitleItem(String titleItem) {
        this.titleItem = titleItem;
    }

    public Integer getImageItem() {
        return imageItem;
    }

    public void setImageItem(Integer imageItem) {
        this.imageItem = imageItem;
    }

    public Integer getPriceItem() {
        return priceItem;
    }

    public void setPriceItem(Integer priceItem) {
        this.priceItem = priceItem;
    }

    public String getDeskripsiItem() {
        return deskripsiItem;
    }

    public void setDeskripsiItem(String deskripsiItem) {
        this.deskripsiItem = deskripsiItem;
    }
}
