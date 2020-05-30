package com.example.photoassistant.location;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.photoassistant.R;
import java.util.ArrayList;
import java.util.List;

public class LocationListFragment extends Fragment {

    private LocationAdapter locationAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_list, container, false);
        init(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void init(final View view) {
        Context context = getActivity();

        List<Location> locations = new ArrayList<>();

        locationAdapter = new LocationAdapter(context, locations);

        RecyclerView recyclerView = view.findViewById(R.id.location_recycler);

        recyclerView.setAdapter(locationAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        updateData(locationAdapter.getLocations());

        locationAdapter.notifyDataSetChanged();
    }

    public void setOnclickListener(final OnItemClickListener listener) {
        locationAdapter.setOnItemClickListener(new LocationAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, Location onClickLocation) {
                listener.onItemClick(v, onClickLocation);
            }
        });
    }

    public void updateData(List<Location> locations) {
            Location location = new Location();
            location.setTitle("lc 1");
            location.setSubtitle("sub title");
            location.setEntryCount(10);
            locations.add(location);
            location = new Location();
            location.setTitle("lc 2");
            location.setSubtitle("sub title");
            location.setEntryCount(10);
            locations.add(location);
            location = new Location();
            location.setTitle("lc 3");
            location.setSubtitle("sub title");
            location.setEntryCount(10);
            locations.add(location);
            location = new Location();
            location.setTitle("lc 4");
            location.setSubtitle("sub title");
            location.setEntryCount(10);
            locations.add(location);
            //todo remove location mock data
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Location location);
    }
}
