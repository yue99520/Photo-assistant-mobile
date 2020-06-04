package com.example.photoassistant.location.entry;

import com.example.photoassistant.location.entry.condition.Condition;

public class Entry {

    private long id = -1L;

    private boolean hasId;

    private String title = "";

    private String subtitle = "";

    private Condition condition;

    public Entry(Condition condition) {
        this.condition = condition;
        this.hasId = false;
    }

    public boolean meetsSearch(String search) {

        return condition.meetCriteria(search);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        this.hasId = true;
    }

    public boolean hasId() {
        return hasId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
