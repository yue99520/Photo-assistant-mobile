package com.example.photoassistant.auth.component;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.photoassistant.auth.ValidateException;
import com.example.photoassistant.config.App;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginComponent {

    private Context context;

    private LoginListener loginListener;

    private EmailEditText emailEditText;

    private PasswordEditText passwordEditText;

    private Button loginSubmit;

    private boolean validationResult = false;

    private String validationMsg = "";

    private boolean loginResult = false;
    private String loginMsg = "";
    private String token = "";

    public LoginComponent(Context context, EmailEditText email, PasswordEditText password, Button submit, LoginListener loginListener) {
        this.context = context;
        this.emailEditText = email;
        this.passwordEditText = password;
        this.loginSubmit = submit;
        this.loginListener = loginListener;
        initListener();
    }

    private void initListener() {
        loginSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {

                    validate();

                    beforeLogin();

                    attemptLogin();

                    validationResult = true;
                    validationMsg = "OK";

                } catch (ValidateException e) {
                    validationResult = false;
                    validationMsg = e.getMessage();
                }

                loginResult();
            }
        });
    }

    private void beforeLogin() {
        loginListener.beforeLogin();
    }

    private void loginResult() {
        loginListener.loginResult(validationResult, validationMsg, token);
    }

    private void validate() throws ValidateException {
        this.emailEditText.validate();
        this.passwordEditText.validate();
    }

    private void attemptLogin() {
        Volley.newRequestQueue(context);

        final String username = emailEditText.getText();
        final String password = passwordEditText.getText();
        final String deviceName = Build.MODEL;

        StringRequest loginRequest = new StringRequest(
                Request.Method.POST,
                App.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = (boolean) jsonObject.get("success");
                            token = (String) jsonObject.get("token");

                            if (success) {
                                loginResult = true;
                                loginMsg = "Login success";
                                
                            }

                        } catch (JSONException e) {
                            loginResult = false;
                            loginMsg = "Unknown error...";
                            Log.e(this.getClass().getSimpleName(), "Can not parse response data to json.");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginResult = false;
                        loginMsg = "Server error...";
                        Log.e(this.getClass().getSimpleName(), "Server internal error on login.");
                    }
                }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", password);
                params.put("device_name", deviceName);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(loginRequest);
    }

    public interface LoginListener {

        void beforeLogin();

        void loginResult(boolean result, String msg, String token);
    }
}
