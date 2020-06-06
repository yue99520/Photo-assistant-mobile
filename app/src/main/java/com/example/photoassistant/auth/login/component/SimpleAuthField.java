package com.example.photoassistant.auth.login.component;

import android.widget.EditText;

import com.example.photoassistant.auth.login.AuthField;

import java.util.regex.Pattern;

public abstract class SimpleAuthField implements AuthField {

    private final EditText editText;

    private final String REGEX;

    private ValidateListener validateListener;

    public SimpleAuthField(EditText editText, String REGEX) {
        this.editText = editText;
        this.REGEX = REGEX;
    }

    @Override
    public EditText getView() {
        return editText;
    }

    @Override
    public void validate() {
        String content = getText();

        if (validateListener != null) {
            if (isMatchRegex(content)) {
                validateListener.onValidateSuccess(getFieldName());
            } else {
                validateListener.onValidateFailure(getFieldName());
            }
        }
    }

    public abstract String getFieldName();

    public void setValidateListener(ValidateListener validateListener) {
        this.validateListener = validateListener;
    }

    @Override
    public String getText() {
        return editText.getText().toString();
    }

    @Override
    public void setText(String text) {
        this.editText.setText(text);
    }

    private boolean isMatchRegex(String content) {
        Pattern pattern = Pattern.compile(REGEX);
        return pattern.matcher(content).matches();
    }

    public static interface ValidateListener {

        void onValidateSuccess(String fieldName);

        void onValidateFailure(String fieldName);
    }
}
