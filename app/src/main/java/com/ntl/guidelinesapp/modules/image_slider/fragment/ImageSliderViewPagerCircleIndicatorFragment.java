package com.ntl.guidelinesapp.modules.image_slider.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.image_slider.ImageSliderActivity;
import com.ntl.guidelinesapp.modules.image_slider.adapter.ImageSliderViewPagerCircleIndicatorAdapter;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageSliderViewPagerCircleIndicatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageSliderViewPagerCircleIndicatorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageSliderActivity mActivity;
    private ViewPager vpPhoto;
    private CircleIndicator circleIndicator;
    private List<Photo> mList;

    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (vpPhoto.getCurrentItem() == mList.size() - 1) {
                vpPhoto.setCurrentItem(0);
            } else {
                vpPhoto.setCurrentItem(vpPhoto.getCurrentItem() + 1);
            }
        }
    };

    public ImageSliderViewPagerCircleIndicatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageSliderViewPagerCircleIndicatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageSliderViewPagerCircleIndicatorFragment newInstance(String param1, String param2) {
        ImageSliderViewPagerCircleIndicatorFragment fragment = new ImageSliderViewPagerCircleIndicatorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ImageSliderActivity) getActivity();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_slider_view_pager_circle_indicator, container, false);

        vpPhoto = view.findViewById(R.id.vp_slider);
        circleIndicator = view.findViewById(R.id.circle_center);
        mList = getList();
        ImageSliderViewPagerCircleIndicatorAdapter adapter = new ImageSliderViewPagerCircleIndicatorAdapter(mList);
        vpPhoto.setAdapter(adapter);
        circleIndicator.setViewPager(vpPhoto);

        view.findViewById(R.id.bt_back).setOnClickListener(v -> {
            getParentFragmentManager().popBackStack();
            mActivity.updateView(false);
        });

        handler.postDelayed(runnable, 2000);

        vpPhoto.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private List<Photo> getList() {
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.img_600_300_1));
        list.add(new Photo(R.drawable.img_600_300_2));
        list.add(new Photo(R.drawable.img_600_300_3));
        list.add(new Photo(R.drawable.img_600_300_4));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 2000);
    }
}