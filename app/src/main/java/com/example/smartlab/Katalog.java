package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
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
    private EditText search;
    private List<News> newsList = new ArrayList<>();
    private List<CatalogItem> catalogItems;
    private RecyclerView recyclerView;
    private ApiService apiService;
    private ImageView buttom;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        buttom = findViewById(R.id.profilebuttom);
        recyclerView = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search);
        NewsAdapter newsAdapter = new NewsAdapter(newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(newsAdapter);
        catalogList = findViewById(R.id.catalog_list);
        catalogItems = new ArrayList<>();
        catalogItems.add(new CatalogItem("ПЦР-тест на опредление РНК короновируса стандартный", "2 дня", "1800 ₽"));
        catalogItems.add(new CatalogItem("Клинический анализ крови с лейкоцитарной фомулой", "1 день", "690 ₽"));
        catalogItems.add(new CatalogItem("Биохимический анализ крови, базовый", "1 день", "2440 ₽"));
        catalogItems.add(new CatalogItem("СОЭ (венозная кровь)", "2 дня", "1500 ₽"));

        HighliteAdapter adapter = new HighliteAdapter(this, catalogItems);
        catalogList.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http:/10.0.2.2:5000/api/").addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(ApiService.class);
        Call<List<News>> call = apiService.getNews();
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    newsList.addAll(response.body());
                    Log.d("Katalog", "Product List Size: " + newsList.size());
                    newsAdapter.notifyDataSetChanged();
                } else {
                    Log.e("Katalog", "Failed1" + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e("Katalog", "Failed2" + t.getMessage());
            }
        });
        buttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Katalog.this, Profile.class);
                startActivity(intent);
            }
        });

//Поиск

search.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.setSearchText(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});
    }
}