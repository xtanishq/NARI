package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NariDashboard extends AppCompatActivity {
    ImageView msk,helpline,pdfself;
    ImageView fakecall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nari_dashboard);
        msk = findViewById(R.id.msk);
        fakecall = findViewById(R.id.fakecall);
        helpline = findViewById(R.id.helpLine);
        pdfself = findViewById(R.id.self);

        msk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:181"));
                startActivity(intent);
            }
        });
        helpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:181"));
                startActivity(intent);

            }
        });


        fakecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NariDashboard.this, ring.class));

            }
        });
        pdfself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NariDashboard.this,pdfview.class));
            }
        });
    }
}