package com.example.test_task.api;

import com.example.test_task.api.messages.AuthResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IAuthApi {

    @POST("oauth/token")
    Call<AuthResponse> getToken(
            @Header("Authorization") String authHeader,
            @Body RequestBody body
    );

}
