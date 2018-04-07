package com.vanxnf.photovalley.features.UserProfile.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vanxnf.photovalley.R;
import com.vanxnf.photovalley.features.UserProfile.Entity.SubscribeItem;

import java.util.List;

/**
 * Created by VanXN 2018/4/6.
 */
public class SubscribeAdapter extends ArrayAdapter<SubscribeItem> {

    public SubscribeAdapter(@NonNull Context context, @NonNull List<SubscribeItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.subscribe_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else holder = (ViewHolder) convertView.getTag();

        SubscribeItem option = getItem(position);

        holder.description.setText(option.getDescription());
        holder.amount.setText(option.getAmount());

        return convertView;
    }

    private static final class ViewHolder {
        TextView description;
        TextView amount;

        public ViewHolder(View v) {
            description = (TextView) v.findViewById(R.id.subscribe_description);
            amount = (TextView) v.findViewById(R.id.subscribe_amount);
        }
    }
}

