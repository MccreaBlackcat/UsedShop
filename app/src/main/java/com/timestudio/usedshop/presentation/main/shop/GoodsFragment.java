package com.timestudio.usedshop.presentation.main.shop;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.timestudio.usedshop.R;
import com.timestudio.usedshop.commonutils.ConnectUtil;
import com.timestudio.usedshop.model.GoodsEntity;
import com.timestudio.usedshop.presentation.details.GoodsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends Fragment implements GoodsView{

    private View view;
    @Bind(R.id.ptr_FL_goods)
    PtrClassicFrameLayout ptrLayout;
    @Bind(R.id.rv_goods)
    RecyclerView recyclerView;
    @Bind(R.id.rv_type)
    RecyclerView recyclerView_type;
    @Bind(R.id.tv_error)
    TextView tv_error;

    private GoodsPresenter presenter;

    private GoodsAdapter adapter;
    private GoodsTypeAdapter typeAdapter;

    private ArrayList<GoodsEntity> goods = new ArrayList<>();
    private String[] type = {"","household","electron","dress","toy","book","gift","other"};
    private int selectType ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new GoodsAdapter();
        typeAdapter = new GoodsTypeAdapter();
        presenter = new GoodsPresenter();
        presenter.onCreate();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods, container, false);
        ButterKnife.bind(this,view);
        presenter.attachView(this);
        if (ConnectUtil.isNetworkAvailable(getContext())) {
            initView();
            recyclerView_type.setAdapter(typeAdapter);
            recyclerView.setAdapter(adapter);
        } else {
            tv_error.setVisibility(View.VISIBLE);
            tv_error.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ConnectUtil.isNetworkAvailable(getContext())) {
                        initView();
                        recyclerView_type.setAdapter(typeAdapter);
                        recyclerView.setAdapter(adapter);
                        presenter.refreshData(1,type[selectType]);
                        tv_error.setVisibility(View.GONE);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //当前页面没有数据的时候，自动刷新
        if (adapter.getItemCount() == 0) {
            ptrLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptrLayout.autoRefresh();
                }
            }, 200);
        }
    }

    private void initView() {
        //设置recyclerView的显示类型
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView_type.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        //对ptrFrameLayout的一些设置
        //使用本对象作为key，记录刷新时间，如果两次下拉刷新的时间间隔太短，则不会触发新的刷新
        ptrLayout.setLastUpdateTimeHeaderRelateObject(this);
        //设置刷新时的背景颜色
        ptrLayout.setBackgroundResource(R.color.colorPrimaryDark);
        //刷新结束后，关闭ptr所需要的时间
        ptrLayout.setDurationToCloseHeader(1000);
        //设置下拉刷新，上滑加载更多
        ptrLayout.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                //上滑加载
                if (ConnectUtil.isNetworkAvailable(getContext())) {
                    presenter.loadDataMore(type[selectType]);
                } else {
                    tv_error.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新
                if (ConnectUtil.isNetworkAvailable(getContext())) {
                    presenter.refreshData(1,type[selectType]);
                } else {
                    tv_error.setVisibility(View.VISIBLE);
                }
            }
        });

        typeAdapter.setListener(new GoodsTypeAdapter.OnItemClickedListener() {
            @Override
            public void onTypeClicked(int typeIndex) {
                selectType = typeIndex;
                typeAdapter.setIndex(typeIndex);
                typeAdapter.notifyDataSetChanged();
                if (ConnectUtil.isNetworkAvailable(getContext())) {
                    presenter.refreshData(1,type[selectType]);
                } else {
                    tv_error.setVisibility(View.VISIBLE);
                }
            }
        });
        adapter.setListener(new GoodsAdapter.OnItemClickedListener() {
            @Override
            public void onPhotoClicked(GoodsEntity goodsEntity) {
                Toast.makeText(getActivity(),goodsEntity.getName(),Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent();
                mIntent.setClass(getActivity(), GoodsDetailsActivity.class);
                mIntent.putExtra("uuid", goodsEntity.getUuid());
                mIntent.putExtra("state", "goods");
                startActivity(mIntent);
            }
        });
    }

    @Override
    public void hideRefresh() {
        ptrLayout.refreshComplete();
    }

    @Override
    public void addRefreshData(List<GoodsEntity> data) {
        if (data != null) {
            adapter.clear();
            adapter.addData(data);
        }
    }

    @Override
    public void loadDataMore() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

}
