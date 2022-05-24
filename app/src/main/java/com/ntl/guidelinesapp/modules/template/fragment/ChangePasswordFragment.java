package com.ntl.guidelinesapp.modules.template.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseFragment;
import com.ntl.guidelinesapp.core.IReAuthenticateListener;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.NavigationDrawerToolbarFragmentActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangePasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangePasswordFragment extends BaseFragment {
    private final String TAG = ChangePasswordFragment.class.getSimpleName();

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NavigationDrawerToolbarFragmentActivity mActivity;

    private View mView;
    private FirebaseUser mUser;
    private EditText edtNewPassword;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangePasswordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangePasswordFragment newInstance(String param1, String param2) {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (NavigationDrawerToolbarFragmentActivity) getActivity();
        mUser = getUser();
        mView = inflater.inflate(R.layout.fragment_change_password, container, false);

        edtNewPassword = mView.findViewById(R.id.edt_new_password);
        mView.findViewById(R.id.bt_update_password).setOnClickListener(v -> {
            doUpdatePassword();

        });
        return mView;
    }

    private void doUpdatePassword() {
        mActivity.showProgress();
        mUser.updatePassword(edtNewPassword.getText().toString().trim())
                .addOnCompleteListener(task -> {
                    mActivity.dismissProgress();
                    if (task.isSuccessful()) {
                        Log.e(TAG, "User password updated.");
                        Toast.makeText(mActivity, "User password updated.", Toast.LENGTH_LONG).show();
                    } else {
                        reAuthenticateUser(mActivity, new IReAuthenticateListener() {
                            @Override
                            public void onComplete() {
                                doUpdatePassword();
                            }
                        });
                    }
                });
    }
}