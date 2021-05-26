package com.example.owner.nfcuserapplication;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ScanOtherUser extends AppCompatActivity implements Listener{

    EditText et;
    Button scan,get;
    public static String requestedId="";

    SQLiteDatabase db;
    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";

    ////////////////////////////////////////////////////////
    public static int isEdit=0;
    Button mBtRead;

    private NFCReadFragmentOther mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try
        {
            setContentView(R.layout.activity_scan_other_user);
        et=(EditText)findViewById(R.id.user_id);
        scan=(Button)findViewById(R.id.scan_btn);
        get=(Button)findViewById(R.id.try_btn);
        scan.setOnClickListener(view -> showReadFragment());

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);



        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestedId=et.getText().toString();
                if(requestedId.equals("")){
                    et.setError("Please fill this!");
                }else{
                    progress=new ProgressDialog(context);
                    progress.setMessage("Wait...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);
                    progress.setProgress(0);
                    progress.setCancelable(false);
                    progress.show();
                    new registerUser().execute();



                }
            }
        });
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error 3="+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /////////////////////////////////////////////////////////

    ////////////////////////////////////////////////

    private void showReadFragment() {
        try
        {
        isEdit=1;

        mNfcReadFragment = (NFCReadFragmentOther) getFragmentManager().findFragmentByTag(NFCReadFragmentOther.TAG);

        if (mNfcReadFragment == null) {

            mNfcReadFragment = NFCReadFragmentOther.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(),NFCReadFragmentOther.TAG);
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error 2="+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogDisplayed() {

        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {

        isDialogDisplayed = false;
        isWrite = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
            IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected, tagDetected, ndefDetected};

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            if (mNfcAdapter != null)
                mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try
        {
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error 6="+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        try {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            //  Log.d(TAG, "onNewIntent: "+intent.getAction());

            if (tag != null) {
                //Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
                Ndef ndef = Ndef.get(tag);

                if (isDialogDisplayed) {


                    mNfcReadFragment = (NFCReadFragmentOther) getFragmentManager().findFragmentByTag(NFCReadFragmentOther.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(context, "Error 4="+ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /////////////////////////////////////////////////////////////////////
    private class registerUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/getOtherUser.php");
            //temp=params[0];
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",requestedId));

            try
            {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            }
            catch (Exception ex)
            {
                // e1=ex.toString();
                //  Toast.makeText(getApplicationContext(), "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            //Perform HTTP Request
            try
            {
                ResponseHandler<String> responseHandler=new BasicResponseHandler();
                receivedValue =client.execute(post,responseHandler);
                // Toast.makeText(getApplicationContext(), receivedValue, Toast.LENGTH_SHORT).show();
                //name.setText(receivedValue);
            }
            catch (Exception ex)
            {
                // e2=ex.toString();
                // Toast.makeText(getApplicationContext(), "Error 2="+ex.toString(), Toast.LENGTH_SHORT).show();
            }

            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progress.dismiss();

            // Toast.makeText(context, "E1="+e1, Toast.LENGTH_SHORT).show();
            //  Toast.makeText(context, "E2="+e2, Toast.LENGTH_SHORT).show();
            // Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();
            if(receivedValue.contains("wro"))
            {
                Toast.makeText(context, "Invalid Id", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Found Successfully", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getApplicationContext(),OtherUserActivity.class);
                startActivity(i);
                finish();
            }
        }

        @Override
        protected void onPreExecute()
        {


            super.onPreExecute();

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }



    /////////////////////////////////////////////////////////
}
