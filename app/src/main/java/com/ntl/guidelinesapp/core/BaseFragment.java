package com.ntl.guidelinesapp.core;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BaseFragment extends Fragment {

    public FirebaseUser getUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }
}
