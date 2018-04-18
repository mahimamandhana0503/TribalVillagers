package com.example.chinmay.tribe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

import static com.example.chinmay.tribe.GuideReg.GET_FROM_GALLERY;

public class HostReg extends AppCompatActivity {


    private FirebaseDatabase database;
    private StorageReference mstorageref;
    private DatabaseReference myRef;
    private ProgressDialog prdialog;
    private Bundle b1;
    String url,nplace;
    Uri selectedImage1, selectedImage2, selectedImage3, selectedImage4, selectedImage5;
    String username, village1;
    ImageView i1, prop1, prop2, prop3, prop4;
    EditText capacity, email, contact, available, tariff, street, plot, locality, district, pin, state,nameplace;
    String capacity1, email1, contact1, available1, tariff1, street1, plot1, locality1, district1, pin1, state1;
    Button b;
    public static final int GET_FROM_GALLERY1 = 1;
    public static final int GET_FROM_GALLERY2 = 2;
    public static final int GET_FROM_GALLERY3 = 3;
    public static final int GET_FROM_GALLERY4 = 4;
    public static final int GET_FROM_GALLERY5 = 5;


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
        nameplace=(EditText)findViewById(R.id.nameofplace);
        b = (Button) findViewById(R.id.button2);                                       //
        capacity = (EditText) findViewById(R.id.capacity);
        i1 = (ImageView) findViewById(R.id.image);
        email = (EditText) findViewById(R.id.email);
        contact = (EditText) findViewById(R.id.contact);
        available = (EditText) findViewById(R.id.available);
        tariff = (EditText) findViewById(R.id.tariffnight);
        street = (EditText) findViewById(R.id.Street);
        plot = (EditText) findViewById(R.id.PlotNo);
        locality = (EditText) findViewById(R.id.locality);
        state = (EditText) findViewById(R.id.State);
        pin = (EditText) findViewById(R.id.pin);
        district = (EditText) findViewById(R.id.district);
        prop1 = (ImageView) findViewById(R.id.prop1);
        prop2 = (ImageView) findViewById(R.id.prop2);
        prop3 = (ImageView) findViewById(R.id.prop3);
        prop4 = (ImageView) findViewById(R.id.prop4);

