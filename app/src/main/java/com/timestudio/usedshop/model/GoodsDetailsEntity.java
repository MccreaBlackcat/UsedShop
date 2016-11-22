package com.timestudio.usedshop.model;

import java.util.ArrayList;

/**
 * Created by Power on 2016/11/18.
 */

public class GoodsDetailsEntity {

    private String name;            //商品名称
    private String type;            //商品类型
    private String price;           //商品价格
    private String description;     //商品价格
    private String master;           //商品发布者
    private String uuid;            //商品表中主键
    private ArrayList<ImageUri> pages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public ArrayList<ImageUri> getPages() {
        return pages;
    }

    public void setPages(ArrayList<ImageUri> pages) {
        this.pages = pages;
    }


    public class ImageUri {

        private String uri;

        public String getUri() {
            return uri;
        }
    }

}
