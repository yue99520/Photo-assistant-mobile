package com.example.photoassistant.auth.login;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.photoassistant.MainActivity;
import com.example.photoassistant.R;
import com.example.photoassistant.auth.login.component.EmailEditText;
import com.example.photoassistant.auth.login.component.LoginComponent;
import com.example.photoassistant.auth.login.component.PasswordEditText;
import com.example.photoassistant.auth.login.component.SimpleAuthField;
import com.example.photoassistant.auth.login.repository.LoginRepository;
import com.example.photoassistant.auth.request.LoginRequest;
import com.example.photoassistant.config.App;

public class LoginActivity extends AppCompatActivity {

    private LoginComponent loginComponent;

    private ProgressBar progressBar;

    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        settingLoginListeners();
    }

    private void init() {
        progressBar = findViewById(R.id.login_progress_dialog);
        errorMsg = findViewById(R.id.login_failure_msg);

        loginComponent = new LoginComponent(
                this,
                new EmailEditText((EditText) findViewById(R.id.login_email)),
                new PasswordEditText((EditText) findViewById(R.id.login_password)),
                (Button) findViewById(R.id.login_submit),
                new LoginRequest.LoginListener() {
                    @Override
                    public void loginResult(LoginRequest.Result result) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressBar.onVisibilityAggregated(false);
                        }
                        handleResult(result);
                    }
                });
    }

    private void settingLoginListeners() {
        loginComponent.setWaitingLoginListener(new LoginComponent.LoginListener() {

            @Override
            public void onAttemptingLogin() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.onVisibilityAggregated(true);
                }
            }
        });

        loginComponent.setValidateListener(new SimpleAuthField.ValidateListener() {
            @Override
            public void onValidateSuccess(String fieldName) {
                //
            }

            @Override
            public void onValidateFailure(String fieldName) {
                errorMsg.setText(R.string.input_validation_failure_msg);
            }
        });
    }

    public void handleResult(LoginRequest.Result result) {

        Toast.makeText(this, result.isSuccess() ? "登入成功" : "登入失敗", Toast.LENGTH_SHORT).show();

        if (result.isSuccess()) {
            LoginRepository loginRepository = new LoginRepository(this, App.SQLite_DATABASE_NAME);
            ContentValues values = new ContentValues();
            values.put("api_token", result.getToken());
            loginRepository.insert(values);
            loginRepository.close();
            enterApp();
        } else {
            errorMsg.setText(result.getMsg());
        }
    }

    public void enterApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