        database = FirebaseDatabase.getInstance();
        registerForContextMenu(i1);
        registerForContextMenu(prop1);
        registerForContextMenu(prop2);
        registerForContextMenu(prop3);
        registerForContextMenu(prop4);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        myRef = database.getReference("UserRelation");
        capacity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        contact.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        available.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        state.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        tariff.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        locality.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        plot.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        district.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        pin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        street.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
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
        if (requestCode == GET_FROM_GALLERY2 && resultCode == Activity.RESULT_OK) {
            selectedImage2 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage2);
                prop1.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (requestCode == GET_FROM_GALLERY3 && resultCode == Activity.RESULT_OK) {
            selectedImage3 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage3);
                prop2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (requestCode == GET_FROM_GALLERY4 && resultCode == Activity.RESULT_OK) {
            selectedImage4 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage4);
                prop3.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (requestCode == GET_FROM_GALLERY5 && resultCode == Activity.RESULT_OK) {
            selectedImage5 = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage5);
                prop4.setImageBitmap(bitmap);
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


            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY1);

        } else if(item.getItemId()==R.id.prop1) {


            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY2);
        }
        else if(item.getItemId()==R.id.prop2) {


            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY3);
        }
        else if
        (item.getItemId()==R.id.prop3) {


            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY4);
        }
        else if
        (item.getItemId()==R.id.prop4) {


            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY5);
        }
        else
        {
            return false;
        }
        return true;

    }

    private void attemptLogin() {


        // Reset errors.
        capacity.setError(null);
        contact.setError(null);
        email.setError(null);
        available.setError(null);
        tariff.setError(null);
        plot.setError(null);
        state.setError(null);
        street.setError(null);
        district.setError(null);
        pin.setError(null);
        locality.setError(null);
        nplace=nameplace.getText().toString();

        // Store values at the time of the login attempt.
        capacity1 = capacity.getText().toString();
        contact1 = contact.getText().toString();
        email1 = email.getText().toString();
        available1 = available.getText().toString();
        tariff1 = tariff.getText().toString();
        plot1 = plot.getText().toString();
        state1 = state.getText().toString();
        district1 = district.getText().toString();
        locality1 = locality.getText().toString();
        street1=street.getText().toString();
        pin1 = pin.getText().toString();
        locality1 = locality.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (capacity1.length() < 1) {
            capacity.setError("This Field cannot be blank");
            // focusView = vill;
            cancel = true;
        }
        if (nplace.length() < 1) {
            nameplace.setError("This Field cannot be blank");
            // focusView = vill;
            cancel = true;
        }

        // Check for a valid email address.
        if (contact1.length() < 10) {
            contact.setError("Check Phone Number");
            //focusView = contact;
            cancel = true;
        }
        if (TextUtils.isEmpty(available1)) if (TextUtils.isEmpty(available1)) {
            available.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(tariff1)) {
            tariff.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(plot1)) {
            plot.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(district1)) {
            district.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(street1)) {
            street.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(locality1)) {
            locality.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(pin1)) {
            pin.setError("This field is required");
            // focusView = rate;
            cancel = true;
        }
        if (TextUtils.isEmpty(state1)) {
            state.setError("This field is required");
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
            final String key = "VillageRelation/" + village1 + "/HostRelation/" + username + ".jpg";
            StorageReference riversRef = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("HostRelation").child(username);
                    DatabaseReference postsRef = myRef;                                       //


                    postsRef.child("contact").setValue(contact1);
                    postsRef.child("capacityEachRoom").setValue(capacity1);
                    postsRef.child("email").setValue(email1);

                    //
                    //Image
                    postsRef.child("address").child("village").setValue(village1);
                    postsRef.child("address").child("plotno").setValue(plot1);
                    postsRef.child("address").child("district").setValue(district1);
                    postsRef.child("address").child("locality").setValue(locality1);
                    postsRef.child("address").child("state").setValue(state1);
                    postsRef.child("address").child("street").setValue(street1);
                    postsRef.child("address").child("pincode").setValue(pin1);
                    postsRef.child("approved").setValue("NOT APPROVED");

                    postsRef.child("rating").setValue("0");
                    postsRef.child("nameOfplace").setValue(nplace);
                    postsRef.child("profilepic").setValue(key);
                    postsRef.child("price").setValue(tariff1);
                    postsRef.child("roomsAvailable").setValue(available1);
//                    Intent   i = new Intent(HostReg.this, Main5Activity.class);
//                    Toast.makeText(HostReg.this, "Success", Toast.LENGTH_SHORT).show();
//                    i.putExtra("username",username);
//                    startActivity(i,
//                            ActivityOptions.makeSceneTransitionAnimation(HostReg.this).toBundle());


                }
            });
            final String key2 = "VillageRelation/" + village1 + "/HostRelation/" + username + "/image1.jpg";
            StorageReference riversRef1 = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("HostRelation").child(username);
                    DatabaseReference postsRef = myRef;
                    postsRef.child("image1").setValue(key2);

                }
            });
            final String key3 = "VillageRelation/" + village1 + "/HostRelation/" + username + "/image2.jpg";
            StorageReference riversRef2 = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("HostRelation").child(username);
                    DatabaseReference postsRef = myRef;
                    postsRef.child("image2").setValue(key3);

                }
            });
            final String key4 = "VillageRelation/" + village1 + "/HostRelation/" + username + "/image3.jpg";
            StorageReference riversRef4 = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("HostRelation").child(username);
                    DatabaseReference postsRef = myRef;
                    postsRef.child("image3").setValue(key4);

                }
            });
            final String key5 = "VillageRelation/" + village1 + "HostRelation/" + username + "/image4.jpg";
            StorageReference riversRef5 = ref.child(key);
            riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("HostRelation").child(username);
                    DatabaseReference postsRef = myRef;
                    postsRef.child("image4").setValue(key5);
                    prdialog.dismiss();
                    Intent   i = new Intent(HostReg.this, Main5Activity.class);
                    Toast.makeText(HostReg.this, "Success", Toast.LENGTH_SHORT).show();
                    i.putExtra("username",username);
                    startActivity(i,
                            ActivityOptions.makeSceneTransitionAnimation(HostReg.this).toBundle());

                }
            });
        }
    }
}
