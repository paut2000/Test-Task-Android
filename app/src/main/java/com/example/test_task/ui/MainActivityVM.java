package com.example.test_task.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test_task.model.User;

public class MainActivityVM extends ViewModel {

    private MutableLiveData<User> userLiveData = new MutableLiveData<>();



    @Override
    protected void onCleared() {
        super.onCleared();


    }

}
