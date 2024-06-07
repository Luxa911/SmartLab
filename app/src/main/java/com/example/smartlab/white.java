package com.example.smartlab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;

public class white extends AppCompatActivity {
ViewPager2 vpVertical;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_white);
        vpVertical = findViewById(R.id.vp);
        vpVertical.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                switch (position) {
                    case 0:
                        return new PageFragments.Page1Fragment();
                    case 1:
                        return new PageFragments.Page2Fragment();
                    case 2:
                        return new PageFragments.Page3Fragment();
                    default:
                        throw new IllegalArgumentException("Invalid position: " + position);
                }
            }

            @Override
            public int getItemCount() {
                return 3;
            }


        });
        vpVertical.setOffscreenPageLimit(2);
    }
}