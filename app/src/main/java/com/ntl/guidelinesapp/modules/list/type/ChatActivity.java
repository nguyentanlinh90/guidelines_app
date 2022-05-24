package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;
import com.ntl.guidelinesapp.generated.callback.OnClickListener;
import com.ntl.guidelinesapp.modules.list.adapter.UserLinearAdapter;
import com.ntl.guidelinesapp.modules.list.model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends BaseActivity {
    private EditText edtMessage;
    private Button btSend;
    private RecyclerView rcvMessage;
    private UserLinearAdapter adapter;
    private List<User> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        AppUtils.setTitleBar(this, ChatActivity.class);

        edtMessage = findViewById(R.id.edt_message);
        btSend = findViewById(R.id.bt_send);
        rcvMessage = findViewById(R.id.rcv_messages);
        rcvMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new UserLinearAdapter();
        rcvMessage.setAdapter(adapter);

        mList = new ArrayList<>();
        btSend.setOnClickListener(v -> handleSend());

        edtMessage.setOnClickListener(v -> checkKeyBoard());
    }

    private void handleSend() {
        String strMessage = edtMessage.getText().toString().trim();
        if (TextUtils.isEmpty(strMessage)) {
            return;
        }
        mList.add(new User(R.drawable.dog_image, strMessage));
        adapter.setData(mList);
        rcvMessage.scrollToPosition(mList.size() - 1);
        edtMessage.setText("");
    }

    //will scroll to last list chat when open keyboard
    private void checkKeyBoard() {
        final View activityRootView = findViewById(R.id.rl_root);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int heightDiff = activityRootView.getRootView().getHeight() - r.height();
                if (heightDiff > 0.25 * activityRootView.getRootView().getHeight()) {
                    //keyboard display
                    if (mList.size() > 0) {
                        rcvMessage.scrollToPosition(mList.size() - 1);

                        //if don't remove, will can't scroll rcv
                        activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }
}