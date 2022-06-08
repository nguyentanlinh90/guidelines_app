package com.ntl.guidelinesapp.modules.rx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.room_db.User;
import com.ntl.guidelinesapp.modules.room_db.UserAdapter;
import com.ntl.guidelinesapp.modules.room_db.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {
    private final String TAG = RxActivity.class.getSimpleName();

    private Disposable mDisposable;

    private UserAdapter adapter;
    private List<User> mList;
    private RecyclerView rcvUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        AppUtils.setTitleBar(this, RxActivity.class);

        getObservableUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserverUser());
        rcvUser = findViewById(R.id.rcv_user);
        mList = new ArrayList<>();
        adapter = new UserAdapter(null);
//        adapter.setData(mList);
        rcvUser.setAdapter(adapter);

    }

    private Observable<User> getObservableUser() {
        List<User> users = UserDatabase.getInstance(this).getUserDAO().getListUser();

        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<User> emitter) throws Throwable {
                if (users == null || users.isEmpty()) {
                    if (!emitter.isDisposed()) {
                        emitter.onError(new Exception());
                    }
                } else {
                    for (User user : users) {
                        if (!emitter.isDisposed()) {
                            emitter.onNext(user);
                        }
                    }
                    if (!emitter.isDisposed()) {
                        emitter.onComplete();
                    }
                }
            }
        });
    }

    private Observer<User> getObserverUser() {
        return new Observer<User>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e(TAG, "onSubscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull User user) {
                Log.e(TAG, "onNext " + user.toString());
                mList.add(0, user);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError " + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
                adapter.setData(mList);
            }
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}