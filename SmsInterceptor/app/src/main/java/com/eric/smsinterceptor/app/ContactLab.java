package com.eric.smsinterceptor.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Eric on 5/22/14.
 */
public class ContactLab {

    private ArrayList<Contacts> contactsArrayList;
    private static ContactLab sContactLab;
    private Context mAppContext;

    public ContactLab(Context appContext){
        mAppContext = appContext;
        contactsArrayList = new ArrayList<Contacts>();

        Contacts c1 = new Contacts();
        c1.setcName("Brian Smith");
        c1.setcNumber("3233452134");
        contactsArrayList.add(c1);

    }
    public static ContactLab get(Context c){
        if(sContactLab == null){
            sContactLab = new ContactLab(c.getApplicationContext());
        }
        return sContactLab;
    }
    public ArrayList<Contacts> getContactsArrayList(){

        return contactsArrayList;
    }

    public Contacts getContact(UUID id){
        for(Contacts c: contactsArrayList){
            if(c.getId().equals(id))
                return c;
        }
        return null;
    }
}
