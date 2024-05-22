package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Passcode extends AppCompatActivity {
EditText one;
EditText two;
EditText three;
EditText four;
ImageButton backspace;
String code;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
    one = findViewById(R.id.pass1);
    two = findViewById(R.id.pass2);
    three = findViewById(R.id.pass3);
    four = findViewById(R.id.pass4);
    backspace = findViewById(R.id.backspace);
        Button[] buttons = {findViewById(R.id.b0),
                findViewById(R.id.b1),
                findViewById(R.id.b2),
                findViewById(R.id.b3),
                findViewById(R.id.b4),
                findViewById(R.id.b5),
                findViewById(R.id.b6),
                findViewById(R.id.b7),
                findViewById(R.id.b8),
                findViewById(R.id.b9),
        };
    one.requestFocus();
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
                if (s.length() == 1) {
                    if (one.hasFocus()) {
                        two.requestFocus();
                    } else if (two.hasFocus()) {
                        three.requestFocus();
                    } else if (three.hasFocus()) {
                        four.requestFocus();
                    }
                }

            }
            private void updateCode() {
                code = one.getText().toString() + two.getText().toString() + three.getText().toString() + four.getText().toString();
                if(one.length()>0){
                    one.setBackgroundResource(R.drawable.passcodeun);
                }
                if(two.length()>0){
                    two.setBackgroundResource(R.drawable.passcodeun);
                }
                if(three.length()>0){
                    three.setBackgroundResource(R.drawable.passcodeun);
                }
                if(four.length()>0){
                    four.setBackgroundResource(R.drawable.passcodeun);
                }
                if (code.equals("1234")) {
                    Intent intent = new Intent(Passcode.this, Karta.class);
                    startActivity(intent);
                }
            }
        };
        one.addTextChangedListener(textWatcher);
        two.addTextChangedListener(textWatcher);
        three.addTextChangedListener(textWatcher);
        four.addTextChangedListener(textWatcher);
backspace.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (four.hasFocus() && !TextUtils.isEmpty(four.getText())) {
            four.setText("");
            four.setBackgroundResource(R.drawable.passcoden);
        }
        else if (three.hasFocus() && !TextUtils.isEmpty(three.getText())) {
            three.setText("");
            three.setBackgroundResource(R.drawable.passcoden);
        }
        else if (two.hasFocus() && !TextUtils.isEmpty(two.getText())) {
            two.setText("");
        two.setBackgroundResource(R.drawable.passcoden);}

        else if (one.hasFocus() && !TextUtils.isEmpty(one.getText())) {
            one.setText("");
            one.setBackgroundResource(R.drawable.passcoden);
        }
        else if (two.hasFocus()){
            one.requestFocus();
        }
        else if (three.hasFocus()){
            two.requestFocus();
        }
        else if (four.hasFocus()){
            three.requestFocus();
        }

    }

});
for (Button button: buttons){
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onNumberButtonCLick(((Button) v).getText().toString());
        }
    });
}

    }
private void onNumberButtonCLick(String number){
        if(one.getText().toString().isEmpty()){
            one.setText(number);
        }
        else if (two.getText().toString().isEmpty()){
            two.setText(number);
        }
        else if(three.getText().toString().isEmpty()){
            three.setText(number);
        }
        else if(four.getText().toString().isEmpty()){
            four.setText(number);
        }
}
}