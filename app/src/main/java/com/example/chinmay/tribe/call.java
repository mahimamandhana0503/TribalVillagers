package com.example.chinmay.tribe;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.chinmay.tribe.R.id.contact;
import static com.example.chinmay.tribe.R.id.email;

public class call extends AppCompatActivity {
    private DatabaseReference myRef;
    private String village = "Bastar";
    private String aname, acontact;
    ProgressDialog prdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        prdialog = new ProgressDialog(this);
        prdialog.setTitle("Loading");
        prdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        prdialog.setCancelable(false);
        prdialog.show();

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot myitem : dataSnapshot.getChildren())

                {
                    if (myitem.getKey().equals("AdminRelation")) {
                        for (DataSnapshot myitem2 : myitem.getChildren()) {
                            if ((myitem2.child("villageassg").getValue().toString().equals(village))) {

                                aname = myitem2.child("name").getValue().toString();
                                acontact = myitem2.child("contact").getValue().toString();
                            }
                        }
                    }
                }
                prdialog.dismiss();
                String uri = "tel:" + acontact;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                if (ActivityCompat.checkSelfPermission(call.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);



    }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

}
}
