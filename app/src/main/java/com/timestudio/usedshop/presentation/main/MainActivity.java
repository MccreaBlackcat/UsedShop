package com.timestudio.usedshop.presentation.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.timestudio.usedshop.R;
import com.timestudio.usedshop.presentation.main.shop.GoodsFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_main_title)
    TextView tv_main_title;
    @Bind(R.id.vp_main)
    ViewPager vp_main;
    @Bind({R.id.tv_shop,R.id.tv_message,R.id.tv_list,R.id.tv_mine})
    TextView[] tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        init();
    }

    private void init() {
        //进入主界面，将第一个设置为默认选中
        tv[0].setSelected(true);
        //设置字体的颜色
        tv[0].setTextColor(getResources().getColor(R.color.text_color_orange));
        //添加ViewPager的改变监听
        vp_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当滑动操作后，做的改变
                //先将所有的textview选择都设为未选中
                for (TextView textView : tv) {
                    textView.setSelected(false);
                    textView.setTextColor(getResources().getColor(R.color.text_color_black));
                }
                //将选中的下标，设置成true
                tv_main_title.setText(tv[position].getText());
                tv[position].setSelected(true);
                tv[position].setTextColor(getResources().getColor(R.color.text_color_orange));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_main.setAdapter(fragmentPagerAdapter);
    }

    @OnClick({R.id.tv_shop, R.id.tv_message, R.id.tv_list, R.id.tv_mine})
    public void onClick(View view) {
        for (int i = 0; i < tv.length; i++) {
            tv[i].setSelected(false);
            tv[i].setTextColor(getResources().getColor(R.color.text_color_black));
            tv[i].setTag(i);
        }
        view.setSelected(true);
        ((TextView)view).setTextColor(getResources().getColor(R.color.text_color_orange));
        //设置ViewPager切换的过度效果
        vp_main.setCurrentItem((Integer) view.getTag(),true);
        tv_main_title.setText(tv[(int) view.getTag()].getText());
    }

    private FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public int getCount() {
            return 4;
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new GoodsFragment();
                case 1:
                    return new GoodsFragment();
                case 2:
                    return new GoodsFragment();
                case 3:
                    return new GoodsFragment();
            }
            return null;
        }

    };
}
