package com.example.chinmay.tribe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
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

public class trader extends AppCompatActivity {
    private FirebaseDatabase database;
    private StorageReference mstorageref;
    private DatabaseReference myRef;
    private ProgressDialog prdialog;
    private Bundle b1;

private String url;
private Uri selectedImage1, selectedImage2, selectedImage3, selectedImage4, selectedImage5;
private String username, village1;

private EditText capacity,  available, tariff, street, plot, locality, district, pin, state;
private String capacity1, available1, tariff1, street1, plot1, locality1, district1, pin1, state1;

private  static final int GET_FROM_GALLERY1 = 1;
private  static final int GET_FROM_GALLERY2 = 2;
private  static final int GET_FROM_GALLERY3 = 3;
private  static final int GET_FROM_GALLERY4 = 4;
private  static final int GET_FROM_GALLERY5 = 5;

    private EditText category,owner,contact,email,shop;
     private String category1,owner1,contact1,email1,shop1;
    private TextView tlatitude,tlongitude;
    private String lat,long1;
    int flag=0;
    private String fl="0";
    private ImageView profile,i1,i2,i3,i4;
    private Button b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trader);
        tlatitude=(TextView)findViewById(R.id.latitude);
        tlongitude=(TextView)findViewById(R.id.longitude);
        shop=(EditText)findViewById(R.id.shopname);
        b=(Button)findViewById(R.id.button2);
        category=(EditText)findViewById(R.id.category);
        owner=(EditText)findViewById(R.id.ownerName);
        contact=(EditText)findViewById(R.id.contact);
        email=(EditText)findViewById(R.id.email);

        profile=(ImageView)findViewById(R.id.image);
        i1=(ImageView)findViewById(R.id.prop1);
        i2=(ImageView)findViewById(R.id.prop2);
        i3=(ImageView)findViewById(R.id.prop3);
        i4=(ImageView)findViewById(R.id.prop4);

        b1 = getIntent().getExtras();
        mstorageref = FirebaseStorage.getInstance().getReference();
        if (b1 != null) {

            username = b1.getString("username");
            village1 = b1.getString("village");
            lat=b1.getString("latitude");
            long1=b1.getString("longitude");
            fl=b1.getString("flag");



        }
        if(fl!=null) {
            flag = Integer.parseInt(fl);
            if (flag == 1) {
                tlatitude.setText(lat);
                tlongitude.setText(long1);
            }
        }

        if(savedInstanceState!=null)
        {
            onRestoreInstanceState(savedInstanceState);
           // profile.setImageBitmap(savedInstanceState.getParc("profile"));
        }


          database = FirebaseDatabase.getInstance();
                registerForContextMenu(i1);
                registerForContextMenu(i2);
                registerForContextMenu(i3);
                registerForContextMenu(i4);
                registerForContextMenu(profile);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

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
                profile.setImageBitmap(bitmap);
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
                i1.setImageBitmap(bitmap);
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
                i2.setImageBitmap(bitmap);
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
               i3.setImageBitmap(bitmap);
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
                i4.setImageBitmap(bitmap);
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

