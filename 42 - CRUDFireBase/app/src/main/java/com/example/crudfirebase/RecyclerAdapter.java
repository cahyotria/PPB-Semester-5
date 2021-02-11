package com.example.crudfirebase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<Barang> barangList;
    private Activity activity;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public RecyclerAdapter(List<Barang> barangList, Activity activity) {
        this.barangList = barangList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        Barang brg = barangList.get(position);
        holder.tv_KodeBarang.setText("Kode : " + brg.getKodeBarang());
        holder.tv_NamaBarang.setText("Nama : " + brg.getNamaBarang());
        holder.tv_Satuan.setText(brg.getSatuan());
        holder.tv_HargaBeli.setText("Rp. " + brg.getHargaBeli());
        holder.tv_HargaJual.setText("Rp. " + brg.getHargaJual());
        holder.tv_Stok.setText("Stok : " + brg.getStok() + " " + brg.getSatuan());
        holder.tv_StokMin.setText("StokMin : " + brg.getStokMin() + " " + brg.getSatuan());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)activity).getSupportFragmentManager();
                DialogForm dialogForm = new DialogForm(brg.getKodeBarang(),
                        brg.getNamaBarang(),
                        brg.getSatuan(),
                        brg.getHargaBeli(),
                        brg.getHargaJual(),
                        brg.getStok(),
                        brg.getStokMin(),
                        brg.getKey(),
                        "Ubah"
                        );
                dialogForm.show(fragmentManager, "form");
            }
        });

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("Data")
                                .child(brg.getKey())
                                .removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(activity, "Berhasil Di Hapus", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(activity, "Gagal Di Hapus", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setMessage("Apakah Yakin ingin menghapus item " + brg.getNamaBarang() + " ?");
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return barangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_KodeBarang, tv_NamaBarang, tv_Satuan, tv_HargaBeli, tv_HargaJual, tv_StokMin,tv_Stok;
        ImageView hapus;

        CardView cardView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_KodeBarang = itemView.findViewById(R.id.tv_KodeBarang);
            tv_NamaBarang = itemView.findViewById(R.id.tv_NamaBarang);
            tv_Satuan = itemView.findViewById(R.id.tv_Satuan);
            tv_HargaBeli = itemView.findViewById(R.id.tv_HargaBeli);
            tv_HargaJual = itemView.findViewById(R.id.tv_HargaJual);
            tv_Stok = itemView.findViewById(R.id.tv_Stok);
            tv_StokMin = itemView.findViewById(R.id.tv_StokMin);
            cardView = itemView.findViewById(R.id.cardView);

            hapus  = itemView.findViewById(R.id.hapus);
        }
    }
}
