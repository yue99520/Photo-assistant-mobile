package com.example.photoassistant.location.entry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.photoassistant.R;

import java.util.List;

public class EntryAdapter extends BaseAdapter {

    private List<Entry> entryList;

    private LayoutInflater layoutInflater;

    public EntryAdapter(Context context, List<Entry> entryList) {
        this.entryList = entryList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.entryList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.entryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.entryList.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.entry_item, null);

            holder = new ViewHolder(
                    (TextView) convertView.findViewById(R.id.entry_item_title),
                    (TextView) convertView.findViewById(R.id.entry_item_subtitle),
                    (TextView) convertView.findViewById(R.id.entry_item_condition)
            );

            convertView.setTag(holder);
        }else {

            holder = (ViewHolder) convertView.getTag();
        }

        Entry entry = entryList.get(position);

        holder.title.setText(entry.getTitle());
        holder.subtitle.setText(entry.getSubtitle());
        holder.condition.setText(String.valueOf(entry.getCondition().getDescribe()));

        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        TextView subtitle;
        TextView condition;

        ViewHolder(TextView title, TextView subtitle, TextView condition) {
            this.title = title;
            this.subtitle = subtitle;
            this.condition = condition;
        }
    }
}
