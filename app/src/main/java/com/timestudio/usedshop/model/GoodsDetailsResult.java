package com.timestudio.usedshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hasee on 2016/11/18.
 */

public class GoodsDetailsResult {

    private int code;
    @SerializedName("msg")
    private String message;
    private GoodsDetailsEntity datas;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public GoodsDetailsEntity getData() {
        return datas;
    }
}
