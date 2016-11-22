package com.timestudio.usedshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shenGqiang on 2016/11/17.
 */

public class GoodsResult {

    private int code;
    @SerializedName("msg")
    private String message;
    private List<GoodsEntity> datas;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<GoodsEntity> getData() {
        return datas;
    }
}
