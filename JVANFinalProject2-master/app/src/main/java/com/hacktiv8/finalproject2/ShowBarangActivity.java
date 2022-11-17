package com.hacktiv8.finalproject2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ShowBarangActivity extends AppCompatActivity {

    private RecyclerView barangRV;
    private ArrayList<barangModel> barangsArrayList;
    private barangAdapter barangAdapter;
    private FirebaseFirestore db;
    private String dataValue;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_barang);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data..");
        progressDialog.show();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dataValue = extras.getString("key");
            //The key argument here must match that used in the other activity
        }

        setTitle(dataValue);


        // initializing our variables.
        barangRV = findViewById(R.id.menTopRecycler);

        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        // creating our new array list
        barangsArrayList = new ArrayList<>();
        barangRV.setHasFixedSize(true);
        barangRV.setLayoutManager(new LinearLayoutManager(this));

        // adding our array list to our recycler view adapter class.
        barangAdapter = new barangAdapter(barangsArrayList, this);

        // setting adapter to our recycler view.
        barangRV.setAdapter(barangAdapter);

        EventChangeListener();




    }

    private void EventChangeListener(){
        db.collection("databarang").whereEqualTo("kategori", dataValue)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                if (error != null){
                    Log.e("Firestore Error", error.getMessage());
                    return;
                }


                for (DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        barangsArrayList.add(new barangModel(dc.getDocument().get("namaBarang").toString(),dc.getDocument().get("hargaBarang").toString(),dc.getDocument().get("stokBarang").toString(),dc.getDocument().get("kategori").toString(),dc.getDocument().get("imageLink").toString()));
                    }
                    System.out.println(value.getDocuments());
                    barangAdapter.notifyDataSetChanged();

                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                }
        }});
        }
    }





