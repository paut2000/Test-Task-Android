package com.example.test_task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.test_task.R;
import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthResponse;
import com.example.test_task.databinding.ActivityMainBinding;
import com.example.test_task.exception.TokenNotExist;
import com.example.test_task.model.User;
import com.example.test_task.ui.adapter.PermissionAdapter;
import com.example.test_task.util.PreferencesSaver;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PreferencesSaver preferencesSaver;

    private RecyclerView permissionRecycler;
    private PermissionAdapter permissionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencesSaver = new PreferencesSaver(this);

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        try {
            Api.setToken(preferencesSaver.readToken());
        } catch (TokenNotExist exception) {
            startAuthActivity();
        }

        Api.getInstance().getUserApi().getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    binding.roleIdText.setText(user.getRoleId());
                    binding.usernameText.setText(user.getUsername());
                    binding.emailText.setText(user.getEmail());
                    permissionAdapter.setPermissions(user.getPermissions());
                } else {
                    Toast.makeText(getApplicationContext(), "HTTP-error " + response.code(), Toast.LENGTH_LONG).show();
                    if (response.code() == 401) startAuthActivity();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) { }
        });

        binding.logoutButton.setOnClickListener(view -> {
            preferencesSaver.removeToken();
            startAuthActivity();
        });
    }

    private void initRecyclerView() {
        permissionRecycler = binding.permissionRecycler;
        permissionRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        permissionAdapter = new PermissionAdapter();
        permissionRecycler.setAdapter(permissionAdapter);
    }

    private void startAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

}