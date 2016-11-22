package com.timestudio.usedshop.presentation.main.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.timestudio.usedshop.R;
import com.timestudio.usedshop.commonutils.ImageLoadOptions;
import com.timestudio.usedshop.model.GoodsEntity;
import com.timestudio.usedshop.network.ShopApi;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shenGqiang on 2016/11/16.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GoodsEntity> list = new ArrayList<>();
    private OnItemClickedListener listener;

    @Override
    public GoodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsAdapter.ViewHolder holder, final int position) {
        holder.tv_goods_name.setText(list.get(position).getName());
        holder.tv_goods_price.setText("￥"+list.get(position).getPrice());
        holder.iv_goods_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPhotoClicked(list.get(position));
                }
            }
        });
        //给ImageView设置图片,用的是LoadImage框架
        ImageLoader.getInstance().displayImage(
                ShopApi.IMAGE_URL + list.get(position).getPage()
                ,holder.iv_goods_pic
                , ImageLoadOptions.build_item()
                );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_goods_pic)
        ImageView iv_goods_pic;
        @Bind(R.id.tv_goods_name)
        TextView tv_goods_name;
        @Bind(R.id.tv_goods_price)
        TextView tv_goods_price;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void addData(List<GoodsEntity> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickedListener {
        void onPhotoClicked(GoodsEntity goodsEntity);
    }
}
