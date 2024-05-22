package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Hello extends AppCompatActivity {
    EditText mail;
    Button next;
    Boolean click = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        mail = findViewById(R.id.editTextTextEmailAddress);
        next = findViewById(R.id.next);
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasText = s.length() > 0;
                next.setClickable(hasText);
                next.setEnabled(hasText);
                if (hasText) {
                    next.setBackgroundResource(R.drawable.buttonun);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void next(View view) {
        Intent intent = new Intent(this, Confirm.class);
        startActivity(intent);
    }
}