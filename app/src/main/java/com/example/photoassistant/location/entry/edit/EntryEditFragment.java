package com.example.photoassistant.location.entry.edit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.photoassistant.R;
import com.example.photoassistant.location.entry.Entry;
import com.example.photoassistant.location.entry.EntryBundleTranslator;
import com.example.photoassistant.location.entry.condition.ConditionType;
import com.example.photoassistant.location.entry.condition.EventCondition;
import com.example.photoassistant.location.entry.repository.EntryRepository;
import com.example.photoassistant.location.entry.repository.HttpEntryRepository;

public abstract class EntryEditFragment extends Fragment {

    public static EntryEditFragment create(ConditionType conditionType) {

        if (conditionType == ConditionType.EVENT_CONDITION) {

            Entry entry = new Entry(new EventCondition(""));

            return update(entry);
        }

        return null;
    }

    public static EntryEditFragment update(Entry entry) {

        if (entry.getCondition().getType() == ConditionType.EVENT_CONDITION) {

            Bundle bundle = EntryBundleTranslator.toBundle(entry);

            EntryEditFragment fragment = new EventConditionEntryEditFragment();

            fragment.setArguments(bundle);

            return fragment;
        }

        return null;
    }

    protected Entry entry;

    protected EntryRepository entryRepository;

    protected EditText titleEditText;

    protected EditText subtitleEditText;

    protected Button save;

    protected Button cancel;

    protected abstract String getLogTag();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entryRepository = new HttpEntryRepository();

        Bundle bundle = getArguments();

        if (bundle == null)
            Log.e(getLogTag(), "No bundle arguments for init");
        else
            entry = EntryBundleTranslator.toEntry(bundle);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleEditText = view.findViewById(R.id.entry_edit_title_edit_text);
        subtitleEditText = view.findViewById(R.id.entry_edit_subtitle_edit_text);
        save = view.findViewById(R.id.save_button);
        cancel = view.findViewById(R.id.cancel_button);

        initEditText();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readBasicInput();

                String result;

                if (!entry.hasId()) {
                    result = entryRepository.create(entry)? "成功":"失敗";
                    Toast.makeText(getContext(), "建立" + result, Toast.LENGTH_SHORT).show();
                } else {
                    result = entryRepository.update(entry)? "成功":"失敗";
                    Toast.makeText(getContext(), "更新" + result, Toast.LENGTH_SHORT).show();
                }

                finishFragment();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishFragment();
            }
        });
    }

    private void initEditText() {
        titleEditText.setText(entry.getTitle());
        subtitleEditText.setText(entry.getSubtitle());
    }

    private void readBasicInput() {
        entry.setTitle(titleEditText.getText().toString());
        entry.setSubtitle(subtitleEditText.getText().toString());
        readAdditionalInput();
    }

    private void finishFragment() {
        FragmentActivity fragmentActivity = getActivity();

        if (fragmentActivity != null) {
            FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            fragmentManager.popBackStackImmediate();
        }
    }

    protected abstract void readAdditionalInput();
}
