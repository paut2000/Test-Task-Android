package com.example.test_task.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Api instance = null;
    public static final String BASE_URL = "http://smart.eltex-co.ru:8271/api/v1/";

    private IAuthApi authApi;

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private Api() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.authApi = retrofit.create(IAuthApi.class);
    }

    public IAuthApi getAuthApi() {
        return authApi;
    }

}
