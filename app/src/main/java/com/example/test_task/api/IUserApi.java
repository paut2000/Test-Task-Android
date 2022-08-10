package com.example.test_task.api;

import com.example.test_task.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IUserApi {

    @GET("user")
    Call<User> getUser();

}
