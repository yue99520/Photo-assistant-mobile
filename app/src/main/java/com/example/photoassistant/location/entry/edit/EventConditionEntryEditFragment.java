package com.example.photoassistant.location.entry.edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.photoassistant.R;
import com.example.photoassistant.location.entry.condition.EventCondition;

public class EventConditionEntryEditFragment extends EntryEditFragment {

    private EditText conditionEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_entry_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        conditionEditText = view.findViewById(R.id.entry_edit_condition_edit_text);
        conditionEditText.setText(((EventCondition)entry.getCondition()).getEvent());
    }

    @Override
    protected void readAdditionalInput() {
        ((EventCondition)entry.getCondition()).setEvent(conditionEditText.getText().toString());
    }

    @Override
    protected String getLogTag() {
        return this.getClass().getSimpleName();
    }
}
