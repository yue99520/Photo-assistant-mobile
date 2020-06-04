package com.example.photoassistant.location.entry.condition.repository;

import com.example.photoassistant.location.entry.condition.Condition;

public interface ConditionRepository {

    Condition getConditionById(long id);

    boolean update(Condition condition);

    boolean delete(Condition condition);
}
