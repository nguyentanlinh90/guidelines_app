package com.ntl.guidelinesapp.modules.bottom_sheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.General;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private static final String KEY_OBJECT = "key_object";

    private General general;
    private BottomSheetActivity activity;

    public MyBottomSheetDialogFragment getInstance(General general) {
        MyBottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_OBJECT, general);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundleReceiver = getArguments();
        if (bundleReceiver != null) {
            general = (General) bundleReceiver.get(KEY_OBJECT);
        }
        activity = (BottomSheetActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_bottom_sheet, null);
        bottomSheetDialog.setContentView(view);

        initUI(view);

        //Show full screen when open
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                // TODO: 20/05/2022  
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        
        return bottomSheetDialog;
    }

    private void initUI(View view) {
        if (general != null) {
            TextView tvGeneral = view.findViewById(R.id.tv_general);
            tvGeneral.setText(general.getName());

            view.findViewById(R.id.bt_close_bottom_sheet).setOnClickListener(v -> {
                activity.dismissBottomSheetFragment();
            });
        }
    }
}
