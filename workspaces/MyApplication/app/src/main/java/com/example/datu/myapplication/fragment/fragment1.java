package com.example.datu.myapplication.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.datu.myapplication.R;
import com.example.datu.myapplication.activity.PublishActivity;
import com.example.datu.myapplication.activity.SearchActivity;
import com.example.datu.myapplication.adapter.TabFragmentAdapter;
import com.example.datu.myapplication.fragment.base.BaseFragment;
import com.example.datu.myapplication.fragment.fragment1children.guanzhu_fragment;
import com.example.datu.myapplication.fragment.fragment1children.media_fragment;
import com.example.datu.myapplication.fragment.fragment1children.news_fragment;
import com.example.datu.myapplication.fragment.fragment1children.project_fragment;
import com.example.datu.myapplication.fragment.fragment1children.tuijain_fragment;

import java.util.ArrayList;

/**
 * Created by datu on 2018/8/1.
 * 第一个fragment页面
 */

public class fragment1 extends BaseFragment implements View.OnClickListener {
    private String TAG = getClass().getSimpleName();
    private TabLayout tablayout;
    private ViewPager viewPager;
    private String titles[] = {"关注", "推荐", "快讯", "项目", "媒体"};
    private ArrayList<BaseFragment> fragmentArrayList;
    private LinearLayout ll_publish;
    private LinearLayout ll_search;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    protected View initView() {
        Log.e(TAG, "fragment1 view init......");
        View view = View.inflate(mContext, R.layout.fragment1_layout, null);

        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        ll_publish = (LinearLayout) view.findViewById(R.id.ll_publish);
        ll_search = (LinearLayout) view.findViewById(R.id.ll_search);

        ll_publish.setOnClickListener(this);
        ll_search.setOnClickListener(this);


        //初始化viewPager
        initViewPager();
        //绑定viewPager
        tablayout.setupWithViewPager(viewPager);
        // 设置tab文本的没有选中（第一个参数）和选中（第二个参数）的颜色
        tablayout.setTabTextColors(Color.BLACK, Color.RED);
        viewPager.setAdapter(new TabFragmentAdapter(mContext, fragmentArrayList, titles, getActivity().getSupportFragmentManager()));

        return view;
    }


    /**
     *
     */
    @Override
    protected void initData() {
        super.initData();
        Log.e(TAG, "fragment1 data init......");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }


    }

    /**
     * 初始化viewPager,并发消息到对应的fragment
     */
    private void initViewPager() {
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new guanzhu_fragment());
        fragmentArrayList.add(new tuijain_fragment());
        fragmentArrayList.add(new news_fragment());
        fragmentArrayList.add(new project_fragment());
        fragmentArrayList.add(new media_fragment());
        for (int i = 0; i < titles.length; i++) {
            Bundle bundle = new Bundle();
            //发送消息
            bundle.putString("title", titles[i]);
            fragmentArrayList.get(i).setArguments(bundle);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //跳转到搜索页面
            case R.id.ll_search:
                intent = new Intent(mContext, SearchActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_publish:
                intent = new Intent(mContext, PublishActivity.class);
                mContext.startActivity(intent);
                break;

            default:
                break;
        }
    }


}
