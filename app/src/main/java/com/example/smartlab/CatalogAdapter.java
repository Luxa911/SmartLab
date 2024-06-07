package com.example.smartlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CatalogAdapter extends ArrayAdapter<CatalogItem> {
    private final Context context;
    private final List<CatalogItem> items;

    public CatalogAdapter(Context context, List<CatalogItem> items) {
        super(context, R.layout.catalog_item,items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.catalog_item, parent, false);
        }

        CatalogItem item = items.get(position);

        TextView title = convertView.findViewById(R.id.item_title);
        TextView description = convertView.findViewById(R.id.item_description);
        Button addButton = convertView.findViewById(R.id.add_button);
        TextView price = convertView.findViewById(R.id.price);

        title.setText(item.getTitle());
        description.setText(item.getDescription());
        price.setText(item.getPrice());

        addButton.setOnClickListener(v -> {
// Handle button click
        });

        return convertView;
    }
}
