package com.eric.smsinterceptor.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Eric on 5/16/14.
 */
public class HomeScreen extends Activity {

    public Button composeBtn;
    public Button contactBtn;
    public Button cameraBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        composeBtn = (Button)findViewById(R.id.compose_btn);
        composeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ComposeSms.class);
                startActivity(i);
            }
        });

        contactBtn = (Button)findViewById(R.id.contacts_btn);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getApplicationContext(),ContactListActivity.class);
                startActivity(i);

            }

        });

        cameraBtn = (Button)findViewById(R.id.camera_btn);
        cameraBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent i = new Intent(getApplicationContext(),CameraActivity.class);
                startActivity(i);

            }
        });

    }
}
