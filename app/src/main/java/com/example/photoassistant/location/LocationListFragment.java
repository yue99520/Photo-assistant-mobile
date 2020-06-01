package com.example.photoassistant.location;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.photoassistant.R;
import java.util.ArrayList;
import java.util.List;

public class LocationListFragment extends Fragment {

    private LocationAdapter locationAdapter;

    private List<Location> locationList;

    private ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationList = new ArrayList<>();

        locationAdapter = new LocationAdapter(getActivity(), locationList);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_list, container, false);

        listView = view.findViewById(R.id.location_list_view);

        listView.setAdapter(locationAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("list_item", "On item click event");
                intentToDetail(locationList.get(position));
            }
        });

        updateList();
    }

    private void intentToDetail(Location location) {
        Intent intent = new Intent(getContext(), LocationDetailActivity.class);
        Bundle bundle = LocationBundleTranslator.toBundle(location);
        intent.putExtra("TARGET", bundle);
        startActivity(intent);
    }

    private void updateList() {

        updateData();

        locationAdapter.notifyDataSetChanged();
    }

    private void updateData() {
        Location location = new Location();
        location.setId(1);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        this.locationList.add(location);

        new Location();
        location.setId(2);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        this.locationList.add(location);

        new Location();
        location.setId(3);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        this.locationList.add(location);

        new Location();
        location.setId(4);
        location.setTitle("title1");
        location.setSubtitle("sub title");
        location.setEntryAmount(10);
        location.setLongitude(13.123);
        location.setLatitude(20.123);
        this.locationList.add(location);
    }
}
