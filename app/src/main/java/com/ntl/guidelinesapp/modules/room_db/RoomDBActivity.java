package com.ntl.guidelinesapp.modules.room_db;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.room_db.database.UserDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomDBActivity extends AppCompatActivity {
    private String TAG = RoomDBActivity.class.getSimpleName();
    private EditText edtUsername, edtAddress, edtYear, edtPhone;
    private Button btInsert;
    private TextView tvDeleteAll;
    private RecyclerView rcvUser;
    private EditText edtSearch;

    private UserAdapter adapter;

    private List<User> mList;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                Log.e(TAG, "onActivityResult");
                if (result.getResultCode() == RESULT_OK) {
                    Log.e(TAG, "load data after update");
                    loadData();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_dbactivity);
        AppUtils.setTitleBar(this, RoomDBActivity.class);

        edtUsername = findViewById(R.id.edt_user_name);
        edtAddress = findViewById(R.id.etd_address);
        edtYear = findViewById(R.id.edt_year);
        edtPhone = findViewById(R.id.edt_phone);
        btInsert = findViewById(R.id.bt_insert);
        tvDeleteAll = findViewById(R.id.tv_delete_all);
        rcvUser = findViewById(R.id.rcv_user);
        edtSearch = findViewById(R.id.edt_search);

        adapter = new UserAdapter(new UserAdapter.IClickUpdateItem() {
            @Override
            public void updateItem(User user) {
                updateUser(user);
            }

            @Override
            public void deleteItem(User user) {
                deleteUser(user);
            }
        });
        mList = new ArrayList<>();
        adapter.setData(mList);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(manager);
        rcvUser.setAdapter(adapter);

        btInsert.setOnClickListener(v -> insertUser());

        tvDeleteAll.setOnClickListener(v -> deleteAllUser());

        edtSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                handleSearchUser();
            }
            return false;
        });
        loadData();
    }

    private void insertUser() {
        String username = edtUsername.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String year = edtYear.getText().toString().trim();
        String strPhone = edtYear.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(address) || TextUtils.isEmpty(year)) {
            return;
        }
        User user = new User(username, address, year, strPhone);

        if (isUserExits(user)) {
            Toast.makeText(this, "user is exits", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDatabase.getInstance(this).getUserDAO().insertUser(user);
        Toast.makeText(this, "add user success", Toast.LENGTH_SHORT).show();

        hideSoftKeyBoard();

        loadData();
    }

    private void hideSoftKeyBoard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        mList = UserDatabase.getInstance(this).getUserDAO().getListUser();
        Collections.reverse(mList);
        adapter.setData(mList);
    }

    private boolean isUserExits(User user) {
        List<User> list = UserDatabase.getInstance(this).getUserDAO().checkUser(user.getUsername());
        return list != null & !list.isEmpty();
    }

    private void updateUser(User user) {
        Intent intent = new Intent(RoomDBActivity.this, RoomUpdateActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_object", user);
        intent.putExtras(bundle);
        activityResultLauncher.launch(intent);

    }

    private void deleteUser(User user) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete user")
                .setMessage("Are you sure")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDatabase.getInstance(getApplicationContext()).getUserDAO().deleteUser(user);
                        Toast.makeText(RoomDBActivity.this, "delete user success", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void deleteAllUser() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm delete all user")
                .setMessage("Are you sure")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UserDatabase.getInstance(getApplicationContext()).getUserDAO().dellAllUser();
                        Toast.makeText(RoomDBActivity.this, "delete all user success", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    private void handleSearchUser() {
        String textSearch = edtSearch.getText().toString().trim();
        mList = new ArrayList<>();
        mList = UserDatabase.getInstance(this).getUserDAO().searchUsername(textSearch);
        adapter.setData(mList);
        hideSoftKeyBoard();

    }
}