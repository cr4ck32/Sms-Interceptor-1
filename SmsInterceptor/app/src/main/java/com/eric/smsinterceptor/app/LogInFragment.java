package com.eric.smsinterceptor.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eric on 5/22/14.
 */
public class LogInFragment extends Fragment {
    private EditText username = null;
    private EditText password = null;
    private TextView attempts;
    private Button loginBtn;
    int counter = 3;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.log_in_fragment,parent,false);


        username = (EditText) v.findViewById(R.id.username_text);
        password = (EditText) v.findViewById(R.id.password_text);
        attempts = (TextView) v.findViewById(R.id.attempts_text);
        attempts.setText(Integer.toString(counter));

        loginBtn = (Button)v.findViewById(R.id.login_btn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(getActivity(), "Redirecting...",
                            Toast.LENGTH_SHORT).show();

                    username.setText("");
                    password.setText("");

                    Intent i = new Intent(getActivity(),MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getActivity(), "Invalid Credentials",
                            Toast.LENGTH_SHORT).show();

                    attempts.setBackgroundColor(Color.RED);
                    counter--;
                    attempts.setText(Integer.toString(counter));

                    if(counter == 0){
                        loginBtn.setEnabled(false);
                    }
                }
            }

        });

        return v;
    }
}
