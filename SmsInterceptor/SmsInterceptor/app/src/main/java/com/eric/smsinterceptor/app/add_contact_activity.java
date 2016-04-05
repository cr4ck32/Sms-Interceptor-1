package com.eric.smsinterceptor.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Eric on 5/22/14.
 */
public class add_contact_activity extends Activity {
    private Button CancelBtn;
    private Button SaveBtn;
    private EditText name,number;
    private ArrayList<Contacts> mContactListArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_activity);

        mContactListArr = ContactLab.get(getApplicationContext()).getContactsArrayList();

        CancelBtn = (Button)findViewById(R.id.cancel_btn);
        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ContactListActivity.class);
                startActivityForResult(i,0);
            }
        });

        name = (EditText)findViewById(R.id.name_title);
        number = (EditText)findViewById(R.id.phone_number);

        SaveBtn = (Button) findViewById(R.id.save_btn);
        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //New- add course to array
                Contacts c = new Contacts();
                c.setcName(name.getText().toString());
                c.setcNumber(number.getText().toString());
                mContactListArr.add(c);

                Intent i = new Intent(getApplicationContext(), ContactListActivity.class);
                startActivityForResult(i,0);
            }

        });

    }
}
