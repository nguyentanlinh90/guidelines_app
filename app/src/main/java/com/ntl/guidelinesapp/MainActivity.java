/*

    Created by Linh Nguyen 18/5/2022

*/

package com.ntl.guidelinesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.modules.broadcast_receiver.BroadcastReceiverActivity;
import com.ntl.guidelinesapp.modules.download_file.DownloadFileActivity;
import com.ntl.guidelinesapp.modules.keep_state_fragment.KeepStateFragmentActivity;
import com.ntl.guidelinesapp.modules.notification.NotificationActivity;
import com.ntl.guidelinesapp.modules.retrofit.RetrofitActivity;
import com.ntl.guidelinesapp.modules.send_data_fragment_to_fragment.SendDataFragmentToFragmentActivity;
import com.ntl.guidelinesapp.modules.send_data_to_fragment.SendDataActivityToFragmentActivity;
import com.ntl.guidelinesapp.modules.sharepreference.SharePreferenceActivity;
import com.ntl.guidelinesapp.modules.viewpager_fragment.ViewpagerFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonAdapter.IClickButton {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();

    }

    private void initButton() {
        RecyclerView rcvButton = findViewById(R.id.rcv_button);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvButton.setLayoutManager(manager);

        ButtonAdapter adapter = new ButtonAdapter();
        adapter.setData(getListButton(), this);
        rcvButton.setAdapter(adapter);
    }

    private List<ButtonModel> getListButton() {
        List<ButtonModel> buttons = new ArrayList<>();

        buttons.add(new ButtonModel(1, getResources().getString(R.string.screen_retrofit)));
        buttons.add(new ButtonModel(2, getResources().getString(R.string.screen_notification)));
        buttons.add(new ButtonModel(3, getResources().getString(R.string.screen_download_file)));
        buttons.add(new ButtonModel(4, getResources().getString(R.string.screen_broadcast_receiver)));
        buttons.add(new ButtonModel(5, getResources().getString(R.string.screen_data_activity_to_fragment)));
        buttons.add(new ButtonModel(6, getResources().getString(R.string.screen_data_fragment_to_fragment)));
        buttons.add(new ButtonModel(7, getResources().getString(R.string.share_preference)));
        buttons.add(new ButtonModel(8, getResources().getString(R.string.viewpager_fragment)));
        buttons.add(new ButtonModel(9, getResources().getString(R.string.keep_state_fragment)));

        return buttons;
    }

    @Override
    public void onClickButton(ButtonModel buttonModel) {
        switch (buttonModel.getId()) {
            case 1:
                gotoScreen(RetrofitActivity.class);
                break;
            case 2:
                gotoScreen(NotificationActivity.class);
                break;
            case 3:
                gotoScreen(DownloadFileActivity.class);
                break;
            case 4:
                gotoScreen(BroadcastReceiverActivity.class);
                break;
            case 5:
                gotoScreen(SendDataActivityToFragmentActivity.class);
                break;
            case 6:
                gotoScreen(SendDataFragmentToFragmentActivity.class);
                break;
            case 7:
                gotoScreen(SharePreferenceActivity.class);
                break;
            case 8:
                gotoScreen(ViewpagerFragmentActivity.class);
                break;
            case 9:
                gotoScreen(KeepStateFragmentActivity.class);
                break;
        }
    }

    private void gotoScreen(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}