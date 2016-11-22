package com.timestudio.usedshop.presentation.details;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timestudio.usedshop.R;
import com.timestudio.usedshop.commonutils.ImageLoadOptions;
import com.timestudio.usedshop.model.GoodsDetailsEntity;
import com.timestudio.usedshop.network.ShopApi;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

public class GoodsDetailsActivity extends AppCompatActivity implements GoodsDetailsView{

    @Bind(R.id.toolbar_goodsDetail)
    Toolbar toolbar;
    @Bind(R.id.vp_goodsDetail)
    ViewPager vp_goods;
    @Bind(R.id.tv_details_name)
    TextView tv_details_name;
    @Bind(R.id.tv_details_price)
    TextView tv_details_price;
    @Bind(R.id.tv_details_master)
    TextView tv_details_master;
    @Bind(R.id.tv_details_describe)
    TextView tv_details_describe;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
    @Bind(R.id.btn_sendMsg)
    Button btn_sendMsg;

    private GoodsDetailsPresenter presenter;

    private ArrayList<String> imageUrl_list;
    private ArrayList<ImageView> imageViews;
    private GoodsDetailsAdapter adapter;

    private static final String UUID = "uuid";
    //记录状态，是从商品页面跳转过来的还是从我的商品跳转过来的
    private static final String STATE = "state";

    private String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        presenter = new GoodsDetailsPresenter();
        presenter.attachView(this);
        imageViews = new ArrayList<>();
        adapter = new GoodsDetailsAdapter(imageViews);
        vp_goods.setAdapter(adapter);
        init();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    private void init() {
        uuid = getIntent().getStringExtra(UUID);
        presenter.getData(uuid);
    }

    private void getImage() {
        for (int i = 0; i < imageUrl_list.size(); i++) {
            ImageView imageView = new ImageView(this);
            Log.i("shen", "---------------------" + imageUrl_list.get(i));
            ImageLoader.getInstance()
                    .displayImage(
                            ShopApi.IMAGE_URL + imageUrl_list.get(i)
                    ,imageView
                    , ImageLoadOptions.build_item());
            imageViews.add(imageView);
        }
    }

    @Override
    public void setImageUrlData(ArrayList<String> list) {
        imageUrl_list = list;
        getImage();
        adapter.notifyDataSetChanged();
        indicator.setViewPager(vp_goods);
    }

    @Override
    public void setDetailsData(GoodsDetailsEntity detailsEntity) {
        tv_details_name.setText(detailsEntity.getName());
        tv_details_price.setText(detailsEntity.getPrice());
        tv_details_master.setText(detailsEntity.getMaster());
        tv_details_describe.setText(detailsEntity.getDescription());
    }
}
