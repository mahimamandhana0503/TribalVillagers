package com.example.chinmay.tribe;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.firebase.database.FirebaseDatabase;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
    public void Next(View view)
    {
        Intent i=new Intent(this,Main4Activity.class);

        startActivity(i);
    }
}
