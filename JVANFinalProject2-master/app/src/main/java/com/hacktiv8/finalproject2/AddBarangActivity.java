package com.hacktiv8.finalproject2;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class AddBarangActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText idBarang, stokBarang;
    private Button btntambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_barang);
        db = FirebaseFirestore.getInstance();
        idBarang = findViewById(R.id.txt_id);
        stokBarang = findViewById(R.id.txt_stokBarang);
        btntambah = findViewById(R.id.btntambah);

        db.collection("databarang")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahbarang(idBarang.getText().toString(), stokBarang.getText().toString());
            }
        });
    }

    public void tambahbarang(String idBarang,String stokBarang){

        // Get a new write batch
        WriteBatch batch = db.batch();

        DocumentReference sfRef = db.collection("databarang").document(idBarang);
        batch.update(sfRef, "stokBarang", stokBarang);

//

        // Commit the batch
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddBarangActivity.this, "Update Stock Success", Toast.LENGTH_SHORT).show();
            }
        });

    }
}