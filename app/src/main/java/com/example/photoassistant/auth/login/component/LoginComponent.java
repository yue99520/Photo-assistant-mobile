package com.example.photoassistant.auth.login.component;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.photoassistant.auth.request.LoginRequest;

public class LoginComponent {

    private String logTag = this.getClass().getSimpleName();

    private Context context;

    private LoginListener waitingLoginListener;

    private LoginRequest.LoginListener onResponseListener;

    private SimpleAuthField emailEditText;

    private SimpleAuthField passwordEditText;

    private Button loginSubmit;

    public LoginComponent(Context context, EmailEditText email, PasswordEditText password, Button submit, LoginRequest.LoginListener onResponseListener, LoginListener waitingLoginListener) {
        this(context, email, password, submit, onResponseListener);
        this.waitingLoginListener = waitingLoginListener;
    }

    public LoginComponent(Context context, EmailEditText email, PasswordEditText password, Button submit, LoginRequest.LoginListener onResponseListener) {
        this.context = context;
        this.emailEditText = email;
        this.passwordEditText = password;
        this.loginSubmit = submit;
        this.onResponseListener = onResponseListener;
        initListener();
    }

    private void initListener() {
        loginSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d(logTag, "Validating inputs");
                validate();

                Log.d(logTag, "Attempting login");
                attemptLogin();
            }
        });
    }

    public void setWaitingLoginListener(LoginListener waitingLoginListener) {
        this.waitingLoginListener = waitingLoginListener;
    }

    public void setValidateListener(SimpleAuthField.ValidateListener validateListener) {
        this.emailEditText.setValidateListener(validateListener);
        this.passwordEditText.setValidateListener(validateListener);
    }

    private void validate() {
        this.emailEditText.validate();
        this.passwordEditText.validate();
    }

    private void attemptLogin() {

        LoginRequest.Data data = new LoginRequest.Data();
        data.email = emailEditText.getText();
        data.password = passwordEditText.getText();
        data.deviceName = Build.MODEL;

        LoginRequest loginRequest = new LoginRequest(data, onResponseListener);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(loginRequest);

        waitingLoginListener.onAttemptingLogin();
    }

    public interface LoginListener {

        void onAttemptingLogin();
    }
}
