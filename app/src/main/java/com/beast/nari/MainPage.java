package com.beast.nari;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.beast.nari.databinding.ActivityMainPageBinding;

public class MainPage extends AppCompatActivity {

    RelativeLayout relativeLayout1,relativeLayout2;
    private ActivityMainPageBinding binding;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String signStatus, job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main_page);
        sp = getSharedPreferences("DATA_NAME", MODE_PRIVATE);
        editor = sp.edit();

        signStatus = sp.getString("SIGN_UP_STATUS", null);
        job = sp.getString("JOB", null);

        if (signStatus != null){

            if (job.equalsIgnoreCase("NARI")) {

                startActivity(new Intent(getApplicationContext(), NariDashboard.class));
                finish();
            }
            if (job.equalsIgnoreCase("GUARDIAN")) {

                startActivity(new Intent(getApplicationContext(), guardianDashboard.class));
                finish();
            }
        }


        binding.nari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("JOB", "NARI");
                editor.apply();

                startActivity(new Intent(MainPage.this,nari_login.class));

            }
        });


        binding.guardian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("JOB", "GUARDIAN");
                editor.apply();

                startActivity(new Intent(MainPage.this,guardianLogin.class));
            }
        });
    }
}