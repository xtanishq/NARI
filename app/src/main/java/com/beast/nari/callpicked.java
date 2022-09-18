package com.beast.nari;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.beast.nari.databinding.ActivityCallpickedBinding;

public class callpicked extends AppCompatActivity {
    ImageView callclose;
    private ActivityCallpickedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_callpicked);
//        getSupportActionBar().hide();
//        setContentView(R.layout.activity_callpicked);getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        callclose = findViewById(R.id.callendd);
//        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        final Sensor ProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
//        SensorEventListener sensorEventListener= new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent sensorEvent) {
//                if (sensorEvent.values[0]<ProximitySensor.getMaximumRange()){
//
//                    getWindow().getDecorView().setBackgroundColor(View.INVISIBLE);
//                }
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int i) {
//
//            }
//        };
//        sensorManager.registerListener(sensorEventListener,ProximitySensor,2*1000*1000);

        callclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(callpicked.this, NariDashboard.class));
                finishAffinity();
            }
        });
    }
}