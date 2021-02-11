package com.example.datasiswa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private List<dataSiswa> listSiswa;
    private Context context;
    private DataSiswaOpenHelper helper;

    private TextView tv_nama, tv_nim;
    private RelativeLayout relativeLayout;
    private ImageView imgDelete;

    public ListViewAdapter(List<dataSiswa> listSiswa, Context context) {
        this.listSiswa = listSiswa;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listSiswa.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_siswa, null);
        tv_nim = v.findViewById(R.id.tv_NIM);
        tv_nama = v.findViewById(R.id.tv_Nama);
        relativeLayout = v.findViewById(R.id.relative);
        imgDelete = v.findViewById(R.id.imgDelete);

        tv_nim.setText(listSiswa.get(position).getNim());
        tv_nama.setText(listSiswa.get(position).getNama());

        helper = new DataSiswaOpenHelper(context);

       relativeLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(context, InputActivity.class);
               intent.putExtra("ID", listSiswa.get(position).getId());
               intent.putExtra("NIM", listSiswa.get(position).getNim());
               intent.putExtra("NAMA", listSiswa.get(position).getNama());
               intent.putExtra("TANDA", "Ubah");

               context.startActivity(intent);
           }
       });

       imgDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               helper.deleteData(listSiswa.get(position).getId());
               Intent intent = new Intent(context, MainActivity.class);
               context.startActivity(intent);
           }
       });


        return v;
    }
}
