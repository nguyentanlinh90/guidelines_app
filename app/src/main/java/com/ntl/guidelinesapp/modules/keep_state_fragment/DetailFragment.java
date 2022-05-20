package com.ntl.guidelinesapp.modules.keep_state_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.model.General;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static final String TAG = DetailFragment.class.getName();

    // TODO: Rename and change types of parameters
    private General general;

    private View mView;
    private TextView textView;
    private Button btBack, btNext;
    private KeepStateFragmentActivity activity;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(General general) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, general);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            general = (General) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (KeepStateFragmentActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_detail, container, false);
        textView = mView.findViewById(R.id.tv_text);
        btBack = mView.findViewById(R.id.bt_back);
        btNext = mView.findViewById(R.id.bt_next);

        if (general != null) {
            textView.setText(general.getName());
        }

        btBack.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        btNext.setOnClickListener(v -> activity.gotoNextFragment(general));
        return mView;
    }
}