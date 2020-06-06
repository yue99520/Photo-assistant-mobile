package com.example.photoassistant.auth.login.component;

import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;


public class PasswordEditText extends SimpleAuthField {

    public PasswordEditText(EditText editText) {
        super(editText, "^[A-Za-z0-9_.]+$");
        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    @Override
    public String getFieldName() {
        return "Password";
    }
}
