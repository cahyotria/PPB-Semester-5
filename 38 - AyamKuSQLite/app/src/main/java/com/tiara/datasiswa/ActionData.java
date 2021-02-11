package com.tiara.datasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.model.Config;
import com.nguyenhoanglam.imagepicker.model.Image;
import com.nguyenhoanglam.imagepicker.ui.imagepicker.ImagePicker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionData extends AppCompatActivity {
    @BindView(R.id.edt_value_name)
    EditText edtValueName;
    @BindView(R.id.edt_value_harga)
    EditText edtValueHarga;
    @BindView(R.id.img_value_profile)
    ImageView imgValueProfile;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    private DataModel dataModel;
    private ArrayList<Image> imageLibrary = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_siswa);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        dataModel = bundle.getParcelable(MainAdapter.EXTRA_SISWA);
        edtValueName.setText(dataModel.getName());
        edtValueHarga.setText(dataModel.getHarga());
        Glide.with(this)
                .load(dataModel.getPathPicture())
                .into(imgValueProfile);
    }
    @OnClick(R.id.img_value_profile)
    public void onImgValueProfileClicked() {
        ImagePicker.with(this)
                .setFolderMode(true)
                .setMaxSize(10)
                .setMultipleMode(false)
                .setCameraOnly(false)
                .setFolderTitle("Albums")
                .setSelectedImages(imageLibrary)
                .setAlwaysShowDoneButton(true)
                .setKeepScreenOn(true)
                .start();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            imageLibrary = data.getParcelableArrayListExtra(Config.EXTRA_IMAGES);
            dataModel.setPathPicture(imageLibrary.get(0).getPath());
            Glide.with(this)
                    .load(imageLibrary.get(0).getPath()).into(imgValueProfile);
        }
    }
    @OnClick(R.id.btn_update)
    public void onBtnUpdateClicked() {
        dataModel.setName(edtValueName.getText().toString());
        dataModel.setHarga(edtValueHarga.getText().toString());
        dataModel.setPathPicture(dataModel.getPathPicture());
        MyApp.db.userDao().update(dataModel);
        startActivity(new Intent(this, MainActivity.class));
        Intent intent = new Intent(new Intent(this, MainActivity.class));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @OnClick(R.id.btn_delete)
    public void onBtnDeleteClicked() {
        MyApp.db.userDao().deleteUsers(dataModel);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}