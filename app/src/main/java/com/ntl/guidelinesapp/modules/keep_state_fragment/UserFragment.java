package com.ntl.guidelinesapp.modules.keep_state_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.general.adapter.GeneralAdapter;
import com.ntl.guidelinesapp.general.model.General;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment implements GeneralAdapter.IOnClickItemGeneral {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private KeepStateFragmentActivity mActivity;
    private View mView;
    private RecyclerView recyclerView;
    private GeneralAdapter adapter;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_user, container, false);

        mActivity = (KeepStateFragmentActivity) getActivity();

        recyclerView = mView.findViewById(R.id.rcv);
        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(manager);

        adapter = new GeneralAdapter(getList(), this);
        recyclerView.setAdapter(adapter);

        return mView;
    }

    private List<General> getList() {
        List<General> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new General("This name is: " + i));
        }
        return list;
    }

    @Override
    public void onClickItemGeneral(General general) {
        mActivity.gotoDetailFragment(general);
    }
}