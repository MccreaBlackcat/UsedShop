package com.timestudio.usedshop.presentation.main.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timestudio.usedshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shenGqiang on 2016/11/17.
 */

public class GoodsTypeAdapter extends RecyclerView.Adapter<GoodsTypeAdapter.ViewHolder> {

    private String[] type = {"全部","家用","电子","服饰","玩具","图书","礼品","其他"};
    private Context context;
    private OnItemClickedListener listener;
    private int index;

    @Override
    public GoodsTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_goods_type, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsTypeAdapter.ViewHolder holder, final int position) {
        holder.tv_goods_type.setText(type[position]);
        holder.tv_goods_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onTypeClicked(position);
                }
            }
        });
        if (index == position) {
            holder.tv_goods_type.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.tv_goods_type_line.setBackgroundResource(R.color.colorPrimary);
        } else {
            holder.tv_goods_type.setTextColor(context.getResources().getColor(R.color.text_color_gray));
            holder.tv_goods_type_line.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return type.length;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_goods_type)
        TextView tv_goods_type;
        @Bind(R.id.tv_goods_type_line)
        TextView tv_goods_type_line;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void setListener(OnItemClickedListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickedListener {
        void onTypeClicked(int typeIndex);
    }
}
