package com.example.photoassistant.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoassistant.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter {

    private Context context;

    private List<Location> locations;

    private OnItemClickListener onItemClickListener;

    public LocationAdapter(Context context, List<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.location_item, parent, false);
        LocationViewHolder locationViewHolder = new LocationViewHolder(view);

        locationViewHolder.title = view.findViewById(R.id.location_title);
        locationViewHolder.subtitle = view.findViewById(R.id.location_subtitle);
        locationViewHolder.entryAmount = view.findViewById(R.id.location_entry_amount);

        return locationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final LocationViewHolder locationHolder = (LocationViewHolder) holder;

        final Location currentBindLocation = locations.get(position);

        locationHolder.setLocation(currentBindLocation);

        initOnClickListener(locationHolder, currentBindLocation);
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.onItemClickListener = itemClickListener;
    }

    private void initOnClickListener(LocationAdapter.LocationViewHolder viewHolder, final Location location) {

        viewHolder.itemView.setClickable(true);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            private Location currentOnClickLocation = location;

            @Override
            public void onClick(View v) {

                if (onItemClickListener != null) {

                    onItemClickListener.onClick(v, currentOnClickLocation);

                }
            }
        });
    }

    public interface OnItemClickListener{

        void onClick(View v, Location onClickLocation);
    }

    static class LocationViewHolder extends RecyclerView.ViewHolder {

        private Location location;

        public TextView title;

        public TextView subtitle;

        public TextView entryAmount;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void setLocation(Location location) {
            this.location = location;
            updateView();
        }

        public void updateView() {
            this.title.setText(location.getTitle());
            this.subtitle.setText(location.getSubtitle());
            this.entryAmount.setText(String.valueOf(location.getEntryCount()));
        }

        public Location getLocation() {
            return location;
        }
    }
}
