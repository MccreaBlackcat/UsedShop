package com.timestudio.usedshop.presentation.main.shop;

import com.timestudio.usedshop.base.MvpView;
import com.timestudio.usedshop.model.GoodsEntity;

import java.util.List;

/**
 * Created by shenGqiang on 2016/11/16.
 */

public interface GoodsView extends MvpView {

    /**
     * 数据刷新 --隐藏下拉上滑的刷新视图
     */
    void hideRefresh();

    /**
     * 添加更多数据
     */
    void addRefreshData(List<GoodsEntity> data);

    /**
     * 上滑加载更多数据
     */
    void loadDataMore();


    GoodsView NULL = new GoodsView() {
        @Override
        public void hideRefresh() {

        }

        @Override
        public void addRefreshData(List<GoodsEntity> data) {

        }


        @Override
        public void loadDataMore() {

        }
    };
}
