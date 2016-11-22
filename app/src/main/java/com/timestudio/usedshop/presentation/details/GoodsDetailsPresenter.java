package com.timestudio.usedshop.presentation.details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.timestudio.usedshop.base.MvpPresenter;
import com.timestudio.usedshop.model.GoodsDetailsEntity;
import com.timestudio.usedshop.model.GoodsDetailsResult;
import com.timestudio.usedshop.network.ShopClient;
import com.timestudio.usedshop.network.UICallBack;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * Created by hasee on 2016/11/18.
 */

public class GoodsDetailsPresenter extends MvpPresenter<GoodsDetailsView> {

    private Call call;


    /**
     * 获取商品信息详情
     * @param uuid
     */
    public void getData(String uuid) {
        Log.i("shen", "----------------------:" + uuid);
        call = ShopClient.getInstance().getGoodsDetailsData(uuid);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureInUi(Call call, Exception e) {

            }

            @Override
            public void onResponseInUi(Call call, String body) {
                GoodsDetailsResult result = new Gson().fromJson(body, GoodsDetailsResult.class);
                switch (result.getCode()) {
                    case 1:
                        GoodsDetailsEntity entity = result.getData();
                        ArrayList<String> list = new ArrayList<>();
                        for (int i = 0; i < entity.getPages().size(); i++) {
                            list.add(entity.getPages().get(i).getUri());
                        }
                        getView().setDetailsData(entity);
                        getView().setImageUrlData(list);
                        break;
                }

            }
        });
    }

    @NonNull
    @Override
    protected GoodsDetailsView getNullObject() {
        return null;
    }
}
