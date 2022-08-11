package com.example.test_task.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthResponse;
import com.example.test_task.databinding.ActivityAuthBinding;
import com.example.test_task.util.PreferencesSaver;

import java.util.Base64;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;
    private PreferencesSaver preferencesSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesSaver = new PreferencesSaver(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.loginButton.setOnClickListener(view -> {
            String username = binding.usernameText.getText().toString();
            String password = binding.passwordText.getText().toString();

            if (username.equals("")) {
                Toast.makeText(getApplicationContext(), "Empty username", Toast.LENGTH_LONG).show();
                return;
            } else if (password.equals("")) {
                Toast.makeText(getApplicationContext(), "Empty password", Toast.LENGTH_LONG).show();
                return;
            }

            login(username, password);
        });
    }

    private void login(String username, String password) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("grant_type", "password")
                .addFormDataPart("username", username)
                .addFormDataPart("password", password)
                .build();

        Api.getInstance().getAuthApi().getToken(
                requestBody
        ).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    String token = authResponse.getAccessToken();
                    preferencesSaver.saveToken(token);
                    startMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "HTTP-error " + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) { }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}