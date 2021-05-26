package com.example.owner.nfcuserapplication;

import android.app.ProgressDialog;
import android.content.Context;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherUserActivity extends AppCompatActivity {
    private ProgressDialog progress;
    final Context context=this;
    private String receivedValue="";
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    int cnt=0;
    public static int num=0;

    //////////////////////
    EditText id, name, contact, address, email;
    Button register;

    public String nmS,mobS,adS,mailS;


    ////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);

        id = (EditText) findViewById(R.id.userIdO);
        name = (EditText) findViewById(R.id.userNameO);
        contact = (EditText) findViewById(R.id.userContactO);
        address = (EditText) findViewById(R.id.userAddressO);
        email = (EditText) findViewById(R.id.userEmailO);

        register = (Button) findViewById(R.id.addUserO);
        id.setEnabled(false);
        name.setEnabled(false);
        contact.setEnabled(false);
        address.setEnabled(false);
        email.setEnabled(false);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });





        progress=new ProgressDialog(context);
        progress.setMessage("Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgress(0);
        progress.setCancelable(false);
        progress.show();
        new checkUser().execute();
    }
    
    ///////////////////////////////////////////////////////////




    private class checkUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/getData.php");

            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",ScanOtherUser.requestedId));

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


                id.setText(Login.unameString);
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



    ///////////////////////////////////////////////////////
}
