package com.ntl.guidelinesapp.modules.firebase.realtime_db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.core.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealTimeDBActivity extends BaseActivity {
    private final String TAG = RealTimeDBActivity.class.getSimpleName();

    private String PATH_STRING = "message";
    private String PATH_OBJECT = "users";
    private String PATH_MAP = "map";
    private String PATH_LIST_OBJECT = "list_object";

    private EditText edtInput;
    private Button btPushData, btGetData, btUpdateData, btDeleteData;
    private TextView tvData;

    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private User mUser;

    private List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_dbactivity);
        AppUtils.setTitleBar(this, RealTimeDBActivity.class);

        edtInput = findViewById(R.id.edt_input);
        btPushData = findViewById(R.id.bt_push_data);
        btGetData = findViewById(R.id.bt_get_data);
        btUpdateData = findViewById(R.id.bt_update_data);
        btDeleteData = findViewById(R.id.bt_delete_data);
        tvData = findViewById(R.id.tv_data);

        mDatabase = FirebaseDatabase.getInstance();

        mUser = new User("1", "lin", "abc@gmail.com", new Job("1", "Android"));

        btPushData.setOnClickListener(v -> {
//            pushDataObject();
//            pushMapData();
            pushListObject();
        });

        btGetData.setOnClickListener(v -> {
//            getDataObject();
//            getMapData();
            getListObject();
        });

        btUpdateData.setOnClickListener(v -> {
//            updateDataObject();
//            updateDataMap();
            updateListObject();
        });

        btDeleteData.setOnClickListener(v -> {
//            deleteDataObject();
//            deleteDataMap();
            deleteItemOnListObject();
        });
    }

    private void pushDataString() {
        myRef = mDatabase.getReference(PATH_STRING);

        myRef.setValue(edtInput.getText().toString().trim(), new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(RealTimeDBActivity.this, "Push data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getDataString() {
        myRef = mDatabase.getReference(PATH_STRING);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                tvData.setText(value);

                Log.e(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void updateDataString() {
        // TODO: 23/05/2022 the same function getDataString()
    }

    private void deleteDataString() {
        myRef = mDatabase.getReference(PATH_STRING);
        myRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Log.e(TAG, "Remove data String success");
            }
        });
    }

    private void pushDataLong() {
        // TODO: 23/05/2022
    }

    private void pushDataDouble() {
        // TODO: 23/05/2022
    }

    private void pushDataBoolean() {

    }

    private void pushDataObject() {
        mUser.setAddress("Ho Chi Minh");
        myRef = mDatabase.getReference(PATH_OBJECT);
        myRef.setValue(mUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "Error getting data", task.getException());
                } else {
                    Toast.makeText(RealTimeDBActivity.this, "Push object success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //todo SHOULD USE case 3 follow document https://firebase.google.com/docs/database/android/read-and-write
    private void updateDataObject() {
        // TODO: case 1 override - the same pushDataObject()
        // TODO: case 2 - update path or add child
        /*
            //update path
            myRef = mDatabase.getReference(PATH_OBJECT + "/username");
            myRef.setValue("lin2").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "Error update data case 2 ", task.getException());
                    }
                    else {
                        Toast.makeText(RealTimeDBActivity.this, "Update object case 2 success", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //add child
            myRef = mDatabase.getReference(PATH_OBJECT);
            myRef.child("job").child("name").setValue("iOS").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "Error update data case 3 ", task.getException());
                    }
                    else {
                        Toast.makeText(RealTimeDBActivity.this, "Update object case 2 success", Toast.LENGTH_SHORT).show();
                    }
                }
            });
         */
        // TODO: case 3 - use Map Object
        /*myRef = mDatabase.getReference(PATH_OBJECT);
        User user = new User("2", "linnguyen", "qaz@gmail.com", new Job("2", "C#"));
        myRef.updateChildren(user.toMap()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "Error update data case 3 ", task.getException());
                }
                else {
                    Toast.makeText(RealTimeDBActivity.this, "Update object case 3 success", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        // TODO: if just update some params, ex: username
        User user1 = new User("linz");
        myRef = mDatabase.getReference(PATH_OBJECT);
        myRef.updateChildren(user1.toMapUpdateUserName()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "Error update data case 3 ", task.getException());
                } else {
                    Toast.makeText(RealTimeDBActivity.this, "Update object case 3 success", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getDataObject() {
        myRef = mDatabase.getReference(PATH_OBJECT);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    tvData.setText(user.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    private void deleteDataObject() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure delete?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myRef = mDatabase.getReference(PATH_OBJECT);
                        // todo: if just delete param
                        // myRef = mDatabase.getReference(PATH_OBJECT + "/job");
                        myRef.removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(RealTimeDBActivity.this, "Delete object success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void pushMapData() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("key1", true);
        map.put("key2", false);
        map.put("key3", true);
        map.put("key4", false);
        map.put("key5", true);

        myRef = mDatabase.getReference(PATH_MAP);

        myRef.setValue(map, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(RealTimeDBActivity.this, "Push map data success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMapData() {
        myRef = mDatabase.getReference(PATH_MAP);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map<String, Boolean> mapResult = new HashMap<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    mapResult.put(dataSnapshot.getKey(), dataSnapshot.getValue(Boolean.class));
                }
                tvData.setText(mapResult.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "loadPost:onCancelled", error.toException());
            }
        });
    }

    private void updateDataMap() {
        // TODO: case 1 override - the same pushDataObject()
        // TODO: case 2 - update path or add child
/*

            //update path
            myRef = mDatabase.getReference(PATH_MAP + "/key1");
            myRef.setValue("lin2").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "Error update data case 2 ", task.getException());
                    }
                    else {
                        Toast.makeText(RealTimeDBActivity.this, "Update map case 2 success", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //add child
            myRef = mDatabase.getReference(PATH_MAP);
            myRef.child("key1").setValue(false).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Log.e(TAG, "Error update data case 2 ", task.getException());
                    }
                    else {
                        Toast.makeText(RealTimeDBActivity.this, "Update map case 2 success", Toast.LENGTH_SHORT).show();
                    }
                }
            });
*/

        // TODO: case 3 - use Map Object
        myRef = mDatabase.getReference(PATH_MAP);
        Map<String, Object> mapUpdate = new HashMap<>();
        mapUpdate.put("key2", true);
        mapUpdate.put("key4", false);
        myRef.updateChildren(mapUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "Error update data case 3 ", task.getException());
                } else {
                    Toast.makeText(RealTimeDBActivity.this, "Update map case 3 success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteDataMap() {
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure delete?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        myRef = mDatabase.getReference(PATH_MAP);
                        // todo: if just delete key
                        myRef = mDatabase.getReference(PATH_MAP + "/key2");
                        myRef.removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(RealTimeDBActivity.this, "Delete map success", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void pushListObject() {
        // TODO: add object to list
        User user = new User("1", "abc");
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        /*String pathObject = user.getId();
        myRef.child(pathObject).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RealTimeDBActivity.this, "Push object to list success", Toast.LENGTH_SHORT).show();
            }
        });*/

        // TODO: add all object to list
        List<User> listUser = new ArrayList<>();
        listUser.add(new User("1", "A"));
        listUser.add(new User("2", "B"));
        listUser.add(new User("3", "B"));
        myRef.setValue(listUser).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(RealTimeDBActivity.this, "Push all object to list success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getListObject() {
        mUserList = new ArrayList<>();
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);

        // TODO: case 1 - will reload data every data change
        /*myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (mUserList != null) {
                    //avoid duplicate date when have change
                    mUserList.clear();
                }

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    mUserList.add(user);
                }
                tvData.setText(String.valueOf(mUserList.size()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "loadPost:onCancelled", error.toException());
            }
        });*/

        // TODO: case 2 - will listen CHILD data every data change
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    mUserList.add(user);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // TODO: after run updateListObject()
                User user = snapshot.getValue(User.class);
                if (user == null || mUserList == null || mUserList.size() == 0) {
                    return;
                }
                for (int i = 0; i < mUserList.size(); i++) {
                    if (user.getId().equals(mUserList.get(i).getId())) {
                        mUserList.set(i, user);
                        break;
                    }
                }
                //TODO adapter.notifyDataSetChange();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                // TODO: after run deleteItemOnListObject()
                User user = snapshot.getValue(User.class);
                if (user == null || mUserList == null || mUserList.size() == 0) {
                    return;
                }
                for (int i = 0; i < mUserList.size(); i++) {
                    if (user.getId().equals(mUserList.get(i).getId())) {
                        mUserList.remove(mUserList.get(i));
                        break;
                    }
                }
                //TODO adapter.notifyDataSetChange();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // TODO: 23/05/2022
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "Error get list", error.toException());
            }
        });
    }

    private void updateListObject() {
        // TODO: want to update username of User with id = 1
        User userUpdate = new User("1", "lin update");
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        myRef.child(userUpdate.getId()).updateChildren(userUpdate.toMapUpdateUserName()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (!task.isSuccessful()) {
                    Log.e(TAG, "Error update object on list object ", task.getException());
                } else {
                    Toast.makeText(RealTimeDBActivity.this, "Update object on list success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteItemOnListObject() {
        // TODO: want to delete item User with id = 1
        User userUpdate = new User("1", "lin update");
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        myRef.child(userUpdate.getId()).removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(RealTimeDBActivity.this, "Delete item on list object success", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sortItemOnListObject() {
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        //want to sort by id increase, add query
        Query query = myRef.orderByChild("id");
        /*
            TODO: orderByKey()
            myRef.orderByKey();
        */
        /*
            TODO: orderByValue()
            Map<String, String> map = new HashMap<>();
            map.put("1", "aja");
            map.put("3", "kfk");
            map.put("2", "hsg");
            //if data with map as above, use orderByValue
            //Note: distinguish between uppercase and lowercase letters
            myRef.orderByValue();
        */
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    mUserList.add(user);
                    /*
                        todo if sort DESC
                        mUserList.add(0, user);
                    */
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void filterItemOnListObject() {
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        //TODO want to filter 2 item first
        Query query = myRef.limitToFirst(2);

        //TODO want to filter 2 item last : myRef.limitToLast(2);
        //TODO want to filter items has rate >= 4 : myRef.orderByChild("rate").startAt(4);
        //TODO want to filter items has rate > 4 : myRef.orderByChild("rate").startAfterAt(4);
        //TODO want to filter items has rate <= 3 : myRef.orderByChild("rate").endAt(3);
        //TODO want to filter items has rate < 3 : myRef.orderByChild("rate").endAfterAt(3);
        //TODO want to filter items has rate = 5 : myRef.orderByChild("rate").equalTo(5);

        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    mUserList.add(user);
                    /*
                        todo if sort DESC
                        mUserList.add(0, user);
                    */
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void filterItemOnListObjectByKey(String key) {
        myRef = mDatabase.getReference(PATH_LIST_OBJECT);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null) {
                    if (user.getUsername().equals(key)) {
                        mUserList.add(user);
                    }
                    /*
                        todo if sort DESC
                        mUserList.add(0, user);
                    */
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}