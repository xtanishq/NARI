package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class guardianSignup extends AppCompatActivity {

    Button signup,already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardian_signup);

        signup = findViewById(R.id.signup2);
        already = findViewById(R.id.login2);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianSignup.this,guardianDashboard.class));
            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(guardianSignup.this,guardianLogin.class));
            }
        });
    }
}