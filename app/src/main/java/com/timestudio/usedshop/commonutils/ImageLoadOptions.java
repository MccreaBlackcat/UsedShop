package com.timestudio.usedshop.commonutils;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.timestudio.usedshop.R;

/**
 * Created by Power on 2016/11/17.
 */

public class ImageLoadOptions {

    private ImageLoadOptions() {

    }

    private static DisplayImageOptions options_item;

    /**
     * 图片加载使用的option
     */
    public static DisplayImageOptions build_item() {
        if (options_item == null) {
            options_item = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_launcher) //空的uri显示的图片
                    .showImageOnLoading(R.mipmap.ic_launcher) //加载图片时显示
                    .showImageOnFail(R.mipmap.ic_launcher) //加载失败显示的图片
                    .cacheOnDisk(true) //开启硬盘缓存
                    .cacheInMemory(true) //开启内存缓存
                    .resetViewBeforeLoading(true) //加载前重置View
//                    .displayer(new RoundedBitmapDisplayer(0)) //设置圆角
                    .build();
        }
        return options_item;
    }

}
