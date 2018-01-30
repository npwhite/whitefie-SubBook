package com.example.subbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*

    --> Alright, So far I have a simple list app that displays strings, allows them to be clicked,
        and a new page will open up (a new activity)
    --> I should probably consider this to be practice, as most of what I have added was copied
        from examples online
    --> Need to do:
          * make list hold Subscription objects
          * clicking on Subscription object opens proper view for that object
          * + symbol opens window to create new Subscription

        Should do:
          * check user input to make sure it meets constraints





 */


public class MainActivity extends AppCompatActivity {

    //private ListView subscriptionList;

    // Output a simple list (non-interact-able) using ListView and Adapter?
    // --> output String array

    // youtube video --> https://www.youtube.com/watch?v=A-_hKWMA7mk
    // --> simple_list_item_1 is just a default list view output in the layout xml file
    // --> String indicated that the array contains strings
    // listAdapter converts array into something that can be viewed as a List
//    private String[] subscriptionList = {"Subscription 1", "Subscription 2", "Subscription 3",
//        "Subscription 4", "Subscription 5", "Subscription 6", "Subscription 7", "Subscription 8",
//        "Subscription 9", "Subscription 10", "Subscription 11", "Subscription 12", "Subscription 13"};
    //private ArrayList<Subscription> subscriptionList;

//    ListAdapter myListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subscriptionList);
//    // () --> guy referred to this as "type cast"
//    ListView myListView = (ListView) findViewById(R.id.myListView);
//    myListView.setAdapter(myListAdapter);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //Test Subscription 1
        Date date1 = new Date();
        //Subscription sub1 = new Subscription("Sub object 1", date1, 10);
        //subscriptionList.add(sub1);
        Subscription[] subscriptionList = {
                new Subscription("Sub Object 1", date1, 10),
                new Subscription("Sub Object 2", date1, 15),
                new Subscription("Sub Object 3", date1, 30),
                new Subscription("Sub Object 4", date1, 30),
                new Subscription("Sub Object 5", date1, 30),
                new Subscription("Sub Object 6", date1, 30),
                new Subscription("Sub Object 7", date1, 30),
                new Subscription("Sub Object 8", date1, 30),
                new Subscription("Sub Object 9", date1, 30),
                new Subscription("Sub Object 10", date1, 30),
                new Subscription("Sub Object 11", date1, 30),
                new Subscription("Sub Object 12", date1, 30),
                new Subscription("Sub Object 13", date1, 30),
        };

        //ArrayAdapter<Subscription> myListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subscriptionList);
        ArrayAdapter<Subscription> myListAdapter = new SubscriptionRowAdapter(this, subscriptionList);


        // () --> guy referred to this as "type cast"
        ListView myListView = findViewById(R.id.myListView);
        myListView.setAdapter(myListAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // example on how to set a click listener onto a ListView/AdapterView
        // https://android--code.blogspot.ca/2015/08/android-listview-item-click.html 2018/01/28
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subscription selectedItem = (Subscription) adapterView.getItemAtPosition(i);
                String itemName = selectedItem.getName();
                Log.d("ITEM_SELECTED", "The item selected was: " + itemName);
         //==============
                // https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                // how to start a new Activity 2018/01/28
                //startActivity(new Intent(MainActivity.this, ViewSubscription.class));

                // Start new activity --> slightly modified by me to allow myself to pass subscription object into
                // new activity
                Intent viewSubscriptionIntent = new Intent(MainActivity.this, ViewSubscription.class);

                // How to pass object into another activity:
                // https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                // user's Samuh, Peter Mortensen, Mustafa Güven
                // 2018/01/29
                viewSubscriptionIntent.putExtra("Selected_Subscription", selectedItem);
                startActivity(viewSubscriptionIntent);



            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
