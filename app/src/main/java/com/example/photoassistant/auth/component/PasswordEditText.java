package com.example.photoassistant.auth.component;

import android.widget.EditText;

import com.example.photoassistant.auth.ValidateException;


public class PasswordEditText extends SimpleAuthField {

    public PasswordEditText(EditText editText) {
        super(editText, "((?=.*[a-z])(?=.*d)(?=.*[@#$%])(?=.*[A-Z]).{6,16})");
    }

    @Override
    public void validate() throws ValidateException {
        try {
            super.validate();
        } catch (ValidateException e) {
            throw new ValidateException("Invalid password pattern.");
        }
    }
}
