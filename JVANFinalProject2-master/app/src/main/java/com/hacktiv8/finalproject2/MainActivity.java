package com.hacktiv8.finalproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private Button btnMainLogin;
    private Button btnMainRegister;
    private TextView txtAdminLogin, txtStaffLogin, txtAboutPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        btnMainLogin = findViewById(R.id.btnMainLogin);
        btnMainRegister = findViewById(R.id.btnMainRegister);
        txtAdminLogin = findViewById(R.id.txtAdminLogin);
        txtStaffLogin = findViewById(R.id.txtStaffLogin);
        txtAboutPage = findViewById(R.id.txtAboutPage);

        db = FirebaseFirestore.getInstance();

        btnMainLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(MainActivity.this, LoginUserActivity.class);
                startActivity(i);
            }
        });

        btnMainRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(MainActivity.this, RegisterUserActivity.class);
                startActivity(i);
            }
        });

        txtAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(MainActivity.this, LoginAdminActivity.class);
                startActivity(i);
            }
        });

        txtStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(MainActivity.this, LoginStaffActivity.class);
                startActivity(i);
            }
        });

        txtAboutPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });
    }
}