package com.timestudio.usedshop;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by ShenGqiang on 2016/11/16.
 */

public class UsedShopApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //配置图片信息
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) //使用内存缓存
                .cacheOnDisk(true)  //使用硬盘缓存
                .resetViewBeforeLoading(true)  //在加载前清空之前的图片
                .build();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheSize(4 * 1024 * 1024) //内存缓存大小
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(configuration);

    }
}
