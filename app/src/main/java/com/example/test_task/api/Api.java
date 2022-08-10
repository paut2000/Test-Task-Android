package com.example.test_task.api;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Api instance = null;
    public static final String BASE_URL = "http://smart.eltex-co.ru:8271/api/v1/";

    private IAuthApi authApi;
    private IUserApi userApi;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    public IAuthApi getAuthApi() {
        if (authApi == null) {
            buildAuthApi();
        }
        return authApi;
    }

    public IUserApi getUserApi() {
        if (userApi == null) {
            buildUserApi();
        }
        return userApi;
    }

    private void buildAuthApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.authApi = retrofit.create(IAuthApi.class);
    }

    private void buildUserApi() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZXIiLCJhdWQiOiJhbmRyb2lkLWNsaWVudCIsIm5iZiI6MTY2MDEzMTUzNSwic2NvcGUiOlsidHJ1c3QiLCJyZWFkIiwid3JpdGUiXSwiaXNzIjoiaHR0cDpcL1wvc21hcnQuZWx0ZXgtY28ucnU6ODA3MSIsImV4cCI6MTY2MDEzNTEzNSwiaWF0IjoxNjYwMTMxNTM1LCJqdGkiOiJkNzA4MTA0OS02Njc1LTQzOWQtOWM4ZS05YTZmMWY4ZDE3ODAifQ.QDBZIjjfKLZ6vLm3Tf1Lo6kw7lSfdWvDAKqNnKvPucqmaaska_ysdyw4vjNdYsNJctuInpzJDC_fbnhIXI2zyu6lgQNtrHN_ZNLUKoMcED1z0M6VPeO8YtnrMZdr0nugUlt-txGu3YOzuA6XsBVtBKu00eLBdCff_3TSO0ExPhNTWBSQyg2iJsvLgbuuHavVxEXlFbLBc5MWX3tdp2g-A_HG7hHWQfjD-duRjNqN9X8bytBAm4W4rwRJCuSt3rNRE4a5dCz9lfO4KTWF6G_Mr1GFwXX1hK-jNgfd4438-Yu7EKE7zuoczz59oKVk_bXVmNHhNfsLD5X6SToUgUcA9A")
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
