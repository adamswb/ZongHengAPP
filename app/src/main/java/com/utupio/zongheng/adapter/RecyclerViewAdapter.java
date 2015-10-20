package com.utupio.zongheng.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.utupio.zongheng.activity.NewsActivity;
import com.utupio.zongheng.activity.R;
import com.utupio.zongheng.pojo.News;

import java.util.List;

/**
 * Created by QiWangming on 2015/6/13.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder> {
    private List<News> newses;
    private Context context;

    public RecyclerViewAdapter(List<News> newses, Context context) {
        this.newses = newses;
        this.context = context;
    }

    //自定义ViewHolder类
    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView newsPhotoView;
        TextView compNewName;
        TextView priceVew;
        TextView distanceView;

        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.my_card_view);
            newsPhotoView = (ImageView) itemView.findViewById(R.id.img);
            compNewName = (TextView) itemView.findViewById(R.id.comp_name);
            priceVew = (TextView) itemView.findViewById(R.id.price);
            distanceView = (TextView) itemView.findViewById(R.id.distance);
            //设置TextView背景为半透明
            compNewName.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_item, viewGroup, false);
        NewsViewHolder nvh = new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder personViewHolder, int i) {
        final int j = i;
        personViewHolder.compNewName.setText(newses.get(i).getTitle());
        personViewHolder.priceVew.setText(newses.get(i).getPrice());
        personViewHolder.distanceView.setText(newses.get(i).getDisance());
        personViewHolder.newsPhotoView.setImageResource(newses.get(i).getPhotoId());
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsActivity.class);
                intent.putExtra("News", newses.get(j));
                context.startActivity(intent);
            }
        });

        personViewHolder.distanceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, newses.get(j).getDesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Intent.createChooser(intent, newses.get(j).getTitle()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }
}
