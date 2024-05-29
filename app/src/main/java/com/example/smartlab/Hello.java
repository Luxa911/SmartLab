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
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Hello extends AppCompatActivity {
    EditText mail;
    Button next;
    private String jwtToken= "ed842a65-a06c-4019-b1f2-d7f451206156";
private ApiService apiService;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        mail = findViewById(R.id.editTextTextEmailAddress);
        next = findViewById(R.id.next);
        apiService = ApiClient.getRetrofitInstance(jwtToken).create(ApiService.class);
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
        String email = mail.getText().toString();
        if(!email.isEmpty()) {
            sendCodeToEmail(email);
            Intent intent = new Intent(this, Confirm.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(Hello.this,"Please enter an email",Toast.LENGTH_SHORT).show();
        }
    }
    private void sendCodeToEmail(String email){
        SendCodeRequest request = new SendCodeRequest(email);
        apiService.sendcode("Bearer "+jwtToken,request).enqueue(new Callback<SendCodeResponse>() {
            @Override
            public void onResponse(Call<SendCodeResponse> call, Response<SendCodeResponse> response) {
                if(response.isSuccessful()&&response.body()!=null){
                    Toast.makeText(Hello.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Hello.this,"Failed to send code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SendCodeResponse> call, Throwable t) {
Toast.makeText(Hello.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}