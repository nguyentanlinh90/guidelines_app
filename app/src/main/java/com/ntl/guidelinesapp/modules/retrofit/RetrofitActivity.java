package com.ntl.guidelinesapp.modules.retrofit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ntl.guidelinesapp.R;
import com.ntl.guidelinesapp.modules.retrofit.api.ApiService;
import com.ntl.guidelinesapp.modules.retrofit.api.ApiService2;
import com.ntl.guidelinesapp.modules.retrofit.model.Article;
import com.ntl.guidelinesapp.modules.retrofit.model.ArticleObject;
import com.ntl.guidelinesapp.modules.retrofit.model.User;
import com.ntl.guidelinesapp.modules.retrofit.utils.RealPathUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION = 99;
    private ApiService apiService;
    private ArticleObject articleObject;
    private ArrayList<Article> articles;

    private Button btDemoGetApi, btSelectImage, btUploadUser;
    private TextView tvUserName, tvPassword;
    private ImageView imgSelect, imgUpload;
    private Uri mUri;

    private ProgressDialog progressBar;

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e("linhnt", "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            imgSelect.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        getSupportActionBar().setTitle("RetrofitActivity");
        btDemoGetApi = findViewById(R.id.bt_get_api_demo);
        btSelectImage = findViewById(R.id.bt_select_img);
        btUploadUser = findViewById(R.id.bt_upload);
        tvUserName = findViewById(R.id.tv_user_name_upload);
        tvPassword = findViewById(R.id.tv_password_upload);
        imgSelect = findViewById(R.id.img_select);
        imgUpload = findViewById(R.id.img_upload);

        btDemoGetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGetApiDemo();
            }
        });

        btSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btUploadUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUploadUser();
            }
        });
    }

    private void startUploadUser() {
        if (mUri != null) {
            progressBar = new ProgressDialog(this);
            progressBar.setMessage("Please wait...");
            progressBar.show();

            String realPath = RealPathUtil.getRealPath(this, mUri);
            Log.e("linhnt real path: ", realPath);
            File file = new File(realPath);

            RequestBody requestBodyUsername = RequestBody.create(MediaType.parse("multipart/form-data"), "LinhNguyen");
            RequestBody requestBodyPass = RequestBody.create(MediaType.parse("multipart/form-data"), "123");
            RequestBody requestBodyAvatar = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("avt", file.getName(), requestBodyAvatar);

            ApiService2.getInstance.createAccount(requestBodyUsername, requestBodyPass, multipartBody).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    progressBar.dismiss();
                    User mUser = response.body();
                    if (mUser != null) {
                        Gson gson = new Gson();
                        Log.e("linhnt muser: ", gson.toJson(mUser));
                        //todo coding
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    progressBar.dismiss();
                    Log.e("linhnt onFailure: ", t.getMessage());
                    Toast.makeText(RetrofitActivity.this, "call api fail", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void startGetApiDemo() {
        apiService.getInstance.getArticle("tesla", "2022-04-17", "publishedAt", "bcb2f4782c934949a08a0282a408bae0")
                .enqueue(new Callback<ArticleObject>() {
                    @Override
                    public void onResponse(Call<ArticleObject> call, Response<ArticleObject> response) {
                        articleObject = response.body();
                        if (articleObject != null) {
                            if (articleObject.getStatus().equalsIgnoreCase("ok")) {
                                Gson gson = new Gson();
                                Log.e("linhnt articleObject: ", gson.toJson(articleObject));

                                articles = articleObject.getArticles();
                                Log.e("linhnt articles size: ", articles.size() + "");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleObject> call, Throwable t) {
                        Toast.makeText(RetrofitActivity.this, "call api fail", Toast.LENGTH_SHORT).show();
                    }
                });

        //goi kieu Query Map
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("everything", "tesla");
        queryMap.put("from", "2022-04-17");
        queryMap.put("publishedAt", "publishedAt");
        queryMap.put("apiKey", "bcb2f4782c934949a08a0282a408bae0");
        //todo
        apiService.getInstance.getArticle2(queryMap);
    }


    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            openGallery();
            return;
        }

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            requestPermissions(permissions, REQUEST_CODE_PERMISSION);
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}