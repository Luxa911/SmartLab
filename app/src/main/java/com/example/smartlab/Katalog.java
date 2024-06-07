package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Katalog extends AppCompatActivity {
private ListView catalogList;
private List<CatalogItem> catalogItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        catalogList = findViewById(R.id.catalog_list);
        catalogItems = new ArrayList<>();
        catalogItems.add(new CatalogItem("ПЦР-тест на опредление РНК короновируса стандартный", "2 дня","1800 ₽"));
        catalogItems.add(new CatalogItem("Клинический анализ крови с лейкоцитарной фомулой", "1 день", "690 ₽"));
        catalogItems.add(new CatalogItem("Биохимический анализ крови, базовый", "1 день", "2440 ₽"));
        catalogItems.add(new CatalogItem("СОЭ (венозная кровь)", "2 дня", "1500 ₽"));

        CatalogAdapter adapter = new CatalogAdapter(this, catalogItems);
        catalogList.setAdapter(adapter);
    }

}