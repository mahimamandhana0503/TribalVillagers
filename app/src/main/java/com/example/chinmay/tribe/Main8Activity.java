package com.example.chinmay.tribe;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Main8Activity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String email,username,village1,description,rating,specialty,history;
    private descs i;
    private TextView t1,t2,t3,t4,t5,t6,t8;
    private ImageView i1;
    String abc="";
    private Bundle b;
    private String culture;
    StorageReference mstorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main8);
        t1=(TextView)findViewById(R.id.vname);
        i1=(ImageView)findViewById(R.id.image);
        t8=(TextView)findViewById(R.id.textViewOptions);

        t2=(TextView)findViewById(R.id.desc);
    //    t3=(TextView)findViewById(R.id.ratin);
        t4=(TextView)findViewById(R.id.culturedes);
        t5=(TextView)findViewById(R.id.historydes);
        t6=(TextView)findViewById(R.id.specialitydes);



         b=getIntent().getExtras();

        if(b!=null)
        {
            email=b.getString("email");
            username=b.getString("username");
            village1=b.getString("village") ;
            description=b.getString("description");
            rating=b.getString("rating");
            history=b.getString("history");
            culture=b.getString("Culture");
            specialty=b.getString("speciality");
            abc=b.getString("abc");



        }
        mstorage= FirebaseStorage.getInstance().getReference().child(abc);
        Glide.with(Main8Activity.this).using(new FirebaseImageLoader()).load(mstorage).into(i1);
        t1.setText(village1);
        t2.setText(description);
       // t3.setText(rating);
        t4.setText(culture);
        t5.setText(history);
        t6.setText(specialty);
     //   Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        database = FirebaseDatabase.getInstance();
     //   myRef = database.getReference("UserRelation").child(email);


        // setting Toolbar as Action Bar for the App
//        setSupportActionBar(mToolbar);
//        ActionBar actionBar=getSupportActionBar();
//
//        actionBar.setDisplayUseLogoEnabled(true);
//        actionBar.setLogo(R.drawable.ic_person_black_24dp);
//        actionBar.setTitle(username);
    }
    public void Pop(View view)
    {

        PopupMenu popup = new PopupMenu(Main8Activity.this,t8 );
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getTitle().equals("Register as Tourist Guide")) {
                  //  Toast.makeText(Main8Activity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Main8Activity.this,GuideReg.class);

                    i.putExtra("village",village1);
                    i.putExtra("username",username);


                    startActivity(i);
                    return true;
                }
                else  if(item.getTitle().equals("Register as home Owner")) {
                    Intent i=new Intent(Main8Activity.this,HostReg.class);
                    i.putExtra("village",village1);
                    i.putExtra("username",username);
                    startActivity(i);
                    Toast.makeText(Main8Activity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                    return true;
                }
                else  if(item.getTitle().equals("Register as Trader")) {
                    Intent i=new Intent(Main8Activity.this,trader.class);
                    i.putExtra("village",village1);
                    i.putExtra("username",username);
                    startActivity(i);

                    return true;
                }
                else {return false;}
            }
        });

        popup.show();//showing popup menu
    }



    }



