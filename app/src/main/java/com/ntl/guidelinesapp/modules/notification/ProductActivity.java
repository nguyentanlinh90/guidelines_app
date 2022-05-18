package com.ntl.guidelinesapp.modules.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ntl.guidelinesapp.R;

public class ProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        getSupportActionBar().setTitle("ProductActivity");

        findViewById(R.id.bt_go_to_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
}