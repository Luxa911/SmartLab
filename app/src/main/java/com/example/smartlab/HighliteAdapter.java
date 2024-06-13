package com.example.smartlab;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class HighliteAdapter extends BaseAdapter {
    private Context context;
    private List<CatalogItem> items;
    private String searchText = "";

    public HighliteAdapter(Context context, List<CatalogItem> items){
        this.context = context;
        this.items = items;
    }
    public void setSearchText(String searchText){
        this.searchText = searchText;
        notifyDataSetChanged();
    }
    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return items.get(position);
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.catalog_item, parent, false);
        }
        TextView titleTextView = convertView.findViewById(R.id.item_title);
        TextView descriptionTextView = convertView.findViewById(R.id.item_description);
        TextView priceTextView = convertView.findViewById(R.id.price);
        CatalogItem item = items.get(position);
        highliteText(titleTextView, item.getTitle());
        highliteText(descriptionTextView, item.getDescription());
        highliteText(priceTextView, item.getPrice());
        return convertView;
    }
    private void highliteText(TextView textView,String text){
        SpannableString spannableString = new SpannableString(text);
        if(searchText.length()>=3){
            int start = text.toLowerCase().indexOf(searchText.toLowerCase());
            while (start>=0){
                int end = start +searchText.length();
                spannableString.setSpan(new BackgroundColorSpan(Color.BLUE),start,end,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                start = text.toLowerCase().indexOf(searchText.toLowerCase(),end);
            }
        }
        textView.setText(spannableString);
    }
}
