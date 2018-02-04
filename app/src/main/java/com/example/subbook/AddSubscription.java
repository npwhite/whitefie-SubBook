package com.example.subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

public class AddSubscription extends AppCompatActivity {

    private String nameText;
    private Double priceText;
    private String dateText;
    private String commentText;

    // Had this error before:
    // Variable is accessed within inner class. Needs to be declared final
    // referenced lonelyTwitter --> declare text boxes outside of method? why?
    private EditText subscriptionAddName;
    private EditText subscriptionAddPrice;
    //private EditText subscriptionAddDate;
    private EditText subscriptionAddComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        Button addSubscriptionButton = findViewById(R.id.addSubscription);
        subscriptionAddName = findViewById(R.id.subscriptionAddName);
        subscriptionAddPrice = findViewById(R.id.subscriptionAddPrice);
        //subscriptionAddDate = findViewById(R.id.subscriptionAddDate);
        subscriptionAddComment = findViewById(R.id.subscriptionAddComment);


        addSubscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ADD_SUBSCRIPTION", "Add subscription button pressed");
                /*
                User trying to save input as a new subscription
                Needed: get input
                Timer permits: error check it
                */

                nameText = subscriptionAddName.getText().toString();

                /*
                Convert String to double
                http://javadevnotes.com/java-string-to-double-examples
                2018/02/04
                 */
                priceText = Double.parseDouble(subscriptionAddPrice.getText().toString());
                //dateText = subscriptionAddDate.getText().toString();
                Date date = new Date();

                commentText = subscriptionAddComment.getText().toString();

                Subscription newSubscription = new Subscription(nameText, date, priceText, commentText);
                Log.d("SUBSCRIPTION CREATED", "New subscription made: " + newSubscription.getName());

                Intent intent = new Intent();
                intent.putExtra("newSubscription", newSubscription);
                setResult(Activity.RESULT_OK, intent);

                finish();




            }
        });

        // if back button pressed --> RESULT_CANCELLED --> add later
    }
}
