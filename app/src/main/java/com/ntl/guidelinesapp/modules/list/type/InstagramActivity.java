package com.ntl.guidelinesapp.modules.list.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ntl.guidelinesapp.AppUtils;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.list.adapter.InstagramAdapter;
import com.ntl.guidelinesapp.modules.list.model.ListPhoto;
import com.ntl.guidelinesapp.modules.list.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class InstagramActivity extends AppCompatActivity {
    private RecyclerView rcvPhotos;
    private InstagramAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        AppUtils.setTitleBar(this, InstagramActivity.class);

        rcvPhotos = findViewById(R.id.rcv_photos);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rcvPhotos.setLayoutManager(manager);

        adapter = new InstagramAdapter();
        adapter.setData(this, getList());
        rcvPhotos.setAdapter(adapter);
    }

    private List<ListPhoto> getList() {
        List<ListPhoto> listPhotos = new ArrayList<>();
        int type = 1;
        for (int i = 0; i < 20; i++) {
            List<Photo> photos = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Photo photo = new Photo(R.drawable.img_400_600);
                photos.add(photo);
            }
            listPhotos.add(new ListPhoto(type, photos));
            type++;
            if (type == 4) {
                type = 1;
            }
        }
        return listPhotos;
    }
}