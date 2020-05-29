package com.example.photoassistant.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoassistant.R;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<Location> locations = new ArrayList<>();

    public LocationAdapter(Context context, List<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.location_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.title = view.findViewById(R.id.location_title);
        viewHolder.subtitle = view.findViewById(R.id.location_subtitle);
        viewHolder.entryAmount = view.findViewById(R.id.location_entry_amount);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder locationHolder = (ViewHolder) holder;

        Location location = locations.get(position);

        locationHolder.setLocation(location);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private Location location;

        public TextView title;

        public TextView subtitle;

        public TextView entryAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setLocation(Location location) {
            this.location = location;
            this.title.setText(location.getTitle());
            this.subtitle.setText(location.getSubtitle());
            this.entryAmount.setText(location.getEntryCount());
        }

        public Location getLocation() {
            return location;
        }
    }
}
