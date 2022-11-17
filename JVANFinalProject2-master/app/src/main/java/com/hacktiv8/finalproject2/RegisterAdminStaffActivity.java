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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterAdminStaffActivity extends AppCompatActivity {
    private EditText txtStaffRegisterEmail, txtStaffRegisterPassword, txtStaffRegisterConfirmPassword;
    private TextView loginTV;
    private Button btnStaffRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_staff);
        getSupportActionBar().hide();

        txtStaffRegisterEmail = findViewById(R.id.txtStaffRegisterEmail);
        txtStaffRegisterPassword = findViewById(R.id.txtStaffRegisterPassword);
        txtStaffRegisterConfirmPassword = findViewById(R.id.txtStaffRegisterConfirmPassword);
        btnStaffRegister = findViewById(R.id.btnRegisterStaff);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterAdminStaffActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Silahkan Menunggu");
        progressDialog.setCancelable(false);

        btnStaffRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hiding our progress bar.

                //getting data fro =m our edit text.
                String userName = txtStaffRegisterEmail.getText().toString();
                String pwd = txtStaffRegisterPassword.getText().toString();
                String cnfPwd = txtStaffRegisterConfirmPassword.getText().toString();
                //checking if the password and confirm password is equal or not.

                if (userName.matches("^\\S+@staff\\.com$") == false){
                    Toast.makeText(RegisterAdminStaffActivity.this, "Email Stuff Must Ended with @staff.com", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!pwd.equals(cnfPwd)) {
                    Toast.makeText(RegisterAdminStaffActivity.this, "Please check both having same password..", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(cnfPwd)) {
                    //checking if the text fields are empty or not.
                    Toast.makeText(RegisterAdminStaffActivity.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                } else {
                    progressDialog.show();
                    //on below line we are creating a new user by passing email and password.
                    mAuth.createUserWithEmailAndPassword(userName, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //on below line we are checking if the task is success or not.
                            if (task.isSuccessful()) {
                                //in on success method we are hiding our progress bar and opening a login activity.
                                progressDialog.dismiss();
                                Toast.makeText(RegisterAdminStaffActivity.this, "Staff Registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterAdminStaffActivity.this, ActivityAdmin.class);
                                startActivity(i);
                                finish();
                            } else {
                                //in else condition we are displaying a failure toast message.
                                progressDialog.dismiss();
                                Toast.makeText(RegisterAdminStaffActivity.this, "Fail to register Staff..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}