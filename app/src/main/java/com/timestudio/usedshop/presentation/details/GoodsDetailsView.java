package com.timestudio.usedshop.presentation.details;

import com.timestudio.usedshop.base.MvpView;
import com.timestudio.usedshop.model.GoodsDetailsEntity;

import java.util.ArrayList;

/**
 * Created by Power on 2016/11/18.
 */

public interface GoodsDetailsView extends MvpView {


    /**
     * 设置图片URL数据
     */
    void setImageUrlData(ArrayList<String> list);

    /**
     * 显示Details实体类信息到UI
     */
    void setDetailsData(GoodsDetailsEntity detailsEntity);

    GoodsDetailsView NULL = new GoodsDetailsView() {

        @Override
        public void setImageUrlData(ArrayList<String> list) {

        }

        @Override
        public void setDetailsData(GoodsDetailsEntity detailsEntity) {

        }
    };
}
