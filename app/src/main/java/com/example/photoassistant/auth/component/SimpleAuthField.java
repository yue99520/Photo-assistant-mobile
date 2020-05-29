package com.example.photoassistant.auth.component;

import android.widget.EditText;

import com.example.photoassistant.auth.ValidateException;
import com.example.photoassistant.auth.login.AuthField;

import java.util.regex.Pattern;

public class SimpleAuthField implements AuthField {

    private final EditText editText;

    private final String REGEX;

    public SimpleAuthField(EditText editText, String REGEX) {
        this.editText = editText;
        this.REGEX = REGEX;
    }

    @Override
    public EditText getView() {
        return editText;
    }

    @Override
    public void validate() throws ValidateException {
        String content = getText();

        if ( !isMatchRegex(content)) {
            throw new ValidateException("Invalid Pattern!");
        }
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
}
