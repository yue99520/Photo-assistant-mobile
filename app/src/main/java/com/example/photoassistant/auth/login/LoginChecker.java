package com.example.photoassistant.auth.login;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.photoassistant.auth.login.repository.LoginRepository;
import com.example.photoassistant.auth.request.CheckLoginRequest;

public class LoginChecker {

    public static void check(Context context, CheckLoginRequest.CheckLoginListener checkLoginListener) {

        LoginRepository repository = new LoginRepository(context);

        if (repository.isTokenExists()) {
            String token = repository.getToken();
            Log.d(LoginChecker.class.getSimpleName(), "token: " + token);
            CheckLoginRequest checkLoginRequest = new CheckLoginRequest(token, checkLoginListener);
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            requestQueue.add(checkLoginRequest);
        } else {
            Log.d(LoginChecker.class.getSimpleName(), "token: null");
        }
        repository.close();
    }
}
