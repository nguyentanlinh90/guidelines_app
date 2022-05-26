package com.ntl.guidelinesapp.modules.content_provider.view;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.modules.content_provider.Contact;
import com.ntl.guidelinesapp.modules.content_provider.PhoneNumber;
import com.ntl.guidelinesapp.modules.content_provider.SMS;
import com.ntl.guidelinesapp.modules.content_provider.adapter.ContactsAdapter;
import com.ntl.guidelinesapp.modules.content_provider.adapter.SMSAdapter;
import com.ntl.guidelinesapp.modules.content_provider.fragment.ContactFragment;
import com.ntl.guidelinesapp.modules.content_provider.fragment.SMSFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ContactsAndSMSActivity extends BaseActivity {
    private final String TAG = ContactsAndSMSActivity.class.getSimpleName();
    private List<Contact> mListContacts;
    private List<SMS> mListSMS;
    private ContentResolver mResolver;

    private ActivityResultLauncher<String> resultContactLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if (result) {
                        getContacts();
                    } else {
                        Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                    }
                }
            });

    private ActivityResultLauncher<String> resultSMSLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
            result -> {
                if (result) {
                    getSMS();
                } else {
                    Log.e(TAG, "onActivityResult: PERMISSION DENIED");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_sms);
        AppUtils.setTitleBar(this, ContactsAndSMSActivity.class);

        mResolver = getContentResolver();

        findViewById(R.id.bt_get_contact).setOnClickListener(v -> clickRequestPermissionContact());
        findViewById(R.id.bt_get_sms).setOnClickListener(v -> clickRequestPermissionSMS());
    }

    private void clickRequestPermissionSMS() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getSMS();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            getSMS();
        } else {
            resultSMSLauncher.launch(Manifest.permission.READ_SMS);
        }
    }

    private void getSMS() {
        mListSMS = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yy HH:mm:ss");
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor cursor = mResolver.query(uri, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String dateTimestamp = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                SMS sms = new SMS(address, simpleDateFormat.format(Long.parseLong(dateTimestamp)), body);
                mListSMS.add(sms);
                cursor.moveToNext();
            }
            cursor.close();
        }
        Log.e(TAG, "sms size " + mListSMS.size());

        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(mListSMS).getAsJsonArray();
        replaceFragment(R.id.fl_content, SMSFragment.newInstance(jsonArray.toString()));
    }

    private void clickRequestPermissionContact() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            getContacts();
            return;
        }
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            getContacts();
        } else {
            resultContactLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }

    private void getContacts() {
        mListContacts = new ArrayList<>();
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };
        String selection = "name LIKE ?";
        String[] selectArgs = {"%h%"};
        String sortOrder = "id";
        Cursor cursor = mResolver.query(uri, null, null, null, null);

        //PhoneNumber
        Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projectionPhone = {ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selectionPhone = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";


        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String displayName = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));

                String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                Cursor cursorPhone = mResolver.query(uriPhone,
                        projectionPhone, selectionPhone, new String[]{String.valueOf(id)}, null);

                List<PhoneNumber> listPhones = new ArrayList<>();
                cursorPhone.moveToFirst();
                while (!cursorPhone.isAfterLast()) {
                    String phoneNumber = cursorPhone.getString(cursorPhone.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    listPhones.add(new PhoneNumber(phoneNumber));
                    cursorPhone.moveToNext();
                }
                cursorPhone.close();

                Contact contact = new Contact(id, displayName, listPhones);
                mListContacts.add(contact);
                cursor.moveToNext();
            }
            cursor.close();
        }

        Log.e(TAG, "contact size " + mListContacts.size());
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(mListContacts).getAsJsonArray();
        replaceFragment(R.id.fl_content, ContactFragment.newInstance(jsonArray.toString()));
    }
}