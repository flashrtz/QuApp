package com.example.quoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quoteapp.Model.Band;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URL;
import java.util.UUID;

public class Admin extends AppCompatActivity {

    private Uri filePath;

    private DatabaseReference myRef;

    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final String TAG = "Admin";

    private Band mBand;



    private String downloadUri;



    Spinner spinner;
    ImageView imageView;
    Button addbtn;
    String names[] = {"Motivation", "HeartTouching", "Reallife", "Dark", "Filmy", "Moddy/Sad", "Friendship", "Broken"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        imageView = findViewById(R.id.imageView);
        addbtn = findViewById(R.id.addbtn);


        spinner = findViewById(R.id.spinner);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Admin.this, names[position] + " SELECTED!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
private void add(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
     //   myRef = database.getReference("bands");

mBand = new Band();
mBand.setCategory(spinner.toString());


        if (filePath != null) {

            UploadTask uploadTask;

            final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            uploadTask = ref.putFile(filePath);
            Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {

                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        downloadUri = task.getResult().toString();
                        Log.d(TAG, "onComplete: " + downloadUri);
                        mBand.setImageUrl(downloadUri);

                        Toast.makeText(getApplicationContext(), "Url : " + downloadUri, Toast.LENGTH_SHORT).show();

                        myRef.push().setValue(mBand);
                        downloadUri = null;



                       // openviewBandActivity(mBand);

                    } else {
                        Toast.makeText(getApplicationContext(), "Can't Upload selected Picture ! Please try Again !", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    else {
        Toast.makeText(getApplicationContext(), " Please provide valid Information to register your mBand !", Toast.LENGTH_SHORT).show();
    }

}}







