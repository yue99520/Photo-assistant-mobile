package com.example.photoassistant.location.entry.repository;

import com.example.photoassistant.location.entry.Entry;
import com.example.photoassistant.location.entry.condition.StringCondition;
import com.example.photoassistant.location.entry.repository.EntryRepository;

import java.util.ArrayList;
import java.util.List;

public class HttpEntryRepository implements EntryRepository {

    @Override
    public List<Entry> getAllEntries(long location) {
        List<Entry> entryList = new ArrayList<>();
        Entry entry;

        for (int i = 1; i <= 15; i++) {
            entry = new Entry();
            entry.setId(1);
            entry.setTitle("Bird");
            entry.setSubtitle("Subtitle");
            entry.getConditions().add(new StringCondition("白天", 1));
            entry.getConditions().add(new StringCondition("白天", 2));
            entry.getConditions().add(new StringCondition("白天", 3));
            entry.getConditions().add(new StringCondition("白天", 4));
            entryList.add(entry);
        }
        return entryList;
    }
}
