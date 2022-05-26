package com.ntl.guidelinesapp.modules.content_provider.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.content_provider.SMS;
import com.ntl.guidelinesapp.modules.content_provider.adapter.SMSAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SMSFragment extends Fragment {
    private final String TAG = SMSFragment.class.getSimpleName();
    private static final String ARG_PARAM_LIST_SMS = "ARG_PARAM_LIST_SMS";

    private List<SMS> mList;
    private SMSAdapter adapter;

    public SMSFragment() {
        // Required empty public constructor
    }

    public static SMSFragment newInstance(String smsList) {
        SMSFragment fragment = new SMSFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_LIST_SMS, smsList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mList = new ArrayList<>();
        if (getArguments() != null) {
            String strListSMS = getArguments().getString(ARG_PARAM_LIST_SMS);
            mList = parseToListSMS(strListSMS);
            Log.e(TAG, mList.size() + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_m_s, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcv_sms);
        adapter = new SMSAdapter(mList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    private List<SMS> parseToListSMS(String srtListSMS) {
        List<SMS> list = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(srtListSMS);
            JSONObject jsonObject;
            SMS sms;
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                sms = gson.fromJson(jsonObject.toString(), SMS.class);
                list.add(sms);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}