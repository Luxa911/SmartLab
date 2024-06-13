package com.example.smartlab;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("sendCode")
    Call<EmailResponse> sendCode(@Body EmailRequest emailRequest);

    @POST("create_patient")
    Call<Void> createPatient(@Body PatientData patientData);

    @GET("news")
    Call<List<News>> getNews();
}
class EmailRequest{
    private String email;
    public EmailRequest(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail() {
        this.email = email;
    }
}
class EmailResponse{
    private String message;
    private String code;
    public String getMessage(){
        return message;
    }
    public void setMessage(){
        this.message = message;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
}
