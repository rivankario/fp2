package com.hacktiv8.finalproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginStaffActivity extends AppCompatActivity {
    private EditText txtStaffLoginEmail, txtStaffLoginPassword;
    private Button btnLoginStaff;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_staff);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        txtStaffLoginEmail = findViewById(R.id.txtStaffLoginEmail);
        txtStaffLoginPassword = findViewById(R.id.txtStaffLoginPassword);
        btnLoginStaff = findViewById(R.id.btnLoginStaff);

        progressDialog = new ProgressDialog(LoginStaffActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Menunggu");
        progressDialog.setCancelable(false);

        //adding on click listener for our login button.
        btnLoginStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting data from our edit text on below line.

                String email = txtStaffLoginEmail.getText().toString();
                String password = txtStaffLoginPassword.getText().toString();
                //on below line validating the text input.
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginStaffActivity.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.matches("^\\S+@staff\\.com$")) {
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //on below line we are checking if the task is succes or not.
                            if (task.isSuccessful()) {
                                //on below line we are hiding our progress bar.
                                progressDialog.dismiss();
                                Toast.makeText(LoginStaffActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(LoginStaffActivity.this, ActivityAdmin.class);
//                                startActivity(i);
//                                finish();
                            } else {
                                //hiding our progress bar and displaying a toast message.
                                progressDialog.dismiss();
                                Toast.makeText(LoginStaffActivity.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginStaffActivity.this, "User Cannot Login on Staff Page", Toast.LENGTH_SHORT).show();
                    return;
                }
                //on below line we are calling a sign in method and passing email and password to it.

            }
        });
    }
}