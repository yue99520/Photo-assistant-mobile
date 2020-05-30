package com.example.photoassistant;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.photoassistant.location.Location;
import com.example.photoassistant.location.LocationListFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    private LocationListFragment listFragment;

    private Fragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createListFragment();

        fragmentManager = getSupportFragmentManager();

        checkoutListFragment();
    }

    private void createListFragment() {
        this.listFragment = new LocationListFragment();
        this.listFragment.setOnclickListener(new LocationListFragment.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Location location) {
                checkoutDetailFragment();
            }
        });
    }

    public void showLocationDetail() {

    }

    public void checkoutDetailFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container_view_main, detailFragment);

        fragmentTransaction.commit();
    }

    public void checkoutListFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container_view_main, listFragment);

        fragmentTransaction.commit();
    }
}
