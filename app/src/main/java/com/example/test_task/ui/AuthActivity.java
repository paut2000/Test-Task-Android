package com.example.test_task.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.test_task.databinding.ActivityAuthBinding;
import com.example.test_task.service.AuthService;

public class AuthActivity extends AppCompatActivity {

    private ActivityAuthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();

        binding.loginButton.setOnClickListener(view -> {
            String username = binding.usernameText.getText().toString();
            String password = binding.passwordText.getText().toString();

            new AuthService().login(username, password, response -> {
                String token = response.getAccessToken();
                startMainActivity();
            });
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}