package com.eric.smsinterceptor.app;

/**
 * Created by Eric on 5/16/14.
 */
public class Copy {
    private String phoneNum;
    private String num;

    public Copy(String phone){
        phoneNum = phone;
        num = phoneNum;
    }
    public Copy(){
        phoneNum = num;
    }

    public void setPhoneNum(String number){
        phoneNum = number;
    }
    public String getPhoneNum(){
        return phoneNum;
    }

}
