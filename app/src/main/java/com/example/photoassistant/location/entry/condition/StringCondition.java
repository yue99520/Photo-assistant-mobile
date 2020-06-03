package com.example.photoassistant.location.entry.condition;

public class StringCondition implements Condition {

    private long id;

    private String event;

    public StringCondition(String event, long id) {
        this.event = event;
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean meetCriteria(String s) {
        return event.contains(s);
    }

    @Override
    public String getTitle() {
        return event;
    }
}
