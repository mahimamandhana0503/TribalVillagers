package com.example.chinmay.tribe;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import static com.example.chinmay.tribe.GuideReg.GET_FROM_GALLERY;
import static com.example.chinmay.tribe.HostReg.GET_FROM_GALLERY1;
import static com.example.chinmay.tribe.R.id.username;

public class guestupload extends AppCompatActivity {
    private Button b1, b2;
    private int flag = 0;private ProgressDialog prdialog;

    private ImageView i1;
    Uri selectedImage1;
    private String village1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b=getIntent().getExtras();
        if(b!=null)
        {
            village1=b.getString("village");
        }
        setContentView(R.layout.activity_guestupload);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        i1=(ImageView)findViewById(R.id.imageup);

        // setting Toolbar as Action Bar for the App
        setSupportActionBar(mToolbar);
        final ActionBar actionBar = getSupportActionBar();



        actionBar.setDisplayUseLogoEnabled(true);
        // FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        actionBar.setLogo(R.drawable.ic_person_black_24dp);
        actionBar.setTitle("Guest");
//        b1 = (Button) findViewById(R.id.uploadfin);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
//
//            }
//        });
        b2 = (Button) findViewById(R.id.uploadfin);
        b2.setEnabled(false);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prdialog = new ProgressDialog(guestupload.this);
                prdialog.setTitle("Uploading");
                prdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                prdialog.setCancelable(false);
                prdialog.show();

                StorageReference ref = FirebaseStorage.getInstance().getReference();
                final String key = "VillageRelation/" + village1 + "/notapprovedimg/" + Calendar.getInstance().getTimeInMillis() + ".jpg";
                StorageReference riversRef = ref.child(key);
                riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("notapprovedimg");
                        DatabaseReference postsRef = myRef.child(""+Calendar.getInstance().getTimeInMillis());
                        postsRef.setValue(key);
                        prdialog.dismiss();
                        Toast.makeText(guestupload.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(guestupload.this,guestvillage.class);
                        startActivity(i);


                    }
                });
            }
        });
    }

            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);


                //Detects request codes
                if (requestCode == GET_FROM_GALLERY1 && resultCode == Activity.RESULT_OK) {
                    selectedImage1 = data.getData();
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage1);
                        i1.setImageBitmap(bitmap);
                        flag=1;
                        b2.setEnabled(true);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

            public void imageup1(View view)
            {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY1);

            }

    }


