package com.example.photoassistant.location;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.photoassistant.R;
import com.example.photoassistant.location.entry.Entry;
import com.example.photoassistant.location.entry.EntryListFragment;

import java.util.List;

public class LocationDetailActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private Location location;

    private Intent intent;

    private TextView title;

    private TextView subtitle;

    private TextView longitude;

    private TextView latitude;

    private ImageButton mapButton;

    private ListView entryListView;

    private List<Entry> locationEntries;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        fragmentManager = getSupportFragmentManager();
        title = findViewById(R.id.location_detail_title);
        subtitle = findViewById(R.id.location_detail_subtitle);
        longitude = findViewById(R.id.location_detail_longitude);
        latitude = findViewById(R.id.location_detail_latitude);

        intent = getIntent();

        loadData();
    }

    private void loadData() {
        Bundle bundle = intent.getBundleExtra("TARGET");
        if (bundle != null) {
            location = LocationBundleTranslator.toLocation(bundle);
            loadView();
            loadFragment();
        }
    }

    private void loadView() {
        title.setText(location.getTitle());
        subtitle.setText(location.getSubtitle());
        longitude.setText(String.valueOf(location.getLongitude()));
        latitude.setText(String.valueOf(location.getLatitude()));
    }

    private void loadFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        EntryListFragment entryListFragment = new EntryListFragment();
        Bundle bundle = LocationBundleTranslator.toBundle(location);
        entryListFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_container_location_detail_activity, entryListFragment);
        fragmentTransaction.commit();
    }
}
