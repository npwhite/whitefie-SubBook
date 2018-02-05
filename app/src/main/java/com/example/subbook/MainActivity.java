package com.example.subbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


/*
    My To Do List:
    --> NEED TO DO:

    --> SHOULD DO:

    --> COULD DO:
        * TODO --> Change date input to date picker (Guarantee's proper input)

    Misc:
      * A possibly better design would have been merging view subscription and add subscription into
        one activity add subscription would have made an empty subscription object, and showed empty
        attributes then add the object into the array only if it met the criteria
 */

/**
 * represents the subBook activity
 * @see Subscription
 * @see AddSubscription
 * @see ViewSubscription
 * @see SubscriptionRowAdapter
 */
public class MainActivity extends AppCompatActivity {

    /*
    Fixes problem when trying to add object to null array list
    https://stackoverflow.com/questions/28409089/nullpointerexception-when-adding-an-object-to-arraylist-in-android\
    2018/02/03 (yyyy/mm/dd)
    Jens
     */
    private static final String FILENAME = "subscriptions.sav";
    private ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();     // need to initialize first
    private double monthlyTotal;
    private TextView monthlyTotalTV;
    private ArrayAdapter<Subscription> myListAdapter; //= new SubscriptionRowAdapter(this, subscriptionList);
    private int lastClickedItem = -1;

    /**
    On create method
    @param savedInstanceState saved instance of previous run
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* load data from previous run*/
        loadFromFile();    

        myListAdapter = new SubscriptionRowAdapter(this, subscriptionList);
        monthlyTotalTV = findViewById(R.id.mainMonthlyTotal);
        /* update monthly charge text view to proper value */
        updateMonthlyCharge();
        
        ListView myListView = findViewById(R.id.myListView);
        myListView.setAdapter(myListAdapter);

        /* floating action button which takes user to create subscription activity*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addSubscriptionIntent = new Intent(MainActivity.this, AddSubscription.class);
                startActivityForResult(addSubscriptionIntent, 2);

            }
        });

        /* click listener which takes user to view subscription for selected subscription */
        // example on how to set a click listener onto a ListView/AdapterView
        // https://android--code.blogspot.ca/2015/08/android-listview-item-click.html 2018/01/28
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subscription selectedItem = (Subscription) adapterView.getItemAtPosition(i);
                selectedItem.setListViewPosition(i);
                /* store last clicked item so if user deletes subscription, we know which position it was in*/
                lastClickedItem = i;

                /*
                http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android
                2018/02/02 (yyyy/mm/dd)
                CODERZHEAVEN
                 */
                Intent intent = new Intent(MainActivity.this, ViewSubscription.class);
                intent.putExtra("myobject", selectedItem);
                startActivityForResult(intent, 1);

            }
        });
    }


    /**
     * Called when user returns from child activity
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RETURN", "returned from view subscription activity");
    }


    /*
    http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
    2018/02/02 (yyyy/mm/dd)
    CODERZHEAVEN
    */

    /**
     * represents
     * @param requestCode code of what activity we are requesting/info we want
     * @param resultCode what the activity returned
     * @param data data in an intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) { //requesting view subscription
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();
                if (b != null) {
                    /* retrieve selected item from view subscription activity */
                    Subscription selectedItem = (Subscription) b.getSerializable("Obj");
                    if (selectedItem.getWasEdited() == 1) {
                        /* User edited the selected subscription */

                        /* reset was edited for item */
                        selectedItem.setWasEdited(0);
                        /* update the array list with updated subscription */
                        updateSubscriptionList(selectedItem);
                        this.updateMonthlyCharge();
                        this.myListAdapter.notifyDataSetChanged();
                        saveInFile();
                    }
                    else {
                        /* User did NOT edit selected item*/
                        Log.d("ON_ACTIVITY_RESULT", "selectedItem was NOT edited");
                    }

                }

            } else if (resultCode == 0) {           /* User deleted item */
                this.subscriptionList.remove(this.lastClickedItem);
                this.updateMonthlyCharge();
                this.myListAdapter.notifyDataSetChanged();
                this.lastClickedItem = -1;          /* reset lastClickedItem to default */
                saveInFile();

            }
        }

        else if (requestCode == 2) {    /* requesting add subscription */
            if (resultCode == Activity.RESULT_OK) {     /* the user added a subscription SUCCESSFULLY */
                Bundle b = data.getExtras();            /* unpack the bundle */
                if (b != null) {
                    /* subscription created */
                    Subscription newSubscription = (Subscription) b.getSerializable("newSubscription");
                    /* add subscription to array list */
                    addSubscription(newSubscription);
                    this.updateMonthlyCharge();
                    this.myListAdapter.notifyDataSetChanged();
                    /* save */
                    saveInFile();
                }

            }
            else if(resultCode == Activity.RESULT_CANCELED) {
                /* The user backed out */
            }


        }


    }

    /*
    replace old subscription with edited subscription in its original position
     */

    /**
     * replace old subscription with edited subscription in its original position
     * @param editedSubscription the edited subscription
     */
    private void updateSubscriptionList(Subscription editedSubscription) {

        int position = editedSubscription.getListViewPosition();
        /* note: position is properly edited in listView click */
        this.subscriptionList.set(position, editedSubscription);

    }

    /**
     * refreshes monthlyCharge text view to current monthly total
     */
    public void updateMonthlyCharge (){
        String monthlyTotalText;
        this.monthlyTotal = 0;
        for (Subscription element: this.subscriptionList) {
            this.monthlyTotal += element.getMonthlyCharge();
        }

        /*
        floating point precision change
        https://stackoverflow.com/questions/4391448/floating-point-with-2-digits-after-point
        2018/02/04
        Ryan Reeves
         */
        monthlyTotalText = String.format("%.02f", this.monthlyTotal);
        this.monthlyTotalTV.setText("Monthly Total: " + monthlyTotalText);
    }

    /**
     * add a new subscription to the array
     * @param subscription subscription object
     */
    public void addSubscription(Subscription subscription){
        this.monthlyTotal += subscription.getMonthlyCharge();
        this.subscriptionList.add(subscription);
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

    /*
    taken verbatim from lonely twitter
    University of Alberta
     */
    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-23
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subscriptionList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            subscriptionList = new ArrayList<Subscription>();
        }

    }

    /*
    taken verbatim from lonely twitter
    University of Alberta
     */
    public void saveInFile() {
        try {

            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(this.subscriptionList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


}

