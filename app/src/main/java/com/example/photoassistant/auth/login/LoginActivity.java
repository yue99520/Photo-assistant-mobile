package com.example.photoassistant.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.photoassistant.R;
import com.example.photoassistant.auth.component.EmailEditText;
import com.example.photoassistant.auth.component.LoginComponent;
import com.example.photoassistant.auth.component.PasswordEditText;
import com.example.photoassistant.config.App;
import com.example.photoassistant.db.LoginInfo;

public class LoginActivity extends AppCompatActivity {

    private LoginComponent loginComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        loginComponent = new LoginComponent(
                this,
                new EmailEditText((EditText) findViewById(R.id.login_email)),
                new PasswordEditText((EditText) findViewById(R.id.login_password)),
                (Button) findViewById(R.id.login_submit),
                new LoginComponent.LoginListener(){

                    @Override
                    public void beforeLogin() {

                    }

                    @Override
                    public void loginResult(boolean result, String msg, String token) {
                        handleResult(result, msg, token);
                    }
                });
    }

    public void handleResult(boolean result, String msg, String token) {

        if (result) {
            LoginInfo loginInfo = new LoginInfo(this, App.SQLite_DATABASE_NAME);
            ContentValues values = new ContentValues();
            values.put("api_token", token);
            loginInfo.insert(values);
            loginInfo.close();
            enterApp();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void enterApp() {

    }
}
