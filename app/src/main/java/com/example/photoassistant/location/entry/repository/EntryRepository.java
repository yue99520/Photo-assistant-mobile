package com.example.photoassistant.location.entry.repository;

import com.example.photoassistant.location.entry.Entry;

import java.util.List;

public interface EntryRepository {

    List<Entry> getAllEntries(long location);

    boolean create(Entry entry);

    boolean update(Entry entry);
//
//    boolean delete(long entry);
}
