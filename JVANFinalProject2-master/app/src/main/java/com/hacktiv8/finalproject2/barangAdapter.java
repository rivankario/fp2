package com.hacktiv8.finalproject2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class barangAdapter extends RecyclerView.Adapter<barangAdapter.ViewHolder>{
    private List<barangModel> barangsArrayList;
    private Context context;

    // creating constructor for our adapter class
    public barangAdapter(List<barangModel> coursesArrayList, Context context) {
        this.barangsArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public barangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.barang_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull barangAdapter.ViewHolder holder, int position) {
        // setting data to our text views from our modal class.
        barangModel courses = barangsArrayList.get(position);
        holder.tvNamaBarang.setText(courses.getDataNama());
        holder.tvHargaBarang.setText(courses.getDataHarga());
        holder.tvStokBarang.setText(courses.getDataStok());
        holder.tvKategoriBarang.setText(courses.getDataKategori());
        Picasso.get().load(courses.getDataImage()).fit().centerCrop().into(holder.imgBarang);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendData1 = new Intent(context, detailsBarangActivity.class);
                sendData1.putExtra("dataNamaBarang", courses.getDataNama());
                sendData1.putExtra("dataHargaBarang", courses.getDataHarga());
                sendData1.putExtra("dataStokBarang", courses.getDataStok());
                sendData1.putExtra("dataImageBarang", courses.getDataImage());
                context.startActivity(sendData1);


            }
        });
    }


    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return barangsArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView tvNamaBarang;
        private final TextView tvHargaBarang;
        private final TextView tvStokBarang;
        private final TextView tvKategoriBarang;
        private final ImageView imgBarang;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            tvNamaBarang = itemView.findViewById(R.id.tvNamaBarang);
            tvHargaBarang = itemView.findViewById(R.id.tvHargaBarang);
            tvStokBarang = itemView.findViewById(R.id.tvStokBarang);
            tvKategoriBarang = itemView.findViewById(R.id.tvKategoriBarang);
            imgBarang = itemView.findViewById(R.id.imgBarang);
        }
    }



}
