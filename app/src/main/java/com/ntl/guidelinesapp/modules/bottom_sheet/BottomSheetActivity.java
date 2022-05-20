package com.ntl.guidelinesapp.modules.bottom_sheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.ntl.guidelinesapp.R;

public class BottomSheetActivity extends AppCompatActivity {
    private RelativeLayout rlBottomSheet;
    private Button btPersistent, btModel, btCloseBottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        getSupportActionBar().setTitle("BottomSheetActivity");

        rlBottomSheet = findViewById(R.id.rl_bottom_sheet);
        btPersistent = findViewById(R.id.bt_persistent);
        btCloseBottomSheet = findViewById(R.id.bt_close_bottom_sheet);
        btModel = findViewById(R.id.bt_model);
        bottomSheetBehavior = BottomSheetBehavior.from(rlBottomSheet);

        btPersistent.setOnClickListener(v -> {
            if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        btCloseBottomSheet.setOnClickListener(v -> bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED));

        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        btPersistent.setText("Persistent CLOSE");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        btPersistent.setText("Persistent OPEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }
}