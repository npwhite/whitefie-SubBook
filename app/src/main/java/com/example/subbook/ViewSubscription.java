/*
followed this guide on sending objects to/from activities
http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
2018/02/02 (yyyy/mm/dd)
 */

package com.example.subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * represents the ViewSubscription activity
 * @see Subscription
 * @see MainActivity
 */
public class ViewSubscription extends AppCompatActivity {

    private String newName;
    private Double newPrice;
    private String newTextDate;
    private Date newDateDate;
    private String newComment;


    private EditText subscriptionViewName;
    private EditText subscriptionViewPrice;
    private EditText subscriptionViewDate;
    private EditText subscriptionViewComment;

    Subscription selectedItem;

    // describes if Subscription object is safe to make or not
    private boolean STATUS_INVALID = false;          // Subscription object CAN be safely made
    private boolean STATUS_VALID = true;            // Subscription object CANNOT be safely made
    private boolean STATUS_VALUE = STATUS_VALID;   // status of creation safety value

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
        // 2018/02/02 (yyyy/mm/dd)
        Bundle b = getIntent().getExtras();
        if (b != null) {
            selectedItem = (Subscription) getIntent().getExtras().getSerializable("myobject");
        }

        subscriptionViewName = (EditText) findViewById(R.id.subscriptionViewName);
        subscriptionViewPrice = (EditText) findViewById(R.id.subscriptionViewPrice);
        subscriptionViewDate = (EditText) findViewById(R.id.subscriptionViewDate);
        subscriptionViewComment = (EditText) findViewById(R.id.subscriptionViewComment);

        String nameText = selectedItem.getName();

        /*
        Converting double to string
        https://stackoverflow.com/questions/5766318/converting-double-to-string
        Bhavit S. Sengar
         */
        String priceText = String.valueOf(selectedItem.getMonthlyCharge());

        /* Making a simple custom date formatter */
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = myDateFormat.format(selectedItem.getDateStarted());

        String commentText = selectedItem.getComment();

        /* Set text box values to initial values*/
        subscriptionViewName.setText(nameText, TextView.BufferType.NORMAL);
        subscriptionViewPrice.setText(priceText);
        subscriptionViewDate.setText(dateText);
        subscriptionViewComment.setText(commentText);

        /* on click listener for delete button */
        Button deleteButton = (Button) findViewById(R.id.deleteSubscription);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* user deleted entry  */
                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);        // not sure if I should be using this result

                finish();       /* return to previous activity */

            }
        });

        /* on click listener for save button*/
        Button saveChangesButton = (Button) findViewById(R.id.saveChanges);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {       // User trying to save changes

            /* lonelyTwitter referenced */
            @Override
            public void onClick(View v) {       /* values changed only if all changed values meet criteria */

                /* Get name text */
                newName = subscriptionViewName.getText().toString();
                if (newName.isEmpty()) {
                    STATUS_VALUE = STATUS_INVALID;
                    /*
                    set pop-up error message
                    https://stackoverflow.com/questions/18225365/show-error-on-the-tip-of-the-edit-text-android
                    2018/02/04 (yyyy/mm/dd)
                    SilentKiller
                     */
                    subscriptionViewName.setError("This field cannot be blank");
                }

                /* Get Date */
                newTextDate = subscriptionViewDate.getText().toString();
                boolean dateFormatValid = Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", newTextDate);

                if (dateFormatValid) {
                    newDateDate = AddSubscription.parseStringToDate(newTextDate);
                } else {
                    STATUS_VALUE = STATUS_INVALID;
                    subscriptionViewDate.setError("Invalid Date, format: yyyy-mm-dd");
                }

                /* Get Price Text */
                /*
                Convert String to double
                http://javadevnotes.com/java-string-to-double-examples
                2018/02/04
                 */
                try {
                    newPrice = Double.parseDouble(subscriptionViewPrice.getText().toString());
                } catch (NullPointerException | NumberFormatException doubleParsExc) {
                    STATUS_VALUE = STATUS_INVALID;
                    subscriptionViewPrice.setError("Invalid Price");
                }

                /* Get Comment Text */
                try {
                    newComment = subscriptionViewComment.getText().toString();
                } catch (NullPointerException badCommentExc) {
                    // do something
                    STATUS_VALUE = STATUS_INVALID;

                }

                /* if the subscription object is safe to edit */
                if (STATUS_VALUE == STATUS_VALID) {
                    /* edit the subscription */
                    selectedItem.changeName(newName);
                    selectedItem.changeDate(newDateDate);
                    selectedItem.setMonthlyCharge(newPrice);
                    selectedItem.setComment(newComment);

                    /* notify that subscription was edited*/
                    selectedItem.setWasEdited(1);

                    /* create new intent and add edited item */
                    /* onActivityResult */
                    Intent intent = new Intent();
                    intent.putExtra("Obj", selectedItem);
                    setResult(Activity.RESULT_OK, intent);

                    /* return to previous activity*/
                    finish();

                }
                STATUS_VALUE = STATUS_VALID;        /* Reset status to default */
                Log.d("new name saved", selectedItem.getName());
            }
        });
    }

    // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
    // 2018/02/02 (yyyy/mm/dd)

    /**
     * execute if back button pressed
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Obj", selectedItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}

