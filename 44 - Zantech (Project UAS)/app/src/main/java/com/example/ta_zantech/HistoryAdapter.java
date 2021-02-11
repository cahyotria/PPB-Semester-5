package com.example.ta_zantech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyHistoryHolder> {

    private Context mContext;
    private List<History> mData;

    public HistoryAdapter(Context mContext, List<History> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyHistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.item_history, parent, false);

        return new MyHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyHistoryHolder holder, int position) {
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        Integer harga = Integer.parseInt(mData.get(position).getHarga());

        holder.tvNamaProduk.setText(mData.get(position).getNama_produk());
        holder.tvHarga.setText(String.valueOf(kursIndonesia.format(harga)));
        holder.tvWaktu.setText(mData.get(position).getTanggal() + " - " + mData.get(position).getJam());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyHistoryHolder extends RecyclerView.ViewHolder {

        TextView tvNamaProduk, tvHarga, tvWaktu;

        public MyHistoryHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaProduk    = itemView.findViewById(R.id.tv_NamaProduk);
            tvHarga         = itemView.findViewById(R.id.tv_Harga);
            tvWaktu         = itemView.findViewById(R.id.tv_waktu);
        }
    }
}
