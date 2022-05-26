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
import com.ntl.guidelinesapp.modules.content_provider.Contact;
import com.ntl.guidelinesapp.modules.content_provider.adapter.ContactsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    private final String TAG = ContactFragment.class.getSimpleName();
    private static final String ARG_PARAM_LIST_CONTACT = "ARG_PARAM_LIST_CONTACT";


    // TODO: Rename and change types of parameters
    private List<Contact> mListContacts;
    private ContactsAdapter contactsAdapter;

    public ContactFragment() {
        // Required empty public constructor
    }

    public static ContactFragment newInstance(String contactList) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_LIST_CONTACT, contactList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListContacts = new ArrayList<>();
        if (getArguments() != null) {
            String contactList = getArguments().getString(ARG_PARAM_LIST_CONTACT);
            mListContacts = parseToListSMS(contactList);
            Log.e(TAG, mListContacts.size() + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcv_contacts);
        contactsAdapter = new ContactsAdapter(mListContacts);
        recyclerView.setAdapter(contactsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        return view;
    }

    private List<Contact> parseToListSMS(String srtListContact) {
        List<Contact> list = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(srtListContact);
            JSONObject jsonObject;
            Contact contact;
            Gson gson = new Gson();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                contact = gson.fromJson(jsonObject.toString(), Contact.class);
                list.add(contact);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}