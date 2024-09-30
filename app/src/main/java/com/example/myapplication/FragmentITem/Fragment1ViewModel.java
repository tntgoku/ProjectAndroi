package com.example.myapplication.FragmentITem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Fragment1ViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> username = new MutableLiveData<>();

    public void setUsername(String newUsername) {
        username.setValue(newUsername);
    }

}