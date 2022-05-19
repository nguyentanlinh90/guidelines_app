package com.ntl.guidelinesapp.modules.send_data_to_fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ntl.guidelinesapp.MainActivity;
import com.ntl.guidelinesapp.R;

/**
 * A simple {@link androidx.fragment.app.Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends androidx.fragment.app.Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private User mParam1;
    private String mParam2;

    private EditText edtEmail;
    private Button btUpdate;

    private View mView;
    private SendDataToFragmentActivity mainActivity;

    private ISendDataToActivity iSendDataToActivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_1.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(User param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (User) getArguments().getSerializable(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (SendDataToFragmentActivity) getActivity();
        initUI();
        return mView;
    }

    private void initUI() {
        edtEmail = mView.findViewById(R.id.edt_email);
        btUpdate = mView.findViewById(R.id.bt_update_to_activity);

        edtEmail.setText(mParam2);

        /*  todo OR
            edtEmail.setText(mParam1.getEmail());
        */
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToActivity();
            }
        });
    }

    private void sendDataToActivity() {
        String strEmail = edtEmail.getText().toString().trim();
        /*
        case 1:
        mainActivity.getEdtEmail().setText(strEmail);*/

        //case 2
        User user = new User(strEmail);
        iSendDataToActivity = mainActivity;
        iSendDataToActivity.sendData(user);

    }
}