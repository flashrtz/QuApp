package com.example.quoteapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URL;
import java.util.UUID;

public class Admin extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =1;

    private Uri mImageUri;

    private ProgressBar mProgressBar;

    private DatabaseReference myRef;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    private static final String TAG = "Admin";

    private Upload up;



    private String downloadUri;


    Button chooseimagebtn;
    Spinner spinner;
    ImageView imageView;
    Button addbtn;
    String names[] = {"Motivation", "HeartTouching", "Reallife", "Dark", "Filmy", "Moddy/Sad", "Friendship", "Broken"};
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        myRef = FirebaseDatabase.getInstance().getReference("uploads");

        imageView = findViewById(R.id.imageView);
        addbtn = findViewById(R.id.addbtn);
        chooseimagebtn = findViewById(R.id.chooseimagebtn);

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


        chooseimagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

    }



    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data !=null && data.getData() != null){
                mImageUri = data.getData();

                Picasso.with(this).load(mImageUri).into(imageView);}
    }


//      private String getFileExtensionFile(Uri uri){
//    ContentResolver cR = getContentResolver();
//    MimeTypeMap mime = MimeTypeMap.getSingleton();
//    return mime.getExtensionFromMimeType(cR.getType(uri));
//}

    private void uploadFile(){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("uploads");

        up = new Upload();

        if (mImageUri != null) {

            UploadTask uploadTask;

            final StorageReference ref = storageReference.child("uploads/" + UUID.randomUUID().toString());
            uploadTask = ref.putFile(mImageUri);
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
//                        Log.d(TAG, "onComplete: " + downloadUri);
                        up.setImageUrl(downloadUri);

                        Toast.makeText(getApplicationContext(), "Url : " + downloadUri, Toast.LENGTH_SHORT).show();

                        myRef.push().setValue(up);
                        downloadUri = null;



                      //  openviewBandActivity(mBand);

                    } else {
                        Toast.makeText(getApplicationContext(), "Can't Upload selected Picture ! Please try Again !", Toast.LENGTH_SHORT).show();

                    }
                }
            });


    } else {
        Toast.makeText(getApplicationContext(), " Please provide valid Information to register your mBand !", Toast.LENGTH_SHORT).show();
    }


//    if(mImageUri != null){
//
//        StorageReference fileReference = storageReference.child(System.currentTimeMillis()+"."+getFileExtensionFile(mImageUri));
//        fileReference.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                downloadUri = tas
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Admin.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
//                double progress = (100.0+taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
//                mProgressBar.setProgress((int)progress);
//            }
//        });
//
//    }
//    else{
//        Toast.makeText(this, "No File SELECTED!", Toast.LENGTH_SHORT).show();
//    }
//



   }
}



