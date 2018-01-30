// Used this tutorial as guide
// https://www.youtube.com/watch?v=nOdSARCVYic 2018/01/30

package com.example.subbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nick on 2018-01-30.
 */

// simple_list_item_1 used in simple adapter works well with simple strings, but in order to
// use my custom Subscription objects, need to make a custom Adapter
public class SubscriptionRowAdapter extends ArrayAdapter<Subscription>{

    // default given by pressing control + enter --> constructor
    public SubscriptionRowAdapter(@NonNull Context context, Subscription[] subscriptionList) {
        super(context, R.layout.subscription_row, subscriptionList);

    }

    // found in control + enter --> Override methods
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // "How android deals with laying things out on your activity"
        LayoutInflater subscriptionListInflater = LayoutInflater.from(getContext());
        View subscriptionRowView = subscriptionListInflater.inflate(R.layout.subscription_row, parent, false);

        String subscriptionName = (getItem(position)).getName();
        TextView subText = (TextView) subscriptionRowView.findViewById(R.id.subNameRow);

        subText.setText(subscriptionName);
        return subscriptionRowView;

    }
}
