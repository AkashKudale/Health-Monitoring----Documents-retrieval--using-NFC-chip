package com.example.owner.nfcadminapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FinalEditRecord extends AppCompatActivity {


    EditText id, name, contact, address, email;
    Button register;

    public String nmS,mobS,adS,mailS;


    public static String receivedValue="",un;
    private ProgressDialog progress;
    final Context context=this;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    int cnt=0;
    public static int num=0;

    public static String uid, nm, mob, add, mail, pass, cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_edit_record);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<h3><font color='#ffffff'>Update User Info</font></h3>"));

        id = (EditText) findViewById(R.id.userIdE);
        name = (EditText) findViewById(R.id.userNameE);
        contact = (EditText) findViewById(R.id.userContactE);
        address = (EditText) findViewById(R.id.userAddressE);
        email = (EditText) findViewById(R.id.userEmailE);

        register = (Button) findViewById(R.id.addUserE);
        id.setEnabled(false);


        progress = new ProgressDialog(context);
        progress.setMessage("Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgress(0);
        progress.setCancelable(false);
        progress.show();

        new getAll().execute();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                uid = id.getText().toString();
                nm = name.getText().toString();
                mob = contact.getText().toString();



                add = address.getText().toString();
                mail = email.getText().toString();

                if (uid.equals("")) {
                    id.setError("Enter UID");
                    Snackbar.make(view, "Enter UID", Snackbar.LENGTH_LONG)
                            .show();
                } else if (nm.equals("")) {
                    name.setError("Enter Name");
                    Snackbar.make(view, "Enter Name", Snackbar.LENGTH_LONG)
                            .show();
                } else if (mob.equals("") || mob.length() < 10) {
                    contact.setError("Check Mobile no");
                    Snackbar.make(view, "Check Mobile no", Snackbar.LENGTH_LONG)
                            .show();
                } else if (add.equals("")) {
                    address.setError("Enter Address");
                    Snackbar.make(view, "Enter Address", Snackbar.LENGTH_LONG)
                            .show();
                } else if (mail.equals("") || !mail.matches("[a-zA-Z0-9.-_]+@[a-z]+\\.[a-z]+")) {
                    email.setError("Check Email Id");
                    Snackbar.make(view, "Check Email Id", Snackbar.LENGTH_LONG)
                            .show();
                } else {

                    progress = new ProgressDialog(context);
                    progress.setMessage("Wait...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);
                    progress.setProgress(0);
                    progress.setCancelable(false);
                    progress.show();

                   new updateUser().execute();


                }


            }
        });


    }

    /////////////////////////////////////////////////////////
    private class updateUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/updateUser.php");
            //temp=params[0];

            //uid,nm,mob,add,mail,pass,cpass;
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",uid));
            pairs.add(new BasicNameValuePair("e2",nm));
            pairs.add(new BasicNameValuePair("e3",mob));
            pairs.add(new BasicNameValuePair("e4",add));
            pairs.add(new BasicNameValuePair("e5",mail));

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
             //Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();
            if(receivedValue.contains("User"))
            {
                Toast.makeText(context, "User record Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
            else if(receivedValue.contains("Doctor"))
            {
                Toast.makeText(context, "Doctor Record Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
            else if(receivedValue.contains("Police"))
            {
                Toast.makeText(context, "Police Record Updated", Toast.LENGTH_SHORT).show();

                finish();
            }
            else if(receivedValue.contains("RTO"))
            {
                Toast.makeText(context, "RTO Record Updated", Toast.LENGTH_SHORT).show();
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







    /////////////////////////////////////////////////////////////////

    private class getAll extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/getData.php");

            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",NFCReadFragment.message));

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
            }
            catch (Exception ex)
            {
                // Toast.makeText(getApplicationContext(), "Error 2="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            showList();

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
    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(receivedValue);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                cnt++;
            }
if(cnt>0) {
    for (int i = 0; i < peoples.length(); i++) {
        JSONObject c = peoples.getJSONObject(i);
        nmS = c.getString("name");
        mobS = c.getString("mobile");
        adS = c.getString("address");
        mailS = c.getString("email");

    }


    id.setText(NFCReadFragment.message);
    id.setEnabled(false);
    name.setText(nmS);
    contact.setText(mobS);
    address.setText(adS);
    email.setText(mailS);


    progress.dismiss();

            /*for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);

            }*/
}else{
    progress.dismiss();
    Toast.makeText(context, "No record with this Id exists!", Toast.LENGTH_SHORT).show();
    finish();
}

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }








}