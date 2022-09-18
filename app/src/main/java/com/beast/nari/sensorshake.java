package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sensorshake extends AppCompatActivity {
    TextView msg;
    EditText number;
    Button send_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensorshake);
        number = findViewById(R.id.number);
        msg = findViewById(R.id.messe);
        send_btn = findViewById(R.id.send);
        send_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>Build.VERSION_CODES.M){


                    if (checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){

                        sendSms();
                    }
                    else{

                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }


            }
        });
    }
    private void sendSms(){
        String num = number.getText().toString();
        String messg = msg.getText().toString();
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(num,null,messg,null,null);
            Toast.makeText(this, "Message is sent", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(sensorshake.this,NariDashboard.class));
        }
        catch (Exception e){

            e.printStackTrace();
            Toast.makeText(this, "Messge not sent", Toast.LENGTH_SHORT).show();
        }
    }
}