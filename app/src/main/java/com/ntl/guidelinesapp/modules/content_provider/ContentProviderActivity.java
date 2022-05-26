package com.ntl.guidelinesapp.modules.content_provider;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.content_provider.view.ContactsAndSMSActivity;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        AppUtils.setTitleBar(this, ContentProviderActivity.class);

        findViewById(R.id.bt_get_contacts).setOnClickListener(v -> AppUtils.gotoScreen(ContentProviderActivity.this, ContactsAndSMSActivity.class));
    }
}