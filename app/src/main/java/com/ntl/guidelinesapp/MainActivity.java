/*

    Created by Linh Nguyen 18/5/2022

*/

package com.ntl.guidelinesapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ntl.guidelinesapp.modules.bottom_sheet.BottomSheetActivity;
import com.ntl.guidelinesapp.modules.broadcast_receiver.BroadcastReceiverActivity;
import com.ntl.guidelinesapp.modules.content_provider.ContentProviderActivity;
import com.ntl.guidelinesapp.modules.download_file.DownloadFileActivity;
import com.ntl.guidelinesapp.modules.filter_recyclerview.FilterRecyclerViewActivity;
import com.ntl.guidelinesapp.modules.firebase.FirebaseActivity;
import com.ntl.guidelinesapp.modules.image_slider.ImageSliderActivity;
import com.ntl.guidelinesapp.modules.keep_state_fragment.KeepStateFragmentActivity;
import com.ntl.guidelinesapp.modules.list.ListActivity;
import com.ntl.guidelinesapp.modules.mvp.MVPActivity;
import com.ntl.guidelinesapp.modules.mvvm.MVVMActivity;
import com.ntl.guidelinesapp.modules.notification.NotificationActivity;
import com.ntl.guidelinesapp.modules.retrofit.RetrofitActivity;
import com.ntl.guidelinesapp.modules.room_db.RoomDBActivity;
import com.ntl.guidelinesapp.modules.rx.RxActivity;
import com.ntl.guidelinesapp.modules.send_data_fragment_to_fragment.SendDataFragmentToFragmentActivity;
import com.ntl.guidelinesapp.modules.send_data_activity_to_fragment.SendDataActivityToFragmentActivity;
import com.ntl.guidelinesapp.modules.service.ServiceActivity;
import com.ntl.guidelinesapp.modules.sharepreference.SharePreferenceActivity;
import com.ntl.guidelinesapp.modules.template.TemplateActivity;
import com.ntl.guidelinesapp.modules.viewpager_fragment.ViewpagerFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ButtonAdapter.IClickButton {
    private static String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButton();

        /*FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        Log.e(TAG, token);
                    }
                });*/
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

        buttons.add(0, new ButtonModel(1, getResources().getString(R.string.screen_retrofit)));
        buttons.add(0, new ButtonModel(2, getResources().getString(R.string.screen_notification)));
        buttons.add(0, new ButtonModel(3, getResources().getString(R.string.screen_download_file)));
        buttons.add(0, new ButtonModel(4, getResources().getString(R.string.screen_broadcast_receiver)));
        buttons.add(0, new ButtonModel(5, getResources().getString(R.string.screen_data_activity_to_fragment)));
        buttons.add(0, new ButtonModel(6, getResources().getString(R.string.screen_data_fragment_to_fragment)));
        buttons.add(0, new ButtonModel(7, getResources().getString(R.string.share_preference)));
        buttons.add(0, new ButtonModel(8, getResources().getString(R.string.viewpager_fragment)));
        buttons.add(0, new ButtonModel(9, getResources().getString(R.string.keep_state_fragment)));
        buttons.add(0, new ButtonModel(10, getResources().getString(R.string.bottom_sheet)));
        buttons.add(0, new ButtonModel(11, getResources().getString(R.string.filter_rcv)));
        buttons.add(0, new ButtonModel(12, getResources().getString(R.string.mvp)));
        buttons.add(0, new ButtonModel(13, getResources().getString(R.string.mvvm)));
        buttons.add(0, new ButtonModel(14, getResources().getString(R.string.firebase)));
        buttons.add(0, new ButtonModel(15, getResources().getString(R.string.template)));
        buttons.add(0, new ButtonModel(16, getResources().getString(R.string.list)));
        buttons.add(0, new ButtonModel(17, getResources().getString(R.string.image_slider)));
        buttons.add(0, new ButtonModel(18, getResources().getString(R.string.room_db)));
        buttons.add(0, new ButtonModel(19, getResources().getString(R.string.service)));
        buttons.add(0, new ButtonModel(20, getResources().getString(R.string.content_provider)));
        buttons.add(0, new ButtonModel(21, getResources().getString(R.string.rx)));

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
            case 10:
                gotoScreen(BottomSheetActivity.class);
                break;
            case 11:
                gotoScreen(FilterRecyclerViewActivity.class);
                break;
            case 12:
                gotoScreen(MVPActivity.class);
                break;
            case 13:
                gotoScreen(MVVMActivity.class);
                break;
            case 14:
                gotoScreen(FirebaseActivity.class);
                break;
            case 15:
                gotoScreen(TemplateActivity.class);
                break;
            case 16:
                gotoScreen(ListActivity.class);
                break;
            case 17:
                gotoScreen(ImageSliderActivity.class);
                break;
            case 18:
                gotoScreen(RoomDBActivity.class);
                break;
            case 19:
                gotoScreen(ServiceActivity.class);
                break;
            case 20:
                gotoScreen(ContentProviderActivity.class);
                break;
            case 21:
                gotoScreen(RxActivity.class);
                break;
        }
    }

    private void gotoScreen(Class<?> cls) {
        Intent intent = new Intent(MainActivity.this, cls);
        startActivity(intent);
    }
}