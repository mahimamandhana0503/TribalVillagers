package com.example.chinmay.tribe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main6Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
   private EditText name, contact, email, password,username,aadhar;
    private Button register;
    private FirebaseAuth mAuth;
    private Spinner spinner;
 private DatabaseReference myRef;
 private String email1,password1,name1,contact1,ad,username2,roleq;
 private String username3;
    private String[] subject;
    FirebaseDatabase database;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        database = FirebaseDatabase.getInstance();
         myRef = database.getReference().child("UserRelation");


        spinner=(Spinner)findViewById(R.id.role) ;
        subject=new String[]{"Tourist","ServiceProvider"};
       ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,subject);
       spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.name);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        username = (EditText) findViewById(R.id.username);
        aadhar = (EditText) findViewById(R.id.aadhar);
        register = (Button) findViewById(R.id.register);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptLogin();
            }

        });
    }

    private void attemptLogin() {


        // Reset errors.
        email.setError(null);
        password.setError(null);



        // Store values at the time of the login attempt.
       email1 = email.getText().toString();
         password1 = password.getText().toString();
         name1 = name.getText().toString();
         contact1 = contact.getText().toString();
         ad = aadhar.getText().toString();
         username2 = username.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password1) && !isPasswordValid(password1)) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }
        if ( ad.length()<12) {
            Toast.makeText(this,"Recheck Aadhar card no",Toast.LENGTH_LONG).show();
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email1)) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        } else if (!isEmailValid(email1)) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }
        if (TextUtils.isEmpty(contact1)) {
            contact.setError(getString(R.string.error_field_required));
          //  focusView = contact;
            cancel = true;
        }
        if (TextUtils.isEmpty(name1)) {
            name.setError(getString(R.string.error_field_required));
         //   focusView = name;
            cancel = true;
        }
        if (username2.length()<6) {
            username.setError("Username should be of more than 5 letters");
           // Toast.makeText(this,"Username should be of more than 5 letters",Toast.LENGTH_LONG).show();
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            //focusView.requestFocus();
        } else {
            Random rand = new Random();


            username3=username2;








            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(Main6Activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful()) {
                                Toast.makeText(Main6Activity.this, "Success!", Toast.LENGTH_LONG).show();
                                myRef= FirebaseDatabase.getInstance().getReference().child("UserRelation");
                                DatabaseReference postsRef = myRef.child(username3);

                                postsRef.child("aadhar").setValue(ad);
                                postsRef.child("contact").setValue(contact1);
                                postsRef.child("email").setValue(email1);
                                postsRef.child("name").setValue(name1);
                                postsRef.child("APPROVED").setValue("No");
                                postsRef.child("Role").setValue("Tourist");
                                postsRef.child("password").setValue(password1);



                                Intent   i = new Intent(Main6Activity.this, Main4Activity.class);
                                i.putExtra("aadhar",ad);
                                i.putExtra("contact",contact1);
                                i.putExtra("email",email1);
                                i.putExtra("name",name1);
                                i.putExtra("approved","no");
                                i.putExtra("role",roleq);
                                i.putExtra("username",username3);
                                i.putExtra("password",password1);
                                startActivity(i,
                                        ActivityOptions.makeSceneTransitionAnimation(Main6Activity.this).toBundle());
                                finish();
                            }
                            //Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            else {
                                Toast.makeText(Main6Activity.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    roleq=subject[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        roleq=subject[0];

    }
    public static class User {

       private String name;
       private String contact;
       private String email;
       private String password;
       private String username;
       private String aadhar;



        public User(String aadhar,String contact, String email,String name, String password) {
            // ...
        }

    }


    // mAuthTask = new UserLoginTask(email, password);
            // mAuthTask.execute((Void) null);
        }





