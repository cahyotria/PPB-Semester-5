package com.example.zantechmysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class MyAdapter extends ArrayAdapter<DataBarang> {

    Context context;
    List<DataBarang> dataBarangList;

    public MyAdapter(@NonNull Context context, List<DataBarang> dataBarangList) {
        super(context, R.layout.list_item, dataBarangList);
        this.context = context;
        this.dataBarangList = dataBarangList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);

        TextView tvKodeBarang = view.findViewById(R.id.txtKodeBarang);
        TextView tvNamaBarang = view.findViewById(R.id.txtNamaBarang);
        TextView tvHargaBarang = view.findViewById(R.id.txtHargaBarang);

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);


        tvKodeBarang.setText(dataBarangList.get(position).getKode_barang());
        tvNamaBarang.setText(dataBarangList.get(position).getNama_barang());
        tvHargaBarang.setText(String.valueOf(kursIndonesia.format(dataBarangList.get(position).getHarga_jual())));

        return view;
    }
}
