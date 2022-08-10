package com.example.test_task.service;

import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthResponse;
import com.example.test_task.callbacks.AuthCallback;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService {

    public void login(String username, String password, AuthCallback callback) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "password")
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();

        Api.getInstance().getAuthApi().getToken(
                "Basic YW5kcm9pZC1jbGllbnQ6cGFzc3dvcmQ=",
                requestBody
        ).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    try {
                        System.out.println("Not good " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                System.out.println("So Bad");
            }
        });
    }

}
