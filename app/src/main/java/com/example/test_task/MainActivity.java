package com.example.test_task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthRequest;
import com.example.test_task.api.messages.AuthResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "password")
                .addFormDataPart("username", "tester")
                .addFormDataPart("password", "tester")
                .build();

        Api.getInstance().getAuthApi().getToken(
                "Basic YW5kcm9pZC1jbGllbnQ6cGFzc3dvcmQ=",
                requestBody
        ).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    System.out.println(authResponse);
                } else {
                    try {
                        System.out.println("Hello not good " + response.errorBody().string().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                System.out.println("Hello bad");
            }
        });
    }
}