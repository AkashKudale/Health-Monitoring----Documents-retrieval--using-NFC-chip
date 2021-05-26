package com.example.owner.nfcadminapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class MainActivity extends AppCompatActivity {

    EditText un,ps;
    Button login,forget;

    public static String unameString,pwdString;

    SQLiteDatabase db;
    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";
    //View view1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        un=(EditText)findViewById(R.id.Username);
        ps=(EditText)findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        forget=(Button)findViewById(R.id.forget_pass);


        db=openOrCreateDatabase("notice", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists register (Username varchar(255))");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                unameString=un.getText().toString();
                pwdString=ps.getText().toString();

                if (unameString.equals("")){
                    un.setError("Enter Username");
                }else if(pwdString.equals("")){
                    ps.setError("Enter Password");
                }else{

                    progress=new ProgressDialog(context);
                    progress.setMessage("Wait...");
                    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progress.setIndeterminate(false);
                    progress.setProgress(0);
                    progress.setCancelable(false);
                    progress.show();
                    new login().execute();
                }


            }
        });

    }


    private class login extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/login.php");
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
             Toast.makeText(context, receivedValue, Toast.LENGTH_SHORT).show();
            if(receivedValue.contains("wro"))
            {
                Toast.makeText(context, "Invalid Authentication", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show();
                db.execSQL("delete from register");
                db.execSQL("insert into register values('"+unameString+"')");
                Intent i=new Intent(getApplicationContext(),AdminActivity.class);
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
