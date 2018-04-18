package com.example.chinmay.tribe;
 import android.app.ProgressDialog;
 import android.content.pm.PackageManager;
 import android.net.Uri;
 import android.support.v4.app.ActivityCompat;
 import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
 import android.widget.Button;
 import android.widget.TextView;
        import android.content.ActivityNotFoundException;
        import android.content.Intent;
        import android.speech.RecognizerIntent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

 import com.google.firebase.database.DataSnapshot;
 import com.google.firebase.database.DatabaseError;
 import com.google.firebase.database.DatabaseReference;
 import com.google.firebase.database.FirebaseDatabase;
 import com.google.firebase.database.ValueEventListener;

 import java.util.ArrayList;
 import java.util.Locale;

public class voice extends AppCompatActivity {
    private DatabaseReference myRef;
    private String village = "Bastar";
    private String aname, acontact;
    ProgressDialog prdialog;
    private TextView voiceInput;
    private TextView speakButton;
    private final int REQ_CODE_SPEECH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);



        voiceInput = (TextView) findViewById(R.id.voiceInput);









    }





    // Showing google speech input dialog

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi speak something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    voiceInput.setText(result.get(0));
                }
                break;
            }
        }
        if(voiceInput.getText().toString().equals("a"))
        {

        }
        if(voiceInput.getText().toString().equals("b"))
        {
            Intent i=new Intent(this,call.class);
            startActivity(i);
        }
        if(voiceInput.getText().toString().equals("c"))
        {

        }
        if(voiceInput.getText().toString().equals("a"))
        {

        }
        if(voiceInput.getText().toString().equals("d"))
        {

        }
        if(voiceInput.getText().toString().equals("f"))
        {

        }
        if(voiceInput.getText().toString().equals("g"))
        {

        }
    }
    public void wvoice(View view)
    {
        askSpeechInput();
    }
}
