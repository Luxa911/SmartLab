package com.example.smartlab;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
   private List<News> newsList;
   public NewsAdapter(List<News> newsList){
       this.newsList = newsList;
   }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        return new NewsViewHolder(view);
    }
    @Override
    public void onBindViewHolder(NewsViewHolder holder,int position){
     News news =newsList.get(position);
     holder.titleNews.setText(news.getTitle());
     holder.descriptionNews.setText(news.getDescription());
     holder.priceNews.setText(news.getPrice());
    }
    @Override
    public int getItemCount(){
       return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        public TextView titleNews,descriptionNews,priceNews;
       public NewsViewHolder(View newsView){
           super(newsView);
           titleNews = newsView.findViewById(R.id.textTitle);
           descriptionNews = newsView.findViewById(R.id.textDescription);
           priceNews = newsView.findViewById(R.id.textPrice);
       }
   }

}
