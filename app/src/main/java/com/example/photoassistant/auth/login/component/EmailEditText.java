package com.example.photoassistant.auth.login.component;

import android.widget.EditText;

public class EmailEditText extends SimpleAuthField {

    public EmailEditText(EditText editText) {
        super(editText, "^(.+)@(.+)$");
    }

    @Override
    public String getFieldName() {
        return "Email";
    }
}
