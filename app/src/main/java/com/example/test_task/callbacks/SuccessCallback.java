package com.example.test_task.callbacks;

import com.example.test_task.api.messages.AuthResponse;

public interface SuccessCallback {

    void onSuccess(AuthResponse response);

}
