package com.example.myapplication.Controller.data;

import com.example.myapplication.Controller.data.model.LoggedInUser;
import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public Result<LoggedInUser> login(String username, String password,User sa) {

        try {
            // TODO: handle loggedInUser authentication

            LoggedInUser fakeUser =
                    new LoggedInUser(sa);
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }


    }


    public void logout() {
        // TODO: revoke authentication
    }
}