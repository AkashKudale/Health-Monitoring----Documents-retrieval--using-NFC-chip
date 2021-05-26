package com.example.owner.nfcuserapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

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

public class Prescriptions extends AppCompatActivity {
    ListView list;
    public static String title[],msg[],sender[],date[],tt1,msg1;

    public static String receivedValue="",un;
    private ProgressDialog progress;
    final Context context=this;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    int cnt=0;
    public static int num=0;
    SQLiteDatabase db;
    public static String imgname="",selectedDate="",selectedTime="";
    View view1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescriptions);

        list=(ListView)findViewById(R.id.list);


        progress = new ProgressDialog(context);
        progress.setMessage("Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgress(0);
        progress.setCancelable(false);
        progress.show();
        new getAll().execute();



    }




    private class getAll extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/get-prescription.php");
            //temp=params[0];
            List<NameValuePair> pairs=new ArrayList<NameValuePair>(1);
            pairs.add(new BasicNameValuePair("e1",Login.unameString));
            try
            {
                post.setEntity(new UrlEncodedFormEntity(pairs));
            }
            catch (Exception ex)
            {
                //  Toast.makeText(getApplicationContext(), "Error 1="+ex.toString(), Toast.LENGTH_SHORT).show();
            }
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
            title=new String[cnt];
           /* msg=new String[cnt];
            sender=new String[cnt];
            date=new String[cnt];*/

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                title[i] = c.getString("pres");
               /* msg[i] = c.getString("msg");
                sender[i] = c.getString("sender");
                date[i] = c.getString("date");*/
            }

            Adapter adapter=new Adapter(this,title);
            list.setAdapter(adapter);
            progress.dismiss();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
