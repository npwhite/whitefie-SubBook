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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Nick on 2018-01-30.
 */

// simple_list_item_1 used in simple adapter works well with simple strings, but in order to
// use my custom Subscription objects, need to make a custom Adapter
public class SubscriptionRowAdapter extends ArrayAdapter<Subscription>{

    // default given by pressing control + enter --> constructor
    public SubscriptionRowAdapter(@NonNull Context context, ArrayList<Subscription> subscriptionList) {
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
        Date subscriptionDate = (getItem(position)).getDateStarted();       // presented in yyyy-mm-dd
        double subscriptionPrice = (getItem(position)).getMonthlyCharge();

        TextView subName = (TextView) subscriptionRowView.findViewById(R.id.subRowName);
        TextView subDate = (TextView) subscriptionRowView.findViewById(R.id.subRowDate);
        TextView subPrice = (TextView) subscriptionRowView.findViewById(R.id.subRowPrice);

        subName.setText(subscriptionName);
        String ct = DateFormat.getDateInstance().format(subscriptionDate);
        //subDate.setText(String.format(subscriptionDate));
        subDate.setText(String.format(ct));
        subPrice.setText(String.valueOf(subscriptionPrice));
        return subscriptionRowView;

    }
}
