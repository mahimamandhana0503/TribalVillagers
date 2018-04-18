package com.example.chinmay.tribe;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;

public class GuideReg extends AppCompatActivity {
    private FirebaseDatabase database;
    private StorageReference mstorageref;
    private DatabaseReference myRef;
    private String rate1,aad1,contact1,village1,username;
    private Button b;
    private String key;
    private ProgressDialog prdialog;
    Uri selectedImage;
    private Uri downloadUrl2;
    String url;
    ImageView i1;
    private Bundle b1;
    private int flag=0;
    public static final int GET_FROM_GALLERY = 3;


    private EditText aad,rate,contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide_reg);
        b1=getIntent().getExtras();
        mstorageref= FirebaseStorage.getInstance().getReference();
        if(b1!=null)
        {

            username=b1.getString("username");
            village1=b1.getString("village") ;




        }

        b=(Button)findViewById(R.id.button2);                                       //
        aad=(EditText)findViewById(R.id.Aadhar);
        i1=(ImageView)findViewById(R.id.image);
        rate=(EditText)findViewById(R.id.Rate);
        contact=(EditText)findViewById(R.id.contact);
        database = FirebaseDatabase.getInstance();
        registerForContextMenu(i1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        myRef = database.getReference("UserRelation");
        aad.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        rate.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
       // menu.setHeaderTitle("Select a Role to Register");
        menu.add(0, v.getId(), 0, "Add Photo");//groupId, itemId, order, title



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
             selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
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
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Add Photo"){

            flag=1;
            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
        }
       else{
            return false;
        }
        return true;
    }
    private void attemptLogin() {


        // Reset errors.
        aad.setError(null);
        contact.setError(null);
        rate.setError(null);

        // Store values at the time of the login attempt.
        aad1 = aad.getText().toString();
        contact1 = contact.getText().toString();
        rate1 = rate.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (aad.getText().toString().length()<12) {
            aad.setError("Check aadhar no");
           // focusView = vill;
            cancel = true;
        }

        // Check for a valid email address.
        if (contact1.length()<10) {
            contact.setError("Check Phone Number");
            //focusView = contact;
            cancel = true;
        } else if (TextUtils.isEmpty(rate1)) {
            rate.setError("This field is required");
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
             key="VillageRelation/"+village1+"/GuideRelation/"+username+".jpg";
            StorageReference ref=FirebaseStorage.getInstance().getReference();
            StorageReference riversRef =ref.child("images/rivers.jpg");
            riversRef.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                     url=downloadUrl.toString();
                    myRef= FirebaseDatabase.getInstance().getReference().child("VillageRelation").child(village1).child("GuideRelation").child(username);
                    DatabaseReference postsRef = myRef;                                       //

                   // postsRef.child("approved").setValue("no");
                    postsRef.child("contact").setValue(contact1);
                    postsRef.child("rate").setValue(rate1);
                    postsRef.child("approved").setValue("NOT APPROVED");
                    //
                    //Image
                    postsRef.child("village").setValue(village1);
                    postsRef.child("rating").setValue("0");
                    postsRef.child("profilepic").setValue(key);
                    Intent   i = new Intent(GuideReg.this, Main5Activity.class);
                    Toast.makeText(GuideReg.this, "Success", Toast.LENGTH_SHORT).show();
                    prdialog.dismiss();
                    i.putExtra("username",username);
                    startActivity(i,
                            ActivityOptions.makeSceneTransitionAnimation(GuideReg.this).toBundle());
                }
            });


                                //Approved,Rating




//            i.putExtra("aadhar",ad);
//            i.putExtra("contact",contact1);
//            i.putExtra("email",email1);
//            i.putExtra("name",name1);
//            i.putExtra("approved","no");
//            i.putExtra("role",roleq);
//            i.putExtra("username",username3);
//            i.putExtra("password",password1);



            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.


            // mAuthTask.execute((Void) null);
        }
    }
}
