package com.example.test_task.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.test_task.R;
import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthResponse;

import java.io.IOException;

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
    }

}