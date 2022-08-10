package com.example.test_task.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.test_task.R;
import com.example.test_task.api.Api;
import com.example.test_task.api.messages.AuthResponse;
import com.example.test_task.databinding.ActivityMainBinding;
import com.example.test_task.model.User;
import com.example.test_task.ui.adapter.PermissionAdapter;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerView permissionRecycler;
    private PermissionAdapter permissionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();

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
                    System.out.println("bad");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("the worst");
            }
        });
    }

    private void initRecyclerView() {
        permissionRecycler = binding.permissionRecycler;
        permissionRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        permissionAdapter = new PermissionAdapter();
        permissionRecycler.setAdapter(permissionAdapter);
    }
}