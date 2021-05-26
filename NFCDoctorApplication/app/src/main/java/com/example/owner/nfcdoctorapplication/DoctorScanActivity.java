package com.example.owner.nfcdoctorapplication;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
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
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DoctorScanActivity extends AppCompatActivity implements Listener{

    public static int isFlag;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    int cnt=0;

    ////////////////////////////////

    private ProgressDialog progress;
    final Context context=this;
    TextInputLayout idTextInputLayoutD;
    EditText idD;
    Button registerD;

    public static String uname,receivedValue="";
public static String title;

    //////////////////////////////////

    ///////////////////////

    private NFCReadFragmentScan mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;


    /////////////////////////////



    Button bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        try {
            bn = (Button) findViewById(R.id.scan);



           // new registerUser().execute();
            ////////////////////////////////////////////////////////////


            idTextInputLayoutD = (TextInputLayout) findViewById(R.id.idTextInputLayoutP);
            idD = (EditText) findViewById(R.id.idEditTextP);

            registerD = (Button) findViewById(R.id.submitUser);

            /////////////////////////////////////////////////////////////////

            bn.setOnClickListener(view -> showReadFragment());

            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        }catch(Exception e){
            Toast.makeText(context, "error => "+e.toString(), Toast.LENGTH_SHORT).show();
        }

            ////////////////////////////////////////////////////////////////
            registerD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (validate(idD, idTextInputLayoutD)) {
                        try {
                            {

                                uname = idD.getText().toString();


                                progress = new ProgressDialog(context);
                                progress.setMessage("Wait...");
                                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progress.setIndeterminate(false);
                                progress.setProgress(0);
                                progress.setCancelable(false);
                                progress.show();

                                new getAll().execute();


                            }

                        } catch (Exception ex) {
                            Toast.makeText(DoctorScanActivity.this, "e33==>" + ex.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            });




    }

    private boolean validate(EditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().length() > 0) {
            return true;
        }
        editText.requestFocus(); // set focus on fields
        textInputLayout.setError("Please Fill This.!!!"); // set error message
        return false;}





    private void showReadFragment() {
        isFlag=1;

        mNfcReadFragment = (NFCReadFragmentScan) getFragmentManager().findFragmentByTag(NFCReadFragmentScan.TAG);

        if (mNfcReadFragment == null) {

            mNfcReadFragment = NFCReadFragmentScan.newInstance();
        }
        mNfcReadFragment.show(getFragmentManager(),NFCReadFragmentScan.TAG);

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
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected,tagDetected,ndefDetected};

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        if(mNfcAdapter!= null)
            mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mNfcAdapter!= null)
            mNfcAdapter.disableForegroundDispatch(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        try {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            //  Log.d(TAG, "onNewIntent: "+intent.getAction());

            if (tag != null) {
              //  Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
                Ndef ndef = Ndef.get(tag);

                if (isDialogDisplayed) {


                    mNfcReadFragment = (NFCReadFragmentScan) getFragmentManager().findFragmentByTag(NFCReadFragmentScan.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
            }
        }catch (Exception e){
           // Toast.makeText(context, "new Intent => "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


///////////////////////////////////////////////////////////////////




    private class getAll extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/getPatient.php");
            //temp=params[0];
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",uname));

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
             Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();
           if(receivedValue.contains("Not"))
            {
                Toast.makeText(context, "Invalid Id Requested", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Patient Found Successfully", Toast.LENGTH_SHORT).show();

                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
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








    ////////////////////////////////////////////////////////////////////////////////





}
