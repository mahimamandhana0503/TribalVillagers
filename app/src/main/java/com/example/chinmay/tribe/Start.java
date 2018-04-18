package com.example.chinmay.tribe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


    }
    public void normal(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }
    public void easy(View view)
    {
        startActivity(new Intent(this,easy.class));
    }
}
