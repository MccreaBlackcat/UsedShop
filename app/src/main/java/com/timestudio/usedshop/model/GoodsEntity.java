package com.timestudio.usedshop.model;

/**
 * Created by shenGqiang on 2016/11/16.
 * @description 商品信息实体类
 */

public class GoodsEntity {

    private String id;
    /*商品价格*/
    private String price;
    /*商品图片URL*/
    private String page;
    /*商品名称*/
    private String name;
    /*商品在商品表中的主键*/
    private String uuid;
    /*商品类型*/
    private String type;
    /*商品的发布者*/
    private String master;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }
}
