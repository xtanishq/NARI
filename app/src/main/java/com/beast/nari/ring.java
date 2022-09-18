package com.beast.nari;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ring extends AppCompatActivity {
    ImageView callend,callstartt;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        callend = findViewById(R.id.callend);
        callstartt = findViewById(R.id.callstart);

        mediaPlayer = MediaPlayer.create(this,R.raw.ringtone);
        mediaPlayer.start();
//        callend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer.stop();
//                startActivity(new Intent(ring.this,call_demo.class));
//            }
//        });
       callstartt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               mediaPlayer.stop();
               startActivity(new Intent(ring.this,callpicked.class));
//                finishAffinity();
               finish();
           }
       });
    }
}