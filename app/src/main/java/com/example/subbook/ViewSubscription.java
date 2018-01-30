package com.example.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ViewSubscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        // To retrieve object in second Activity
        Subscription selectedItem = (Subscription) getIntent().getSerializableExtra("Selected_Subscription");

        Log.d("OBJECT_PASSED", "Subscription passed: " + selectedItem.getName());


    }
}
