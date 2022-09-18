package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.beast.nari.databinding.ActivityGuardianDashboardBinding;

public class guardianDashboard extends AppCompatActivity {

    private ActivityGuardianDashboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_guardian_dashboard);

        binding.addNari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddNariActivity.class));
            }
        });

    }
}