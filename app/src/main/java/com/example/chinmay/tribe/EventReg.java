package com.example.chinmay.tribe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class EventReg extends AppCompatActivity {


    private FirebaseDatabase database;
    private StorageReference mstorageref;
    private DatabaseReference myRef;
    private ProgressDialog prdialog;
    private Bundle b1;
    String url,nplace;
    Uri selectedImage1, selectedImage2, selectedImage3, selectedImage4, selectedImage5;
    String username, village1;
    ImageView i1, prop1, prop2, prop3, prop4;
    EditText name,date,description,category;
    String name1, date1, descrip1, category1;
    Button b;
    public static final int GET_FROM_GALLERY1 = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_host_reg3);

        b1 = getIntent().getExtras();
        mstorageref = FirebaseStorage.getInstance().getReference();
        if (b1 != null) {

            username = b1.getString("username");
            village1 = b1.getString("village");


        }
        name=(EditText)findViewById(R.id.name);
        b = (Button) findViewById(R.id.button2);                                       //
        date = (EditText) findViewById(R.id.date);
        i1 = (ImageView) findViewById(R.id.image);
        description = (EditText) findViewById(R.id.description);
        category = (EditText) findViewById(R.id.category);



        registerForContextMenu(i1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });


//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar1);
//        // setting Toolbar as Action Bar for the App
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar=getSupportActionBar();
//
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setLogo(R.drawable.ic_person_black_24dp);
//        actionBar.setTitle("UserName");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        // menu.setHeaderTitle("Select a Role to Register");
        menu.add(0,v.getId(),0,"Add Image");//groupId, itemId, order, title


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
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.image) {


            startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY1);

        }
        else
        {
            return false;
        }
        return true;

    }

    private void attemptLogin() {


        // Reset errors.

        name1=name.getText().toString();

        // Store values at the time of the login attempt.
        category1 = category.getText().toString();
        descrip1 = description.getText().toString();
         date1= date.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (name1.length() < 1) {
            name.setError("This Field cannot be blank");
            // focusView = vill;
            cancel = true;
        }
        if (category1.length() < 1) {
            category.setError("This Field cannot be blank");
            // focusView = vill;
            cancel = true;
        }

        // Check for a valid email address.
        if (descrip1.length() < 1) {
            description.setError("Check Phone Number");
            //focusView = contact;
            cancel = true;
        }
        if (TextUtils.isEmpty(date1))  {
            date.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            // focusView.requestFocus();
        } else {
            prdialog = new ProgressDialog(this);
            prdialog.setTitle("Uploading");
            prdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            prdialog.setCancelable(false);
            prdialog.show();

            StorageReference ref = FirebaseStorage.getInstance().getReference();
            final String key = "VillageRelation/" + village1 + "/EventRelation/" + username + ".jpg";
            StorageReference riversRef = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("EventRelation").child(username);
                    DatabaseReference postsRef = myRef;                                       //


//                    postsRef.child("image").setValue();
//                    postsRef.child("capacityEachRoom").setValue(capacity1);
//                    postsRef.child("email").setValue(email1);
//
//                    //
//                    //Image
//                    postsRef.child("address").child("village").setValue(village1);
//                    postsRef.child("address").child("plotno").setValue(plot1);
//                    postsRef.child("address").child("district").setValue(district1);
//                    postsRef.child("address").child("locality").setValue(locality1);
//                    postsRef.child("address").child("state").setValue(state1);
//                    postsRef.child("address").child("street").setValue(street1);
//                    postsRef.child("address").child("pincode").setValue(pin1);
//                    postsRef.child("approved").setValue("NOT APPROVED");
//
//                    postsRef.child("rating").setValue("0");
//                    postsRef.child("nameOfplace").setValue(nplace);
//                    postsRef.child("profilepic").setValue(key);
//                    postsRef.child("price").setValue(tariff1);
//                    postsRef.child("roomsAvailable").setValue(available1);
                    prdialog.dismiss();
                    Intent   i = new Intent(EventReg.this, Main5Activity.class);
                    Toast.makeText(EventReg.this, "Success", Toast.LENGTH_SHORT).show();
                    i.putExtra("username",username);
                    startActivity(i,
                            ActivityOptions.makeSceneTransitionAnimation(EventReg.this).toBundle());
//                    Intent   i = new Intent(HostReg.this, Main5Activity.class);
//                    Toast.makeText(HostReg.this, "Success", Toast.LENGTH_SHORT).show();
//                    i.putExtra("username",username);
//                    startActivity(i,
//                            ActivityOptions.makeSceneTransitionAnimation(HostReg.this).toBundle());


                }
            });



                }

        }
    }

