package com.example.photoassistant.location.entry.condition;

import androidx.annotation.NonNull;

public interface Condition {

    long getId();

    void setId(long id);

    boolean meetCriteria(String s);

    String getTitle();
}
