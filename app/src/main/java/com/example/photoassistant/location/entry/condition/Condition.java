package com.example.photoassistant.location.entry.condition;

public interface Condition {

    long getId();

    void setId(long id);

    boolean meetCriteria(String s);

    String getDescribe();

    ConditionType getType();
}
