package com.ntl.guidelinesapp.modules.image_slider.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.model.Photo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhotoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoFragment extends Fragment {
    private static final String ARG_PHOTO = "param_photo";

    private Photo mPhoto;

    private View mView;

    public PhotoFragment() {
        // Required empty public constructor
    }

    public static PhotoFragment newInstance(Photo photo) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhoto = (Photo) getArguments().getSerializable(ARG_PHOTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_photo, container, false);
        if (mPhoto != null) {
            ImageView imageView = mView.findViewById(R.id.iv_photo);
            imageView.setImageResource(mPhoto.getResource());
        }
        return mView;
    }
}