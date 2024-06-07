package com.example.smartlab;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Confirm extends AppCompatActivity {
EditText one;
EditText two;
EditText three;
EditText four;
private CountDownTimer countDownTimer;
TextView timertext;
private static final long TIMER_DURATION = 40000;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        one = findViewById(R.id.cifra1);
        two = findViewById(R.id.cifra2);
        three = findViewById(R.id.cifra3);
        four = findViewById(R.id.cifra4);
        timertext = findViewById(R.id.TimerText);
        startTimer();
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
    private void startTimer(){
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsLeft = millisUntilFinished /1000;
                timertext.setText("Отправить код повторно можно будет через "+secondsLeft+" секунд");
            }

            @Override
            public void onFinish() {
timertext.setText("Отправить код ещё раз");
timertext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startTimer();
    }
});
            }
        }.start();
    }
}