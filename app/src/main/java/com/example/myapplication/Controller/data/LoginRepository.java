package com.example.myapplication.Controller.data;

import android.util.Log;

import com.example.myapplication.Controller.data.model.LoggedInUser;
import com.example.myapplication.MySql.ConnectDB;
import com.example.myapplication.Object.User;

import java.io.IOException;
import java.util.UUID;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public Result<LoggedInUser> login(String username, String password) {
        // handle login
        ConnectDB db=new ConnectDB();
        db.conn= db.getConn();;
        // Thực hiện truy vấn SQL để kiểm tra thông tin đăng nhập
        String sqlResult = db.Query("loginapp N'" + username + "', N'" + password + "'");
        Log.i("TAG", "login: "+sqlResult);
        Result<LoggedInUser> result=null;
            if (!sqlResult.isEmpty()){
                String[] rows=sqlResult.split("\t");
                User sa=new User(rows[0],rows[1],rows[2],rows[3],rows[6], rows[5],rows[7],rows[8],rows[4] );
                result= dataSource.login(rows[0], password,sa);
                if (result instanceof Result.Success) {

                setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());

            }
        }
        return result;
    }


}