package com.example.photoassistant.auth.login;

import android.widget.EditText;

import com.example.photoassistant.auth.ValidateException;

public interface AuthField {

    EditText getView();

    void validate() throws ValidateException;

    String getText();

    void setText(String text);
}
