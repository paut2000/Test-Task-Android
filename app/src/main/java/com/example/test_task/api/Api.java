package com.example.test_task.api;

import android.os.Build;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Api instance = null;
    public static final String BASE_URL = "http://smart.eltex-co.ru:8271/api/v1/";
    private static final String credentials = "android-client:password";
    private static String token;

    private IAuthApi authApi;
    private IUserApi userApi;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public static void setToken(String token) {
        Api.token = token;
    }

    public IAuthApi getAuthApi() {
        if (authApi == null) {
            buildAuthApi();
        }
        return authApi;
    }

    public IUserApi getUserApi() {
        if (userApi == null) {
            if (token == null) throw new RuntimeException("Access token wasn't set");
            buildUserApi(token);
        }
        return userApi;
    }

    private void buildAuthApi() {

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            String encodeCredentials = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                encodeCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
            }

            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Basic " + encodeCredentials)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.authApi = retrofit.create(IAuthApi.class);
    }

    private void buildUserApi(String token) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.userApi = retrofit.create(IUserApi.class);
    }

}
