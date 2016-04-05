package com.eric.smsinterceptor.app;

import java.util.UUID;

/**
 * Created by Eric on 5/22/14.
 */
public class Contacts {
    private UUID mID;
    private String cName;
    private String cNumber;

    public Contacts(){
        mID = UUID.randomUUID();
    }
    @Override
    public String toString(){
        return cName;
    }
    public UUID getId(){
        return mID;
    }
    public void setcName(String name){
        cName = name;
    }
    public String getcName(){
        return cName;
    }
    public void setcNumber(String number){
        cNumber = number;
    }
    public String getcNumber(){
        return cNumber;
    }
}
