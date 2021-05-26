package com.example.owner.nfcdoctorapplication;

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
import android.support.design.widget.Snackbar;
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

public class Login extends AppCompatActivity implements Listener{

    EditText uname,pwd;
    Button login,reg;
    public static String unameString,pwdString;
    SQLiteDatabase db;
    public static int loginflag=0;
    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";
    //View view1;


    ///////////////////////

    private NFCReadFragment mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;


    /////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db=openOrCreateDatabase("notice", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists register (Username varchar(255))");
loginflag=0;
        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        reg=(Button)findViewById(R.id.register);
/////////////////////////////////////////////

        reg.setOnClickListener(view -> showReadFragment());

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

////////////////////////////////////////////

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unameString=uname.getText().toString();
                pwdString=pwd.getText().toString();
                if(unameString.equals(""))
                {
                    Snackbar.make(view, "Enter Username", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else  if(pwdString.equals(""))
                {
                    Snackbar.make(view, "Enter Password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
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
    //////////////////////////////////////////////////////////////


    private void showReadFragment() {
        try {
            loginflag = 1;

            mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);

            if (mNfcReadFragment == null) {

                mNfcReadFragment = NFCReadFragment.newInstance();
            }
            mNfcReadFragment.show(getFragmentManager(), NFCReadFragment.TAG);
        }catch(Exception e){
            Toast.makeText(context, "show read fragment => "+e.toString(), Toast.LENGTH_SHORT).show();
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
               // Toast.makeText(this, getString(R.string.message_tag_detected), Toast.LENGTH_SHORT).show();
                Ndef ndef = Ndef.get(tag);

                if (isDialogDisplayed) {


                    mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
            }
        }catch (Exception e){


        }
    }


///////////////////////////////////////////////////////////////////
    private class registerUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/logindoctor.php");
            //temp=params[0];
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",unameString));
            pairs.add(new BasicNameValuePair("e2",pwdString));

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
                Toast.makeText(context, "Invalid Authentication", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show();
                db.execSQL("delete from register");
                db.execSQL("insert into register values('"+unameString+"')");
                Intent i=new Intent(getApplicationContext(),DoctorScanActivity.class);
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
}
