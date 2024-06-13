package com.example.smartlab;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hello extends AppCompatActivity {
    EditText mail;
    Button next;
    ApiService apiService;
    Button yan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        mail = findViewById(R.id.editTextTextEmailAddress);
        yan = findViewById(R.id.yan);
        next = findViewById(R.id.next);
        apiService = ApiClient.getClient().create(ApiService.class);
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Hello.this, Confirm.class);
                startActivity(intent2);
            }
        });
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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
               sendConfCode(email);
            }
        });
    }
    private void sendConfCode(String email){
        EmailRequest emailRequest = new EmailRequest(email);
        Call<EmailResponse> call = apiService.sendCode(emailRequest);

        call.enqueue(new Callback<EmailResponse>() {
            @Override
            public void onResponse(Call<EmailResponse> call, Response<EmailResponse> response) {
                if (response.isSuccessful()){
                    EmailResponse emailResponse = response.body();
                    Toast.makeText(Hello.this, "Code confirm sent: "+emailResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Hello.this, Confirm.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Hello.this,"Failed to send code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EmailResponse> call, Throwable t) {
Toast.makeText(Hello.this,"Failed2", Toast.LENGTH_SHORT).show();
            }
        });
    }

}