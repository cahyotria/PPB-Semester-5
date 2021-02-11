package com.example.crudcoba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Data> {

    Context context;
    List<Data> arrayListData;

    public MyAdapter(@NonNull Context context, List<Data> arrayListData) {
        super(context, R.layout.list_item, arrayListData);

        this.context = context;
        this.arrayListData = arrayListData;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);

        TextView tvID = view.findViewById(R.id.txtIdList);
        TextView tvName =  view.findViewById(R.id.txtNamaList);

        tvID.setText(arrayListData.get(position).getId());
        tvName.setText(arrayListData.get(position).getName());

        return view;
    }
}
