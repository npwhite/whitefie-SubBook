package com.example.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class ViewSubscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        // To retrieve object in second Activity
        Subscription selectedItem = (Subscription) getIntent().getSerializableExtra("Selected_Subscription");
        String nameText = (String) selectedItem.getName();

        //bodyText = (EditText) findViewById(R.id.body);

        //EditText subText = (EditText) view_subscription.findViewById(R.id.subNameRow);
        EditText subText = (EditText) findViewById(R.id.subscriptionViewName);

        subText.setText(nameText, TextView.BufferType.NORMAL);

        //Log.d("OBJECT_PASSED", "Subscription passed: " + selectedItem.getName());


    }
}
