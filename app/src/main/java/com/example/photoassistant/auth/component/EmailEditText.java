package com.example.photoassistant.auth.component;

import android.widget.EditText;

import com.example.photoassistant.auth.ValidateException;

public class EmailEditText extends SimpleAuthField {

    public EmailEditText(EditText editText) {
        super(editText, "^(.+)@(.+)$");
    }

    @Override
    public void validate() throws ValidateException {
        try {
            super.validate();
        } catch (ValidateException e) {
            throw new ValidateException("Invalid email pattern.");
        }
    }
}
