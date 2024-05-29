package com.example.smartlab;

import android.media.session.MediaSession;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL="https://medic.madskill.ru";
    private static Retrofit retrofit;
    public  static Retrofit getRetrofitInstance(String token){
        if(retrofit == null){
            OkHttpClient client= UnsafeOkHttpClient.getUnsafeOkHttpClient().newBuilder().addInterceptor(chain -> {
              Request original = chain.request();
              Request.Builder requestBuilder = original.newBuilder().header("Authorization", "Bearer "+ token).method(original.method(), original.body());
              Request request = requestBuilder.build();
              return chain.proceed(request);
            }).build();


            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
