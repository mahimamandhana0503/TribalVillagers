package com.example.chinmay.tribe;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class easy extends AppCompatActivity {
    private String village="Guwahati";

    private GpsTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
    }
    public void call(View view)
    {
        Intent i=new Intent(this,call.class);
        startActivity(i);
    }
    public void camera(View view)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);

    }
    public void video(View view)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }
    public void hotspot(View view)
    {
        gpsTracker = new GpsTracker(easy.this);
        if(gpsTracker.canGetLocation())
        {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            DatabaseReference myRef= FirebaseDatabase.getInstance().getReference();
            DatabaseReference r=myRef.child("VillageRelation").child(village).child("Anonymous").child("Node"+ Calendar.getInstance().getTimeInMillis());
            r.child("latitude").setValue(""+latitude);
            r.child("longitude").setValue(""+longitude);
            Toast.makeText(this,"Location saved",Toast.LENGTH_LONG).show();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }
    public void voice(View view)
    {
        Intent i=new Intent(this,voice.class);
        startActivity(i);
    }

}
