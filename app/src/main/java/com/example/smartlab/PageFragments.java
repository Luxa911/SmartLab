package com.example.smartlab;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import  androidx.fragment.app.Fragment;
public class PageFragments extends Fragment {
    public static class Page1Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
           View view = inflater.inflate(R.layout.activity_main, container, false);
            TextView skip = view.findViewById(R.id.skip);
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Hello.class);
                    startActivity(intent);
                }
            });
           return view;
        }
    }

    public static class Page2Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_second_list, container, false);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView skip = view.findViewById(R.id.skipli);
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Hello.class);
                    startActivity(intent);
                }
            });
            return view;
        }
    }

    public static class Page3Fragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.activity_three_list, container, false);
            @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView skip = view.findViewById(R.id.end);
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), Hello.class);
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}