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

public class ViewSubscription extends AppCompatActivity {

    private EditText subText;
    Subscription selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subscription);

        // To retrieve object in second Activity
        //-->selectedItem = (Subscription) getIntent().getSerializableExtra("Selected_Subscription");
        //*Bundle b = getIntent().getExtras();
        //*String nameText = (String) selectedItem.getName();
        //*selectedItem = (Subscription) getIntent().getExtras().getSerializable("Selected_Subscription");  //newest way

        //bodyText = (EditText) findViewById(R.id.body);

        //EditText subText = (EditText) view_subscription.findViewById(R.id.subNameRow);

        // http://www.coderzheaven.com/2013/03/24/pass-object-finishing-activity-previous-activity-android/
        // 2018/02/02 (yyyy/mm/dd)
        Bundle b = getIntent().getExtras();
        if (b != null) {
            selectedItem = (Subscription) getIntent().getExtras().getSerializable("myobject");


        }

        subText = (EditText) findViewById(R.id.subscriptionViewName);
        String nameText = selectedItem.getName();
        subText.setText(nameText, TextView.BufferType.NORMAL);

        Button saveChangesButton = (Button) findViewById(R.id.saveChanges);

        saveChangesButton.setOnClickListener(new View.OnClickListener() {

            /* lonelyTwitter referenced*/
            @Override
            public void onClick(View v) {

                String newName = subText.getText().toString();
                selectedItem.changeName(newName);
                selectedItem.setWasEdited(1);
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

                Intent intent = new Intent();
                intent.putExtra("Obj", selectedItem);
                setResult(Activity.RESULT_OK, intent);

                finish();
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

