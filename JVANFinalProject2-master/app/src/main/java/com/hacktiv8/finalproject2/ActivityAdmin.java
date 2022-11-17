package com.hacktiv8.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ActivityAdmin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnAddStaff;
    private Button btnAddStock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mAuth = FirebaseAuth.getInstance();
        btnAddStaff = findViewById(R.id.btnAddStaff);
        btnAddStock = findViewById(R.id.btnAddStock);

        btnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ActivityAdmin.this, RegisterAdminStaffActivity.class);
                startActivity(i);
            }
        });

        btnAddStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(ActivityAdmin.this, AddBarangActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //adding a click listner for option selected on below line.
        int id = item.getItemId();
        switch (id) {
            case R.id.idAdminLogOut:
                //displaying a toast message on user logged out inside on click.
                Toast.makeText(getApplicationContext(), "Admin Logged Out", Toast.LENGTH_LONG).show();
                //on below line we are signing out our user.
                mAuth.signOut();
                //on below line we are opening our login activity.
                Intent i = new Intent(ActivityAdmin.this, LoginAdminActivity.class);
                startActivity(i);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //on below line we are inflating our menu file for displaying our menu options.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }
}