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

//import static android.provider.Telephony.Mms.Part.FILENAME;


/*

    --> PL WARNING
    --> I should probably consider this to be practice, as most of what I have added was copied
        from examples online
    --> Need to do:




    My To Do List:
    --> NEED TO DO:

    --> SHOULD DO:
        * TODO --> Double, triple check citations

    --> COULD DO:
        * TODO --> Change date input to date picker (Guarantee's proper input)

    Misc:
      * A better design would have been merging view subscription and add subscription into one activity
      * add subscription would have made an empty subscription object, and showed empty attributes
      * then add the object into the array only if it met the criteria


 */


public class MainActivity extends AppCompatActivity {

    //private  ArrayList<Subscription>  subscriptionList;

    /*
    Fixes problem when trying to add object to null array list
    https://stackoverflow.com/questions/28409089/nullpointerexception-when-adding-an-object-to-arraylist-in-android\
    2018/02/03 (yyyy/mm/dd)
    Jens
     */
    private static final String FILENAME = "subscriptions.sav";
    private ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();     // need to initialize first
    private double monthlyTotal = 0;
    private TextView monthlyTotalTV;
    private ArrayAdapter<Subscription> myListAdapter; //= new SubscriptionRowAdapter(this, subscriptionList);
    private int lastClickedItem = -1;

    //Date date1 = new Date();

//    public MainActivity() {
//        //this.subscriptionList = {new Subscription("Sub Object 1", date1, 10)};
//        this.subscriptionList = {
//                new Subscription("Sub Object 1", date1, 10),
//                new Subscription("Sub Object 2", date1, 15),
//                new Subscription("Sub Object 3", date1, 30),
//                new Subscription("Sub Object 4", date1, 30),
//                new Subscription("Sub Object 5", date1, 30),
//                new Subscription("Sub Object 6", date1, 30),
//                new Subscription("Sub Object 7", date1, 30),
//                new Subscription("Sub Object 8", date1, 30),
//                new Subscription("Sub Object 9", date1, 30),
//                new Subscription("Sub Object 10", date1, 30),
//                new Subscription("Sub Object 11", date1, 30),
//                new Subscription("Sub Object 12", date1, 30),
//                new Subscription("Sub Object 13", date1, 30),
//        };
//    }


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

        loadFromFile();



        //Toolbar toolbar = (Toolbar) findViewById(R.id.mainToolbar);

        //setSupportActionBar(toolbar);

        //Test Subscription 1
        //Date date1 = new Date();
        //Subscription sub1 = new Subscription("Sub object 1", date1, 10);
        //subscriptionList.add(sub1);

        // make subscription a constructor variable
        // this.subscription
        Date date1 = new Date();

//        Subscription[] subscriptionList = {
//                new Subscription("wew", date1, 10),
//                new Subscription("Sub Object 2", date1, 15),
//                new Subscription("Sub Object 3", date1, 30),
//                new Subscription("Sub Object 4", date1, 30),
//                new Subscription("Sub Object 5", date1, 30),
//                new Subscription("Sub Object 6", date1, 30),
//                new Subscription("Sub Object 7", date1, 30),
//                new Subscription("Sub Object 8", date1, 30),
//                new Subscription("Sub Object 9", date1, 30),
//                new Subscription("Sub Object 10", date1, 30),
//                new Subscription("Sub Object 11", date1, 30),
//                new Subscription("Sub Object 12", date1, 30),
//                new Subscription("Sub Object 13", date1, 30),
//        };


//******addSubscription(new Subscription("Netflix", date1, 9.00));
        //Log.d("UPDATE_MONTHLY_TOTAL", "new total = " + this.monthlyTotal);
//        addSubscription(new Subscription("Subscription 1", date1, 15.0));
//        //Log.d("UPDATE_MONTHLY_TOTAL", "new total = " + this.monthlyTotal);
//        addSubscription(new Subscription("Subscription 2", date1, 30.0));
//        //Log.d("UPDATE_MONTHLY_TOTAL", "new total = " + this.monthlyTotal);
//        addSubscription(new Subscription("Subscription 3", date1, 30.0));
//        addSubscription(new Subscription("Subscription 4", date1, 30.));
//        addSubscription(new Subscription("Subscription 5", date1, 30.0));
//        addSubscription(new Subscription("Subscription 6", date1, 30.0));
//        addSubscription(new Subscription("Subscription 7", date1, 30.0));
//        addSubscription(new Subscription("Subscription 8", date1, 30.0));
//        addSubscription(new Subscription("Subscription 9", date1, 30.0));
//        addSubscription(new Subscription("Subscription 10", date1, 30.0));
//        addSubscription(new Subscription("Subscription 11", date1, 30.0));
//        addSubscription(new Subscription("Subscription 12", date1, 30.0));

//        subscriptionList.add(new Subscription("wew", date1, 10));
        //ArrayAdapter<Subscription> myListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subscriptionList);
//        ArrayAdapter<Subscription> myListAdapter = new SubscriptionRowAdapter(this, subscriptionList);
        myListAdapter = new SubscriptionRowAdapter(this, subscriptionList);

        monthlyTotalTV = findViewById(R.id.mainMonthlyTotal);
        this.updateMonthlyCharge();  // just for testing, remove later

        //monthlyTotalTV.setText("Monthly Cost: " + monthlyTotalText);

