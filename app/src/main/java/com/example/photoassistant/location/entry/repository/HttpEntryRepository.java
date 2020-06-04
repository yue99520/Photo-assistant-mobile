package com.example.photoassistant.location.entry.repository;

import android.util.Log;

import com.example.photoassistant.location.entry.Entry;
import com.example.photoassistant.location.entry.condition.EventCondition;

import java.util.ArrayList;
import java.util.List;

public class HttpEntryRepository implements EntryRepository {

    private String logTag = this.getClass().getSimpleName();

    @Override
    public List<Entry> getAllEntries(long location) {
        List<Entry> entryList = new ArrayList<>();
        Entry entry;

        for (int i = 1; i <= 15; i++) {
            entry = new Entry(new EventCondition("白天"));
            entry.setId(1);
            entry.setTitle("Bird");
            entry.setSubtitle("Subtitle");
            entryList.add(entry);
        }
        return entryList;
    }

    @Override
    public boolean create(Entry entry) {
        Log.i(logTag, "create entry");
        return true;
    }

    @Override
    public boolean update(Entry entry) {
        Log.i(logTag, "update entry: " + entry.getId());
        return true;
    }
}
