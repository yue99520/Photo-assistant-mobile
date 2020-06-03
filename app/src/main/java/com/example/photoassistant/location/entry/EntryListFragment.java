package com.example.photoassistant.location.entry;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.photoassistant.R;
import com.example.photoassistant.location.Location;
import com.example.photoassistant.location.LocationBundleTranslator;
import com.example.photoassistant.location.entry.repository.EntryRepository;
import com.example.photoassistant.location.entry.repository.HttpEntryRepository;

import java.util.ArrayList;
import java.util.List;

public class EntryListFragment extends Fragment {

    private EntryRepository entryRepository;

    private Location location;

    private EntryAdapter entryAdapter;

    private List<Entry> entryList;

    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entryList = new ArrayList<>();

        entryAdapter = new EntryAdapter(getActivity(), entryList);

        entryRepository = new HttpEntryRepository();

        if (getArguments() != null) {

            location = LocationBundleTranslator.toLocation(getArguments());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entry_list, container, false);

        listView = view.findViewById(R.id.entry_list_view);

        listView.setAdapter(entryAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Entry", "on item click");
                switchFragment(entryList.get(position));
            }
        });

        updateList();
    }

    private void switchFragment(Entry entry) {
        Toast.makeText(getContext(), entry.getTitle(), Toast.LENGTH_SHORT).show();
        //todo switch fragment to target entry
    }

    private void updateList() {

        updateData();

        entryAdapter.notifyDataSetChanged();
    }

    private void updateData() {
        List<Entry> newData = entryRepository.getAllEntries(location.getId());
        entryList.addAll(newData);
    }
}
