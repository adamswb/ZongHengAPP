package com.utupio.zongheng.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sch.rfview.AnimRFLinearLayoutManager;
import com.sch.rfview.AnimRFRecyclerView;
import com.utupio.zongheng.activity.R;
import com.utupio.zongheng.adapter.RecyclerViewAdapter;
import com.utupio.zongheng.pojo.News;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

/**
 * Created by Zhang Yan on 2015/8/24.
 */
public class CompetitionScheduledFragment extends Fragment {
    private AnimRFRecyclerView mRecyclerView;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<News> newses;
    private View headerView;
    private View footerView;
    private Handler mHandler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        this.context = this.getActivity().getApplicationContext();
        mRecyclerView = (AnimRFRecyclerView) view.findViewById(R.id.my_recycler_view);
        // 头部
        headerView = LayoutInflater.from(context).inflate(R.layout.header_view, null);
        // 脚部
        footerView = LayoutInflater.from(context).inflate(R.layout.footer_view, null);
        // 使用重写后的瀑布流布局管理器
        mRecyclerView.setLayoutManager(new AnimRFLinearLayoutManager(context));
        //            // 添加头部和脚部，如果不添加就使用默认的头部和脚部
//            mRecyclerView.addHeaderView(headerView);
//            // 设置头部的最大拉伸倍率，默认1.5f，必须写在setHeaderImage()之前
//            mRecyclerView.setScaleRatio(1.7f);
//            // 设置下拉时拉伸的图片，不设置就使用默认的
//            mRecyclerView.setHeaderImage((ImageView) headerView.findViewById(R.id.iv_hander));
//            mRecyclerView.addFootView(footerView);
        // 设置刷新动画的颜色
        mRecyclerView.setColor(Color.MAGENTA, Color.RED);
        // 设置头部恢复动画的执行时间，默认1000毫秒
        mRecyclerView.setHeaderImageDurationMillis(1200);
        // 设置拉伸到最高时头部的透明度，默认0.5f
        mRecyclerView.setHeaderImageMinAlpha(0.6f);

        mRecyclerView.setHasFixedSize(true);
        initData();
        // use a linear layout manager
        //  mLayoutManager = new LinearLayoutManager(context);
        //  mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewAdapter(newses, context);
        mRecyclerView.setAdapter(mAdapter);
        // 设置刷新和加载更多数据的监听，分别在onRefresh()和onLoadMore()方法中执行刷新和加载更多操作
        mRecyclerView.setLoadDataListener(new AnimRFRecyclerView.LoadDataListener() {
            @Override
            public void onRefresh() {
                new Thread(new MyRunnable(true)).start();
            }

            @Override
            public void onLoadMore() {
                new Thread(new MyRunnable(false)).start();
            }
        });
        return view;
    }


    class MyRunnable implements Runnable {
        boolean isRefresh;

        public MyRunnable(boolean isRefresh) {
            this.isRefresh = isRefresh;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (isRefresh) {
                        newData();
                        refreshComplate();
                        // 刷新完成后调用，必须在UI线程中
                        mRecyclerView.refreshComplate();
                    } else {
                        addData();
                        loadMoreComplate();
                        // 加载更多完成后调用，必须在UI线程中
                        mRecyclerView.loadMoreComplate();
                    }
                }
            });
        }
    }

    public void refreshComplate() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    public void loadMoreComplate() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }


    /**
     * 添加数据
     */
    private void addData() {
        if (newses == null) {
            newses = new ArrayList<News>();
        }
        for (int i = 0; i < 13; i++) {
            newses.add(new News("小车河健康中心", "小车河健康中心位于小河接近花溪:" + i, "5km", "30元/小时", R.drawable.xiaochehe));
        }
    }

    public void newData() {
        newses.clear();
        for (int i = 0; i < 13; i++) {
            newses.add(new News("贵阳市民健康中心", "贵阳市民健康中心位于火车站附近:" + i, "5km", "30元/小时", R.drawable.guiyangshiming));
        }
    }


    private void initData() {
        newses = new ArrayList<News>();
        News newsOneInfos = new News("小车河健康中心", "小车河健康中心位于小河接近花溪", "5km", "30元/小时", R.drawable.xiaochehe);
        News newsTowInfos = new News("贵阳市民健康中心", "贵阳市民健康中心位于火车站附近", "5km", "30元/小时", R.drawable.guiyangshiming);
        newses.add(newsOneInfos);
        newses.add(newsTowInfos);
    }
}
