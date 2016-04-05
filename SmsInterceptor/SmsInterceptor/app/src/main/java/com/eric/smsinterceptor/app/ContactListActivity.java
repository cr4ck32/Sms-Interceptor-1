package com.eric.smsinterceptor.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Eric on 5/22/14.
 */
public class ContactListActivity extends Activity{
    public static final String EXTRA_CONTACT_ID = "com.eric.smsinterceptor.app.course_id";


    private ArrayList<Contacts> mContactListArr;
    private Button mAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list_activity);

        mContactListArr = new ArrayList<Contacts>();
        mContactListArr = ContactLab.get(getApplicationContext()).getContactsArrayList();

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.contact_listview, mContactListArr /*contactArr*/);

        ListView listView = (ListView) findViewById(R.id.contact_list_activity);
        listView.setAdapter(adapter);


        mAddContact = (Button) findViewById(R.id.addContactBtn);
        mAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), add_contact_activity.class);
                startActivityForResult(i, 0);
            }
        });
    }


}
