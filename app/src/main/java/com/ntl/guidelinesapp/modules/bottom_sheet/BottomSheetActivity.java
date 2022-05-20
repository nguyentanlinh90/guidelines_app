package com.ntl.guidelinesapp.modules.bottom_sheet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.ntl.guidelinesapp.R;

public class BottomSheetActivity extends AppCompatActivity {
    private Button btPersistent, btModel;
    private BottomSheetDialog modelSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        getSupportActionBar().setTitle("BottomSheetActivity");

        btPersistent = findViewById(R.id.bt_persistent);
        btModel = findViewById(R.id.bt_model);

        btPersistent.setOnClickListener(v -> {
            openPersistentBottomSheetFragment();
        });

        btModel.setOnClickListener(v -> {
            openModelBottomSheetFragment();
        });
    }

    public void openModelSheet() {

        View view = getLayoutInflater().inflate(R.layout.model_bottom_sheet, null);

        modelSheetDialog = new BottomSheetDialog(this);
        modelSheetDialog.setContentView(view);

        // set disable click outside sheetDialog or click backPress
        modelSheetDialog.setCancelable(false);

        modelSheetDialog.show();

        //set open full screen when open
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //todo
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        closeModelSheet(view);
    }

    private void closeModelSheet(View view) {
        view.findViewById(R.id.bt_close_bottom_sheet).setOnClickListener(v -> {
            modelSheetDialog.dismiss();
        });
    }

    private void openPersistentBottomSheetFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, new PersistentBottomSheetFragment());
        transaction.commit();
    }

    private void openModelBottomSheetFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_content, new ModelBottomSheetFragment());
        transaction.commit();
    }
}