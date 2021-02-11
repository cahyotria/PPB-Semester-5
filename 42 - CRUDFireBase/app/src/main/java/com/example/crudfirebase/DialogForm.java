package com.example.crudfirebase;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class DialogForm extends DialogFragment {

    String kodeBarang, namaBarang, satuan, hargaBeli, hargaJual, stok, stokMin, key, pilih;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public DialogForm(String kodeBarang, String namaBarang, String satuan, String hargaBeli, String hargaJual, String stok, String stokMin, String key, String pilih) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.satuan = satuan;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
        this.stok = stok;
        this.stokMin = stokMin;
        this.key = key;
        this.pilih = pilih;
    }

    TextView et_kodeBarang, et_namaBarang, et_satuan, et_hargaBeli, et_hargaJual, et_stok, et_stokMin;

    Button btnSimpan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.input_form, container, false);

        et_kodeBarang = view.findViewById(R.id.ed_KodeBarang);
        et_namaBarang = view.findViewById(R.id.ed_NamaBarang);
        et_satuan = view.findViewById(R.id.ed_Satuan);
        et_hargaBeli = view.findViewById(R.id.ed_HargaBeli);
        et_hargaJual = view.findViewById(R.id.ed_HargaJual);
        et_stok = view.findViewById(R.id.ed_Stok);
        et_stokMin = view.findViewById(R.id.ed_StokMin);

        btnSimpan = view.findViewById(R.id.btn_Simpan);

        et_kodeBarang.setText(kodeBarang);
        et_namaBarang.setText(namaBarang);
        et_satuan.setText(satuan);
        et_hargaBeli.setText(hargaBeli);
        et_hargaJual.setText(hargaJual);
        et_stok.setText(stok);
        et_stokMin.setText(stokMin);



        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kd_barang        = et_kodeBarang.getText().toString();
                String nm_barang        = et_namaBarang.getText().toString();
                String st_barang        = et_satuan.getText().toString();
                String hb_barang        = et_hargaBeli.getText().toString();
                String hj_barang        = et_hargaJual.getText().toString();
                String stok_barang      = et_stok.getText().toString();
                String stokmin_barang   = et_stokMin.getText().toString();

                if(
                        TextUtils.isEmpty(kd_barang) &&
                        TextUtils.isEmpty(nm_barang) &&
                        TextUtils.isEmpty(st_barang) &&
                        TextUtils.isEmpty(hb_barang) &&
                        TextUtils.isEmpty(hj_barang) &&
                        TextUtils.isEmpty(stok_barang) &&
                        TextUtils.isEmpty(stokmin_barang)
                ){
                    input((EditText) et_kodeBarang, "Kode Barang");
                    input((EditText) et_namaBarang, "Nama Barang");
                    input((EditText) et_satuan, "Satuan");
                    input((EditText) et_hargaBeli, "Harga Beli");
                    input((EditText) et_hargaJual, "Harga Jual");
                    input((EditText) et_stok, "Stok");
                    input((EditText) et_stokMin, "Stok Min");
                }else {
                    if(pilih.equals("Tambah")){
                        databaseReference.child("Data")
                                .push()
                                .setValue(new Barang(kd_barang,nm_barang,st_barang,hb_barang,hj_barang,stok_barang,stokmin_barang))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        reset();
                                        Toast.makeText(view.getContext(), "Data Tersimpan", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        reset();
                                        Toast.makeText(view.getContext(), "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else if(pilih.equals("Ubah")){
                        databaseReference.child("Data")
                                .child(key)
                                .setValue(new Barang(kd_barang,nm_barang,st_barang,hb_barang,hj_barang,stok_barang,stokmin_barang))
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        reset();
                                        Toast.makeText(view.getContext(), "Data Berhasil Di Ubah", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        reset();
                                        Toast.makeText(view.getContext(), "Data Gagal Di Ubah", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                }
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    private void reset(){
        et_kodeBarang.setText("");
        et_namaBarang.setText("");
        et_satuan.setText("");
        et_hargaBeli.setText("");
        et_hargaJual.setText("");
        et_stok.setText("");
        et_stokMin.setText("");
    }

    private void input(EditText txt, String s){
        txt.setError(s + " Tidak Boleh Kosong");
        txt.requestFocus();
    }




}
