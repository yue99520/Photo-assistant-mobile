package com.example.photoassistant.location.entry.condition;

public class EventCondition implements Condition {

    public static ConditionType conditionType = ConditionType.EVENT_CONDITION;

    private long id;

    private boolean hasId;

    private String event;

    public EventCondition(String event) {
        this.event = event;
        hasId = false;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
        this.hasId = true;
    }

    @Override
    public boolean meetCriteria(String s) {
        return event.contains(s);
    }

    @Override
    public String getDescribe() {
        return getEvent();
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public ConditionType getType() {
        return conditionType;
    }
}
