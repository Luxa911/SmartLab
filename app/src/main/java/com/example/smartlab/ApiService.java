package com.example.smartlab;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/sendCode")
    Call<SendCodeResponse> sendcode(@Header("Authorization") String token,@Body SendCodeRequest request);
}
class SendCodeRequest{
    private String email;
    public SendCodeRequest(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
}
class SendCodeResponse{
    private String message;
    public String getMessage(){
        return message;
    }
}