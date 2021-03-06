package com.ntl.guidelinesapp.modules.template.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseFragment;
import com.ntl.guidelinesapp.core.IReAuthenticateListener;
import com.ntl.guidelinesapp.modules.template.navigationdrawer_toolbar_fragment.NavigationDrawerToolbarFragmentActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyAccountFragment extends BaseFragment {
    private final String TAG = MyAccountFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_CODE_READ_STORAGE = 11;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NavigationDrawerToolbarFragmentActivity mActivity;

    private View mView;
    private CircleImageView ivAvatar;
    private EditText edtName, edtEmail;
    private Button btUpdate, btUpdateEmail;
    private FirebaseUser mUser;
    private Uri uriAvatar = null;

    public MyAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyAccountFragment newInstance(String param1, String param2) {
        MyAccountFragment fragment = new MyAccountFragment();
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
        mUser = getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (NavigationDrawerToolbarFragmentActivity) getActivity();

        mView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        initUI();
        initEvent();

        return mView;
    }

    private void initUI() {
        ivAvatar = mView.findViewById(R.id.profile_image);
        edtName = mView.findViewById(R.id.edt_name);
        edtEmail = mView.findViewById(R.id.edt_email);
        btUpdate = mView.findViewById(R.id.bt_update);
        btUpdateEmail = mView.findViewById(R.id.bt_update_email);

        if (mUser != null) {
            Glide.with(this).load(mUser.getPhotoUrl()).error(R.drawable.ic_baseline_person_24).into(ivAvatar);
            if (mUser.getDisplayName() != null) {
                edtName.setText(mUser.getDisplayName());
                edtEmail.setText(mUser.getEmail());
            }
        }
    }

    private void initEvent() {
        ivAvatar.setOnClickListener(v -> {
            clickRequestPermission();
        });

        btUpdate.setOnClickListener(v -> {
            updateProfile();
        });

        btUpdateEmail.setOnClickListener(v -> {
            doUpdateEmail();
        });

    }

    private void clickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            openGallery();
            return;
        }
        if (mActivity != null) {
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                mActivity.mPermissionReadStorageResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    public void setBitmap(Bitmap bitmap) {
        ivAvatar.setImageBitmap(bitmap);
    }

    private void updateProfile() {
        mActivity.showProgress();
        if (uriAvatar == null) {
            uriAvatar = mUser.getPhotoUrl();
        }
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(edtName.getText().toString().trim())
                .setPhotoUri(uriAvatar)
                .build();

        mUser.updateProfile(profileUpdates)
                .addOnCompleteListener(task -> {
                    mActivity.dismissProgress();
                    if (task.isSuccessful()) {
                        Log.e(TAG, "User profile updated.");
                        Toast.makeText(mActivity, "User profile updated.", Toast.LENGTH_LONG).show();
                        mActivity.updateUI();
                    } else {
                        Log.e(TAG, "User profile update fail: ");
                    }
                });
    }

    public void setUriAvatar(Uri uriAvatar) {
        this.uriAvatar = uriAvatar;
    }

    private void doUpdateEmail() {
        mActivity.showProgress();
        mUser.updateEmail(edtEmail.getText().toString().trim())
                .addOnCompleteListener(task -> {
                    mActivity.dismissProgress();
                    if (task.isSuccessful()) {
                        Log.e(TAG, "User email address updated.");
                        Toast.makeText(mActivity, "User email address updated.", Toast.LENGTH_LONG).show();
                        mActivity.updateUI();
                    } else {
                        Log.e(TAG, "User profile update fail: ");
                        reAuthenticateUser(mActivity, new IReAuthenticateListener() {
                            @Override
                            public void onComplete() {
                                doUpdateEmail();
                            }
                        });
                    }
                });
    }

}