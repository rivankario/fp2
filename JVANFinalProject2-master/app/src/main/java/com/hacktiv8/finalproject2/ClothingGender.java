package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClothingGender extends AppCompatActivity {
    public ImageButton manButton, womanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_gender);
        getSupportActionBar().hide();
        manButton = findViewById(R.id.manButton);
        womanButton = findViewById(R.id.womanButton);


        manButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClothingGender.this, ClothingCategory.class);
                startActivity(i);
            }
        });

        womanButton = findViewById(R.id.womanButton);
        womanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClothingGender.this, ShowBarangActivity.class);
                i.putExtra("key","Pakaian Wanita");
                startActivity(i);
            }
        });
    }
}