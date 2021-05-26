package com.example.owner.nfcuserapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reports extends AppCompatActivity {

    public static  String PDF_FETCH_URL = url.url+"/getPdfs.php";
    ListView listView;
    //Progress bar to check the progress of obtaining pdfs
    ProgressDialog progressDialog;

    //an array to hold the different pdf objects
    ArrayList<Pdf> pdfList = new ArrayList<Pdf>();

    //pdf adapter

    PdfAdapter pdfAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        listView = (ListView) findViewById(R.id.listView);

        if(UserActivity.cnt==1)
        {
            PDF_FETCH_URL = url.url+"/getPdfs.php";
        }
        else if(UserActivity.cnt==2)
        {
            PDF_FETCH_URL = url.url+"/getPdfsrto.php";
        }
        else if(UserActivity.cnt==3)
        {
            PDF_FETCH_URL = url.url+"/getPdfspolice.php";
        }
        progressDialog = new ProgressDialog(this);
getPdfs();

        //setting listView on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Pdf pdf = (Pdf) parent.getItemAtPosition(position);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(pdf.getUrl()));
                startActivity(intent);

            }
        });

    }

    private void getPdfs() {

        progressDialog.setMessage("Fetching Pdfs... Please Wait");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PDF_FETCH_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        pdfList.clear();
                        try {
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(Reports.this, obj.getString("message"), Toast.LENGTH_SHORT).show();
                            JSONArray jsonArray = obj.getJSONArray("pdfs");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                //Declaring a json object corresponding to every pdf object in our json Array
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                //Declaring a Pdf object to add it to the ArrayList  pdfList
                                Pdf pdf = new Pdf();
                                String pdfName = jsonObject.getString("name");
                                String pdfUrl = jsonObject.getString("url");
                                String ui=jsonObject.getString("uid");
                                if(ui.equals(Login.unameString)) {
                                    pdf.setName(pdfName);
                                    pdf.setUrl(pdfUrl);
                                    pdfList.add(pdf);
                                }
                            }
                            pdfAdapter = new PdfAdapter(Reports.this, R.layout.list_layout, pdfList);
                            listView.setAdapter(pdfAdapter);
                            pdfAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        RequestQueue request = Volley.newRequestQueue(this);
        request.add(stringRequest);
    }

}
