package com.example.photoassistant.location;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.photoassistant.R;

public class LocationDetailActivity extends AppCompatActivity {

    private Intent intent;

    private TextView title;

    private TextView subtitle;

    private TextView longitude;

    private TextView latitude;

    private ImageButton mapButton;

    private ListView entryListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        title = findViewById(R.id.location_detail_title);
        subtitle = findViewById(R.id.location_detail_subtitle);
        longitude = findViewById(R.id.location_detail_longitude);
        latitude = findViewById(R.id.location_detail_latitude);
        entryListView = findViewById(R.id.location_detail_entry_list_view);

        intent = getIntent();

        loadData();
    }

    private void loadData() {
        loadDetail();
        loadEntries();
    }

    private void loadDetail() {
        Bundle bundle = intent.getBundleExtra("TARGET");
        if (bundle != null) {
            Location location = LocationBundleTranslator.toLocation(bundle);
            title.setText(location.getTitle());
            subtitle.setText(location.getSubtitle());
            longitude.setText(String.valueOf(location.getLongitude()));
            latitude.setText(String.valueOf(location.getLatitude()));
        }
    }

    private void loadEntries() {

    }
}
