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

        /*
        Making a simple custom date formatter
         */
        SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateText = myDateFormat.format(selectedItem.getDateStarted());

        String commentText = selectedItem.getComment();

        /* Set text box values to initial values*/
        subscriptionViewName.setText(nameText, TextView.BufferType.NORMAL);
        subscriptionViewPrice.setText(priceText);
        subscriptionViewDate.setText(dateText);
        subscriptionViewComment.setText(commentText);

        Button deleteButton = (Button) findViewById(R.id.deleteSubscription);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                setResult(Activity.RESULT_CANCELED, intent);        // not sure if I should be using this result

                finish();

            }
        });

        Button saveChangesButton = (Button) findViewById(R.id.saveChanges);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {       // User trying to save changes

            /* lonelyTwitter referenced*/
            @Override
            public void onClick(View v) {       /* values changed only if all changed values meet criteria */

                /* Get name text */
                /*
                User trying to save input as a new subscription
                Needed: get input
                Timer permits: error check it
                */
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
//                    selectedItem.changeDate(dateDate);
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
                    // do something
                    STATUS_VALUE = STATUS_INVALID;
                    subscriptionViewPrice.setError("Invalid Price");
                }

                //}

                /* Get Comment Text */
                try {
                    newComment = subscriptionViewComment.getText().toString();
                } catch (NullPointerException badCommentExc) {
                    // do something
                    STATUS_VALUE = STATUS_INVALID;

                }



                if (STATUS_VALUE == STATUS_VALID) {
                    selectedItem.changeName(newName);
                    selectedItem.changeDate(newDateDate);
                    selectedItem.setMonthlyCharge(newPrice);
                    selectedItem.setComment(newComment);

                    selectedItem.setWasEdited(1);
                    Intent intent = new Intent();
                    intent.putExtra("Obj", selectedItem);
                    setResult(Activity.RESULT_OK, intent);

                    finish();

                }

                STATUS_VALUE = STATUS_VALID;        // Reset status to default








//                selectedItem.setWasEdited(1);
                Log.d("new name saved", selectedItem.getName());
                //Intent editedSubscriptionIntent = new Intent(ViewSubscription.this, MainActivity.class);

                // How to pass object into another activity:
                // https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
                // user's Samuh, Peter Mortensen, Mustafa Güven
                // 2018/01/29
                //-->editedSubscriptionIntent.putExtra("edited_Subscription", selectedItem);

                //Intent intent = new Intent();
                //intent.putExtra("obj",selectedItem);
                //setResult(Activity.RESULT_OK,intent);
                //finish();
                //startActivity(editedSubscriptionIntent);

//                Intent intent = new Intent();
//                intent.putExtra("Obj", selectedItem);
//                setResult(Activity.RESULT_OK, intent);
//
//                finish();
            }


        });
    }

    // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
    // 2018/02/02 (yyyy/mm/dd)
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("Obj", selectedItem);
        setResult(Activity.RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }

    //subText.setText(nameText, TextView.BufferType.NORMAL);
    //Log.d("OBJECT_PASSED", "Subscription passed: " + selectedItem.getName());


}

