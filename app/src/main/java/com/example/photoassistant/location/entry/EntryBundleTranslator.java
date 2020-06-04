package com.example.photoassistant.location.entry;

import android.os.Bundle;

import com.example.photoassistant.location.entry.condition.Condition;
import com.example.photoassistant.location.entry.condition.ConditionType;
import com.example.photoassistant.location.entry.condition.EventCondition;

public class EntryBundleTranslator {

    public static Entry toEntry(Bundle bundle) {
        Entry entry = makeEntry(bundle);
        if (bundle.getBoolean("HAS_ID")) {
            entry.setId(bundle.getLong("ID"));
        }
        entry.setTitle(bundle.getString("TITLE"));
        entry.setSubtitle(bundle.getString("SUBTITLE"));

        return entry;
    }

    private static Entry makeEntry(Bundle bundle) {
        String typeString = bundle.getString("CONDITION_TYPE");
        ConditionType conditionType = ConditionType.valueOf(typeString);

        Condition condition = null;

        if (conditionType.equals(ConditionType.EVENT_CONDITION)) {

            String event = bundle.getString("EVENT");
            condition = new EventCondition(event);
        }
        // add new lines to handle more condition types

        return new Entry(condition);
    }

    public static Bundle toBundle(Entry entry) {
        Bundle bundle = makeBundle(entry);

        if (entry.hasId()) {
            bundle.putBoolean("HAS_ID", true);
            bundle.putLong("ID", entry.getId());
        } else {
            bundle.putBoolean("HAS_ID", false);
        }

        bundle.putString("TITLE", entry.getTitle());
        bundle.putString("SUBTITLE", entry.getSubtitle());

        return bundle;
    }

    private static Bundle makeBundle(Entry entry) {
        Bundle bundle = new Bundle();
        Condition condition = entry.getCondition();

        bundle.putString("CONDITION_TYPE", condition.getType().toString());

        if (condition.getType().equals(ConditionType.EVENT_CONDITION)) {
            bundle.putString("EVENT", ((EventCondition)condition).getEvent());
        }
        // add new lines to handle more condition types

        return bundle;
    }
}
