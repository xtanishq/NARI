package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.beast.nari.databinding.ActivityGuardianDashboardBinding;

public class guardianDashboard extends AppCompatActivity {

    private ActivityGuardianDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_dashboard);
    }
}