package com.example.myapplication.Controller.ui;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Controller.data.LoginDataSource;
import com.example.myapplication.Controller.data.LoginRepository;
import com.example.myapplication.Controller.ui.login.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(LoginViewModel.class)) {
                return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource()));
            } else {
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        }
}