        // () --> guy referred to this as "type cast"
        ListView myListView = findViewById(R.id.myListView);
        myListView.setAdapter(myListAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent addSubscriptionIntent = new Intent(MainActivity.this, AddSubscription.class);
//                startActivity(new Intent(MainActivity.this, AddSubscription.class));
                startActivityForResult(addSubscriptionIntent, 2);

            }
        });

        // example on how to set a click listener onto a ListView/AdapterView
        // https://android--code.blogspot.ca/2015/08/android-listview-item-click.html 2018/01/28
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Subscription selectedItem = (Subscription) adapterView.getItemAtPosition(i);
                selectedItem.setListViewPosition(i);
                lastClickedItem = i;

                String itemName = selectedItem.getName();
                Log.d("ITEM_SELECTED", "The item selected was" + itemName);
                Log.d("ITEM_POSITION", "The item position is" + i);
         //==============
                // https://stackoverflow.com/questions/24610527/how-do-i-get-a-button-to-open-another-activity-in-android-studio
                // how to start a new Activity 2018/01/28
                //startActivity(new Intent(MainActivity.this, ViewSubscription.class));

                // Start new activity --> slightly modified by me to allow myself to pass subscription object into
                // new activity
                //*Intent viewSubscriptionIntent = new Intent(MainActivity.this, ViewSubscription.class);

                // How to pass object into another activity:
                // https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                // user's Samuh, Peter Mortensen, Mustafa Güven
                // 2018/01/29
                //-->viewSubscriptionIntent.putExtra("Selected_Subscription", selectedItem);
                //-->startActivity(viewSubscriptionIntent);




                // ---> newest attempt --> not working. try to fix
                // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
                //*viewSubscriptionIntent.putExtra("Selected_Subscription", selectedItem);
                //*startActivityForResult(viewSubscriptionIntent, 1);
                //Log.d("RETURN", "returned from view subscription activity");

                // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
                // 2018/02/02 (yyyy/mm/dd)
                Intent intent = new Intent(MainActivity.this, ViewSubscription.class);
                intent.putExtra("myobject", selectedItem);
                startActivityForResult(intent, 1);


            }
        });


    }


    /*
     Called when an activity returns to its previous activity
     https://stackoverflow.com/questions/11946271/android-detect-when-an-activity-returns-to-the-previous-activity
     User: JiTHiN
     2018/02/02 (yyyy/mm/dd)
    */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("RETURN", "returned from view subscription activity");
        //Subscription editedItem = (Subscription) getIntent().getSerializableExtra("edited_Subscription");
        //String nameText = (String) editedItem.getName();
        //Log.d("MAIN ACT", nameText);

    }


    // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
    // 2018/02/02 (yyyy/mm/dd)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) { //requesting view subscription
            if (resultCode == Activity.RESULT_OK) {
                Bundle b = data.getExtras();
                if (b != null) {
                    Subscription selectedItem = (Subscription) b.getSerializable("Obj");
                    Log.d("ON_ACTIVITY_RESULT", selectedItem.getName());
                    if (selectedItem.getWasEdited() == 1) {
                        Log.d("ON_ACTIVITY_RESULT", "selectedItem was edited");
                        selectedItem.setWasEdited(0);
                        /* edit the array list */
                        updateSubscriptionList(selectedItem);
                        this.updateMonthlyCharge();
                        this.myListAdapter.notifyDataSetChanged();
                        saveInFile();

                    }
                    else {
                        Log.d("ON_ACTIVITY_RESULT", "selectedItem was NOT edited");
                    }

                }
//                else if (resultCode == 0) {
//                    // do something
//                    Log.d("MAIN_RC_0", "Back to main, result code 0");
//                }

            } else if (resultCode == 0) {           // User deleted item
                Log.d("MAIN_RC_0", "Back to main, result code 0");
                this.subscriptionList.remove(this.lastClickedItem);
                this.updateMonthlyCharge();
                this.myListAdapter.notifyDataSetChanged();
                this.lastClickedItem = -1;
                saveInFile();

            }
        }

        else if (requestCode == 2) {    // requesting add subscription
            if (resultCode == Activity.RESULT_OK) {     // the user added a subscription SUCCESSFULLY
                Bundle b = data.getExtras();
                if (b != null) {
                    Subscription newSubscription = (Subscription) b.getSerializable("newSubscription");
                    addSubscription(newSubscription);
                    this.updateMonthlyCharge();
                    //add_subtractMonthlyCost(newSubscription.getMonthlyCharge());
                    //this.monthlyTotalTV.setText("Monthly Cost: " + this.monthlyTotal);
                    this.myListAdapter.notifyDataSetChanged();
                    saveInFile();
                }

            }
            else if(resultCode == Activity.RESULT_CANCELED) {   // the user BACKED OUT
                // do something
            }


        }


    }//onActivityResult

    /*
    replace old subscription with edited subscription in its original position
     */
    private void updateSubscriptionList(Subscription editedSubscription) {

        int position = editedSubscription.getListViewPosition();
        this.subscriptionList.set(position, editedSubscription);

    }

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

//    @Override
//    protected void onStart() {
//
//        // TODO Auto-generated method stub
//        super.onStart();
//        Log.i("LifeCycle --->", "onStart is called");
//
//        loadFromFile();
//
//        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweetList);
//        oldTweetsList.setAdapter(adapter);
//
//    }

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

