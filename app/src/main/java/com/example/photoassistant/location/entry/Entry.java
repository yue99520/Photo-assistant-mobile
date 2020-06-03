package com.example.photoassistant.location.entry;

import com.example.photoassistant.location.entry.condition.Condition;

import java.util.ArrayList;
import java.util.List;

public class Entry {

    private long id;

    private String title;

    private String subtitle;

    private List<Condition> conditions;

    public Entry() {
        conditions = new ArrayList<>();
    }

    public boolean meetsSearch(String search) {

        for (Condition condition : conditions) {

            if (condition.meetCriteria(search)) {

                return true;
            }
        }

        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
