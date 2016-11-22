package com.timestudio.usedshop.presentation.main.shop;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.timestudio.usedshop.base.MvpPresenter;
import com.timestudio.usedshop.model.GoodsResult;
import com.timestudio.usedshop.network.ShopClient;
import com.timestudio.usedshop.network.UICallBack;

import okhttp3.Call;


/**
 * Created by hasee on 2016/11/16.
 */

public class GoodsPresenter extends MvpPresenter<GoodsView> {

    //OkHttp要使用的call对象
    private Call call;
    private int pageInt = 1;


    /**
     * 刷新数据
     * @param pageNo
     * @param type
     */
    public void refreshData(int pageNo, String type) {
        call = ShopClient.getInstance().getAllGoodsData(pageNo, type);
        call.enqueue(new UICallBack() {
            @Override
            public void onFailureInUi(Call call, Exception e) {
                Log.i("shen", "----------------" + e);
            }
            @Override
            public void onResponseInUi(Call call, String body) {
                GoodsResult result = new Gson().fromJson(body, GoodsResult.class);

                switch (result.getCode()) {
                    case 1:
                        getView().addRefreshData(result.getData());
                        getView().hideRefresh();
                        pageInt = 2;
                        break;
                    case 2:
                        getView().hideRefresh();
                        break;
                }
            }
        });

    }

    /**
     * 上滑加载更多数据
     */
    public void loadDataMore(String type) {
        getView().hideRefresh();
    }

    @NonNull
    @Override
    protected GoodsView getNullObject() {
        return GoodsView.NULL;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (call != null) {
            call.cancel();
        }
    }
}
