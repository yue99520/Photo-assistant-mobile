package com.example.photoassistant.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.photoassistant.R;

import java.util.List;

public class LocationAdapter extends BaseAdapter {

    private List<Location> locationList;

    private LayoutInflater layoutInflater;

    public LocationAdapter(Context context, List<Location> locationList) {
        this.locationList = locationList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.locationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.locationList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.location_item, null);

            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.location_item_title),
                    (TextView) convertView.findViewById(R.id.location_item_subtitle),
                    (TextView) convertView.findViewById(R.id.location_item_entry_amount)
            );

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        Location location = locationList.get(position);

        holder.title.setText(location.getTitle());
        holder.subtitle.setText(location.getSubtitle());
        holder.entryAmount.setText(String.valueOf(location.getEntryAmount()));

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView subtitle;
        TextView entryAmount;

        ViewHolder(TextView title, TextView subtitle, TextView entryAmount) {
            this.title = title;
            this.subtitle = subtitle;
            this.entryAmount = entryAmount;
        }
    }
}