private void attemptLogin()
{




    category.setError(null);
    email.setError(null);
    category1=category.getText().toString();
    email1=email.getText().toString();
    owner1=owner.getText().toString();
    contact1=contact.getText().toString();
    shop1=shop.getText().toString();

    boolean cancel = false;
    View focusView = null;

    // Check for a valid password, if the user entered one.
    if (category1.length() < 1) {
        category.setError("This Field cannot be blank");
        // focusView = vill;
        cancel = true;
    }

    // Check for a valid email address.
    if (contact1.length() < 10) {
        contact.setError("Check Phone Number");
        //focusView = contact;
        cancel = true;
    }
    if (TextUtils.isEmpty(owner1))  {
        owner.setError("This field is required");
        // focusView = rate;
        cancel = true;
    }
    if (TextUtils.isEmpty(email1)) {
        email.setError("This field is required");
        // focusView = rate;
        cancel = true;
    }

    if (TextUtils.isEmpty(shop1)) {
        shop.setError("This field is required");
        // focusView = rate;
        cancel = true;
    }
    if(flag==0)
    {
        Toast.makeText(this, "Get Location", Toast.LENGTH_SHORT).show();
        cancel=true;
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
        final String key = "VillageRelation/" + village1 + "/TraderRelation/" + username + ".jpg";
        StorageReference riversRef = ref.child(key);
        riversRef.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("TraderRelation").child(shop1);
                DatabaseReference postsRef = myRef;                                       //


                postsRef.child("contact").setValue(contact1);
                postsRef.child("ownername").setValue(owner1);
                postsRef.child("email").setValue(email1);
                postsRef.child("approved").setValue("NOT APPROVED");
                postsRef.child("category").setValue(category1);
                postsRef.child("rating").setValue("3");
                postsRef.child("village").setValue(village1);
                postsRef.child("shopLat").setValue(lat);
                postsRef.child("shopLong").setValue(long1);
                postsRef.child("image").setValue(key);
                Toast.makeText(trader.this, "pro", Toast.LENGTH_SHORT).show();



            }
        });

         ref = FirebaseStorage.getInstance().getReference();
        final String key1 = "VillageRelation/" + village1 + "/TraderRelation/galleryImage1.jpg";
         riversRef = ref.child(key1);
        riversRef.putFile(selectedImage2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("TraderRelation").child(shop1);
                DatabaseReference postsRef = myRef;                                       //


                postsRef.child("galleryImage1").setValue(key1);
                Toast.makeText(trader.this, "1", Toast.LENGTH_SHORT).show();



            }
        });
        ref = FirebaseStorage.getInstance().getReference();
        final String key2 = "VillageRelation/" + village1 + "/TraderRelation/galleryImage2.jpg";
        riversRef = ref.child(key2);
        riversRef.putFile(selectedImage3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("TraderRelation").child(shop1);
                DatabaseReference postsRef = myRef;                                       //


                postsRef.child("galleryImage2").setValue(key2);
                Toast.makeText(trader.this, "2", Toast.LENGTH_SHORT).show();



            }
        });
        ref = FirebaseStorage.getInstance().getReference();
        final String key3 = "VillageRelation/" + village1 + "/TraderRelation/galleryImage3.jpg";
        riversRef = ref.child(key3);
        riversRef.putFile(selectedImage4).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("TraderRelation").child(shop1);
                DatabaseReference postsRef = myRef;                                       //


                postsRef.child("galleryImage3").setValue(key3);
                Toast.makeText(trader.this, "pro", Toast.LENGTH_SHORT).show();



            }
        });
        ref = FirebaseStorage.getInstance().getReference();
        final String key4 = "VillageRelation/" + village1 + "/TraderRelation/galleryImage4.jpg";
        riversRef = ref.child(key4);
        riversRef.putFile(selectedImage5).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();

                myRef = FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("TraderRelation").child(shop1);
                DatabaseReference postsRef = myRef;                                       //
                postsRef.child("galleryImage4").setValue(key4);
                prdialog.dismiss();
                Toast.makeText(trader.this, "4", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(trader.this,Main5Activity.class);
                i.putExtra("username",username);
                startActivity(i);



            }
        });




            }


}






    public void loc(View view)
    {


        Intent i9=new Intent(this,MapsActivity.class);
        i9.putExtra("village",village1);
        i9.putExtra("username",username);
        startActivity(i9);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {


        super.onSaveInstanceState(outState);

        Toast.makeText(this, "onSaveInstanceState()", Toast.LENGTH_LONG).show();

        outState.putString("village", village1);
        outState.putString("shopname", shop.getText().toString());
        outState.putString("category", category.getText().toString());
        outState.putString("email", email.getText().toString());
        outState.putString("username", username);
        outState.putString("contact", contact.getText().toString());
        outState.putString("ownername", owner.getText().toString());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Toast.makeText(this, "onRestoreInstanceState()", Toast.LENGTH_LONG).show();

        super.onRestoreInstanceState(savedInstanceState);
        village1=savedInstanceState.getString("village");
        username=savedInstanceState.getString("username");

        shop1=savedInstanceState.getString("shopname");
        shop.setText(shop1);
        category1=savedInstanceState.getString("category");
        category.setText(category1);
        email1=savedInstanceState.getString("email");
        email.setText(email1);
        contact.setText(savedInstanceState.getString("contact"));
        owner.setText(savedInstanceState.getString("ownername"));

    }
//        BitmapDrawable drawable = (BitmapDrawable) profile.getDrawable();
//        Bitmap bitmap = drawable.getBitmap();
//
//        outState.putParcelable("profile",bitmap);
//         drawable = (BitmapDrawable) i1.getDrawable();
//         bitmap = drawable.getBitmap();
//
//        outState.putParcelable("i1",bitmap);
//        drawable = (BitmapDrawable) i2.getDrawable();
//        bitmap = drawable.getBitmap();
//
//        outState.putParcelable("i2",bitmap);
//        drawable = (BitmapDrawable) i3.getDrawable();
//        bitmap = drawable.getBitmap();
//
//        outState.putParcelable("i3",bitmap);
//        drawable = (BitmapDrawable) i4.getDrawable();
//        bitmap = drawable.getBitmap();
//
//        outState.putParcelable("i4",bitmap);
//        // Save away the original text, so we still have it if the activity
//        // needs to be killed while paused.
//
//    }
}
