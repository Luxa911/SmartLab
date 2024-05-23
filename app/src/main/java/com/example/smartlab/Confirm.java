package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;


public class Confirm extends AppCompatActivity {
EditText one;
EditText two;
EditText three;
EditText four;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        one = findViewById(R.id.cifra1);
        two = findViewById(R.id.cifra2);
        three = findViewById(R.id.cifra3);
        four = findViewById(R.id.cifra4);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateCode();
            }

            @Override
            public void afterTextChanged(Editable s) {
if(s.length() == 1){
    if(one.hasFocus()){
        two.requestFocus();
    } else if (two.hasFocus()) {
        three.requestFocus();
    }
    else if(three.hasFocus()){
        four.requestFocus();
    }
}
            }

            private void updateCode() {
String code = one.getText().toString()+ two.getText().toString()+three.getText().toString()+four.getText().toString();
if(code.equals("1362")){
    Intent intent = new Intent(Confirm.this, Passcode.class);
    startActivity(intent);
}
            }
        };
    one.addTextChangedListener(textWatcher);
        two.addTextChangedListener(textWatcher);
        three.addTextChangedListener(textWatcher);
        four.addTextChangedListener(textWatcher);
    }
}