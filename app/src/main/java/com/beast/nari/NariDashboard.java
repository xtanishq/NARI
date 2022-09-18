//package com.beast.nari;
//
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.databinding.DataBindingUtil;
//
//        import android.content.Intent;
//        import android.os.Bundle;
//        import android.view.View;
//
//        import com.beast.nari.databinding.ActivityNariDashboardBinding;
//
//public class NariDashboard extends AppCompatActivity {
//
//    private ActivityNariDashboardBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_nari_dashboard);
//
//        binding.location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(NariDashboard.this, MapsActivity.class));
//            }
//        });
//
//    }
//}

package com.beast.nari;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.beast.nari.databinding.ActivityNariDashboardBinding;

public class NariDashboard extends AppCompatActivity {
    private ActivityNariDashboardBinding binding;
    ImageView msk,helpline;
    ImageView fakecall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nari_dashboard);
        msk = findViewById(R.id.msk);
        fakecall = findViewById(R.id.fakecall);
        helpline = findViewById(R.id.helpLine);

        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NariDashboard.this, MapsActivity.class));
            }
        });

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
        fakecall = findViewById(R.id.fakecall);

        fakecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NariDashboard.this, ring.class));

            }
        });
    }
}