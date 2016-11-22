package com.timestudio.usedshop.base;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;
/**
 * Created by shenGqiang on 2016/11/16.
 * @description Presenter 是 model 和 View 之间的桥梁，它通过{@link MvpView}来控制视图的行为。
 * 模型层的数据变化通过事件总线{@link EventBus}来传递给Presenter
 * @param V 为此Presenter所关联的视图
 */

public abstract class MvpPresenter<V extends MvpView> {

    private V view;

    /**
     * Presenter 创建的回调
     * 在Activity中的onCreate()或者Fragment中的onCreate()调用
     *
     */
    public final void onCreate() {

    }

    /**
     * Presenter 和 view 关联
     * 在Activity或者Fragment中的onCreate()
     * @param view
     */
    public final void attachView(V view) {
        this.view = view;
    }

    /**
     * Presenter 和 view 解除关联
     * 在Activity中的onDestory()中调用
     * 在Fragment中的onDestoryView()中调用
     */
    public void detachView() {
        this.view = getNullObject();
    }

    /**
     * 获取与 Presenter 相关联的 view
     * @return
     */
    protected final V getView() {
        return view;
    }
    protected abstract @NonNull V getNullObject();
}
