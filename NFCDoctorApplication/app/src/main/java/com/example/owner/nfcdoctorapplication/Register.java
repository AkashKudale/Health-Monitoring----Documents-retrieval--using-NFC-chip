package com.example.owner.nfcdoctorapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

public class Register extends AppCompatActivity {

    EditText uname,pwd,cpwd;
    Button register;
    String unameString,pwdString,cpwdString;

    private ProgressDialog progress;
    final Context context=this;
    public static String receivedValue="";

    //Local Database
    SQLiteDatabase db;
    View view1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=openOrCreateDatabase("notice", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists register (Username varchar(255))");

        uname=(EditText)findViewById(R.id.username);
        pwd=(EditText)findViewById(R.id.password);
        cpwd=(EditText)findViewById(R.id.cpassword);
        register=(Button)findViewById(R.id.register);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unameString=uname.getText().toString();
                pwdString=pwd.getText().toString();
                cpwdString=cpwd.getText().toString();

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
                else  if(cpwdString.equals(""))
                {
                    Snackbar.make(view, "Enter Confirm Password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if(pwdString.equals(cpwdString))
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
                else
                {
                    Snackbar.make(view, "Incorrect Confirm Password", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private class registerUser extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpClient client=new DefaultHttpClient();
            HttpPost post=new HttpPost(url.url+"/register.php");
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
            if(receivedValue.contains("exists"))
            {
                Toast.makeText(context, "Username Already Exists", Toast.LENGTH_SHORT).show();
            }
            else if(receivedValue.contains("success"))
            {
                Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();
                db.execSQL("insert into register values('"+unameString+"')");
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
