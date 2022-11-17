package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailsBarangActivity extends AppCompatActivity {
    private TextView tvDetailsNama, tvDetailsStok;
    private ImageView imageDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_barang);

        tvDetailsNama = findViewById(R.id.tvDetailsNama);
        tvDetailsStok = findViewById(R.id.tvDetailsStok);
        imageDetails = findViewById(R.id.imgDetailsBarang);

        // Get Data From Intent in next activity

        String dataNama = getIntent().getStringExtra("dataNamaBarang").toString();
        String dataHarga = getIntent().getStringExtra("dataHargaBarang").toString();
        String dataStok = getIntent().getStringExtra("dataStokBarang").toString();
        String dataImage = getIntent().getStringExtra("dataImageBarang").toString();

        tvDetailsNama.setText(dataNama);
        tvDetailsStok.setText(dataStok);
        Picasso.get().load(dataImage).fit().centerCrop().into(imageDetails);

    }
}