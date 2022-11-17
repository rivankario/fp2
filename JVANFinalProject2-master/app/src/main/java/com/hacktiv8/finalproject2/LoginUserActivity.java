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
import com.google.firebase.auth.FirebaseUser;

public class LoginUserActivity extends AppCompatActivity {
    private EditText txtEmail, txtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txt_email);
        txtPassword = findViewById(R.id.txt_password);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        progressDialog = new ProgressDialog(LoginUserActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Menunggu");
        progressDialog.setCancelable(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line opening a login activity.
                Intent i = new Intent(LoginUserActivity.this, RegisterUserActivity.class);
                startActivity(i);
            }
        });


        //adding on click listener for our login button.
        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //getting data from our edit text on below line.

            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            //on below line validating the text input.
            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                Toast.makeText(LoginUserActivity.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.matches("^\\S+@admin\\.com$")) {
                Toast.makeText(LoginUserActivity.this, "Admin Cannot Login on User Page", Toast.LENGTH_SHORT).show();
                return;
            } else {
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //on below line we are checking if the task is succes or not.
                        if (task.isSuccessful()) {
                            //on below line we are hiding our progress bar.
                            progressDialog.dismiss();
                            Toast.makeText(LoginUserActivity.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginUserActivity.this, HomeActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            //hiding our progress bar and displaying a toast message.
                            progressDialog.dismiss();
                            Toast.makeText(LoginUserActivity.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            //on below line we are calling a sign in method and passing email and password to it.

        }
    });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //in on start method checking if the user is already sign in.
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            //if the user is not null then we are opening a main activity on below line.
            Intent i = new Intent(LoginUserActivity.this, HomeActivity.class);
            startActivity(i);
            this.finish();
        }

    }

}