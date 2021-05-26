package com.example.owner.nfcuserapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (checkInternet.isNetworkAvaliable(getApplicationContext()) == false) {
            Toast.makeText(this, "Check Internet Connection", Toast.LENGTH_SHORT).show();

        } else {


            db = openOrCreateDatabase("notice", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists register (Username varchar(255))");


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
            /* Create an Intent that will start the Menu-Activity. */

                    Cursor c = db.rawQuery("select * from register", null);
                    if (c.moveToFirst()) {
                        try {
                            Thread.sleep(2000);

                        } catch (Exception e) {

                        }

                        Intent mainIntent = new Intent(FirstActivity.this, Login.class);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Intent mainIntent = new Intent(FirstActivity.this, Login.class);
                        startActivity(mainIntent);
                        finish();
                    }


                }
            }, 2000);


        }
    }
}
