package com.ntl.guidelinesapp.modules.keep_state_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.adapter.GeneralAdapter;
import com.ntl.guidelinesapp.general.model.General;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextFragment extends Fragment {
    public static final String TAG = NextFragment.class.getName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private General general;
    private View mView;
    private TextView textView;
    private Button btBack;
    public NextFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static NextFragment newInstance(General param1) {
        NextFragment fragment = new NextFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
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

        mView = inflater.inflate(R.layout.fragment_next, container, false);
        textView = mView.findViewById(R.id.tv_text);
        btBack = mView.findViewById(R.id.bt_back);

        if (general != null) {
            textView.setText(general.getName());
        }

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();
            }
        });

        return mView;
    }
}