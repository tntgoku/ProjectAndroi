package com.example.myapplication.Controller.data.model;

import com.example.myapplication.Object.Cart;
import com.example.myapplication.Object.User;

import java.util.HashMap;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private User su=null;
    private HashMap<User, Cart> userCartHashMap;
    public LoggedInUser(User sa) {
        this.userId = sa.getIDP();
        this.displayName = sa.getName();
        su=sa;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}