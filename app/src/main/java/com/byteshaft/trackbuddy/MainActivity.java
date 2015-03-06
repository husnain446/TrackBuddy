package com.byteshaft.trackbuddy;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    Button sendBTN;
    Button locationBTN;
    EditText mEditText;
    EditText mEditText2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBTN = (Button) findViewById(R.id.sendButton);
        locationBTN = (Button) findViewById(R.id.locationButton);
        mEditText = (EditText) findViewById(R.id.phoneNumber);
        mEditText2 = (EditText) findViewById(R.id.smsText);
        sendBTN.setOnClickListener(this);
        locationBTN.setOnClickListener(this);

    }


    private void sendSms(String number, String txt) {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, txt, null, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                sendSms(mEditText.getText().toString(), mEditText2.getText().toString());
                mEditText.getText().clear();
        }
        switch (v.getId()) {
            case R.id.locationButton:
                LocationService gps = new LocationService(MainActivity.this);
                gps.getLocation();
                if(gps.canGetLocation){
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    mEditText2.setText("https://maps.google.com/maps?q=" + latitude + "," + longitude);
                }

        }
    }
}