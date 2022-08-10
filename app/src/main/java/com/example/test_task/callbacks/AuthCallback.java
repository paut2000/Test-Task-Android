package com.example.test_task.callbacks;

import com.example.test_task.api.messages.AuthResponse;

public interface AuthCallback {

    void onSuccess(AuthResponse response);

}
