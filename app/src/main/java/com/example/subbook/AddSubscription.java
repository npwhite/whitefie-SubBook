package com.example.subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class AddSubscription extends AppCompatActivity {

    private String nameText;
    private Double priceText;
    private String TextDate;
    private Date DateDate;
    private String commentText;

    // describes if Subscription object is safe to make or not
    private boolean STATUS_INVALID = false;          // Subscription object CAN be safely made
    private boolean STATUS_VALID = true;            // Subscription object CANNOT be safely made
    private boolean STATUS_VALUE = STATUS_VALID;   // status of creation safety value


    // Had this error before:
    // Variable is accessed within inner class. Needs to be declared final
    // referenced lonelyTwitter --> declare text boxes outside of method? why?
    private EditText subscriptionAddName;
    private EditText subscriptionAddPrice;
    private EditText subscriptionAddDate;
    private EditText subscriptionAddComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subscription);

        Button addSubscriptionButton = findViewById(R.id.addSubscription);
        subscriptionAddName = findViewById(R.id.subscriptionAddName);
        subscriptionAddPrice = findViewById(R.id.subscriptionAddPrice);
        subscriptionAddDate = findViewById(R.id.subscriptionAddDate);
        subscriptionAddComment = findViewById(R.id.subscriptionAddComment);


        addSubscriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ADD_SUBSCRIPTION", "Add subscription button pressed");

                /* Get name text */
                /*
                User trying to save input as a new subscription
                Needed: get input
                Timer permits: error check it
                */

                nameText = subscriptionAddName.getText().toString();

                if (nameText.isEmpty()) {
                    STATUS_VALUE = STATUS_INVALID;
                    /*
                    set pop-up error message
                    https://stackoverflow.com/questions/18225365/show-error-on-the-tip-of-the-edit-text-android
                    2018/02/04 (yyyy/mm/dd)
                    SilentKiller
                     */
                    subscriptionAddName.setError("This field cannot be blank");
                }


                /* Get Price Text */
                /*
                Convert String to double
                http://javadevnotes.com/java-string-to-double-examples
                2018/02/04
                 */
                try {
                    priceText = Double.parseDouble(subscriptionAddPrice.getText().toString());
                } catch (NullPointerException | NumberFormatException doubleParsExc) {
                    // do something
                    STATUS_VALUE = STATUS_INVALID;
                    subscriptionAddPrice.setError("Invalid Price");
                }

                /* Get Date */
                //try {
                TextDate = subscriptionAddDate.getText().toString();
                boolean dateFormatValid = Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", TextDate);

                if (dateFormatValid) {
                    DateDate = parseStringToDate(TextDate);
                } else {
                    STATUS_VALUE = STATUS_INVALID;
                    subscriptionAddDate.setError("Invalid Date, format: yyyy-mm-dd");
                }
                //} catch (NullPointerException badDateExc) {
                    // do something


                //}

                /* Get Comment Text */
                try {
                    commentText = subscriptionAddComment.getText().toString();
                } catch (NullPointerException badCommentExc) {
                    // do something
                    STATUS_VALUE = STATUS_INVALID;

                }

                if (STATUS_VALUE == STATUS_VALID) {
                    Log.d("MAKING_SUBSCRIPTION", "Trying to make a subscription");
                    Subscription newSubscription = new Subscription(nameText, DateDate, priceText, commentText);
                    Log.d("SUBSCRIPTION CREATED", "New subscription made: " + newSubscription.getName());

                    Intent intent = new Intent();
                    intent.putExtra("newSubscription", newSubscription);
                    setResult(Activity.RESULT_OK, intent);

                    finish();
                }

                STATUS_VALUE = STATUS_VALID;        // Reset status to default


            }
        });

        // if back button pressed --> RESULT_CANCELLED --> add later
    }

    /*
    converts a String date to Date date
    https://stackoverflow.com/questions/9945072/convert-string-to-date-in-java
    2018/02/04 (yyyy/mm/dd)
    Boris Strandjev
     */
    public static Date parseStringToDate(String dateText) {

        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-d");
        Date newDate = new Date();
        try {
            newDate = myDateFormat.parse(dateText);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDate;

    }

}
