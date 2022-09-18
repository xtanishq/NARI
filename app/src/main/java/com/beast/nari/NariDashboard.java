package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beast.nari.databinding.ActivityNariDashboardBinding;

public class NariDashboard extends AppCompatActivity {

    private ActivityNariDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nari_dashboard);

        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NariDashboard.this, MapsActivity.class));
            }
        });

    }
}