package com.example.owner.nfcuserapplication;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TextToSpeechMy extends AppCompatActivity {
    TextToSpeech t1;
    Button bn;
    EditText question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                t1.setSpeechRate(0.8f);

            }
        });


bn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        t1.speak(question.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);

    }
});



    }
}
