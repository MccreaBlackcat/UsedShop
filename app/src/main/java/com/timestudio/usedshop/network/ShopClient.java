package com.timestudio.usedshop.network;


import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by shenGqiang on 2016/11/17.
 * @description 单例模式,所有的网络连接请求
 */

public class ShopClient {

    private OkHttpClient okHttpClient;

    private ShopClient() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    private static ShopClient shopClient;

    public static ShopClient getInstance() {
        if (shopClient == null) {
            shopClient = new ShopClient();
        }
        return shopClient;
    }

    /**
     * 获取所有商品信息
     */
    public Call getAllGoodsData(int pageNo, String type) {
        RequestBody requestBody = new FormBody.Builder()
                .add("pageNo", String.valueOf(pageNo))
                .add("type", type)
                .build();
        Request request = new Request.Builder()
                .url(ShopApi.BASE_URL + ShopApi.ALL_GOODS)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

    /**
     * 获取商品信息详情
     */
    public Call getGoodsDetailsData(String uuid) {
        RequestBody requestBody = new FormBody.Builder()
                .add("uuid", uuid)
                .build();
        Request request = new Request.Builder()
                .url(ShopApi.BASE_URL + ShopApi.DETAIL)
                .post(requestBody)
                .build();
        return okHttpClient.newCall(request);
    }

}
