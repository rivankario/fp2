package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ClothingCategory extends AppCompatActivity {
    public ImageButton tshirtButton, formalButton, bottomButton, shoesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing_category);
        getSupportActionBar().hide();
        tshirtButton = findViewById(R.id.tshirtButton);
        formalButton = findViewById(R.id.formalButton);
        bottomButton = findViewById(R.id.bottomButton);
        shoesButton = findViewById(R.id.shoesButton);

        tshirtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ClothingCategory.this, ShowBarangActivity.class);
                i.putExtra("key","Men - T-Shirt");
                startActivity(i);
            }
        });

        formalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ClothingCategory.this, ShowBarangActivity.class);
                i.putExtra("key","Men - Formals");
                startActivity(i);
            }
        });

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ClothingCategory.this, ShowBarangActivity.class);
                i.putExtra("key","Men - Bottom");
                startActivity(i);
            }
        });

        shoesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ClothingCategory.this, ShowBarangActivity.class);
                i.putExtra("key","Men - Shoes");
                startActivity(i);
            }
        });
    }
}