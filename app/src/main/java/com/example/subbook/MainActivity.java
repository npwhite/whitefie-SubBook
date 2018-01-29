package com.example.subbook;

import java.util.ArrayList;

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

public class MainActivity extends AppCompatActivity {

    //private ListView subscriptionList;

    // Output a simple list (non-interact-able) using ListView and Adapter?
    // --> output String array

    // youtube video --> https://www.youtube.com/watch?v=A-_hKWMA7mk
    // --> simple_list_item_1 is just a default list view output in the layout xml file
    // --> String indicated that the array contains strings
    // listAdapter converts array into something that can be viewed as a List
    private String[] subscriptionList = {"Subscription 1", "Subscription 2", "Subscription 3",
        "Subscription 4", "Subscription 5", "Subscription 6", "Subscription 7", "Subscription 8",
        "Subscription 9", "Subscription 10", "Subscription 11", "Subscription 12", "Subscription 13"};
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

        //subscriptionList = (ListView) findViewById(R.id.recipe_list_view);      // Internet
        ListAdapter myListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, subscriptionList);
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
        // https://android--code.blogspot.ca/2015/08/android-listview-item-click.html 2018/01/29
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                Log.d("ITEM_SELECTED", "The item selected was: " + selectedItem);
         //==============
                startActivity(new Intent(MainActivity.this, ViewSubscription.class));


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
