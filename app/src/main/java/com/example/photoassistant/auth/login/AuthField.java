package com.example.photoassistant.auth.login;

import android.widget.EditText;

public interface AuthField {

    EditText getView();

    void validate();

    String getText();

    void setText(String text);
}
