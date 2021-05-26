package com.example.owner.nfcadminapplication;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditRecord extends AppCompatActivity implements Listener{


    EditText ide;
    Button upe;
    public static int isEdit=0;
    Button mBtRead;

    private NFCReadFragment mNfcReadFragment;

    private boolean isDialogDisplayed = false;
    private boolean isWrite = false;

    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

/////////////////////////////////

        ide=(EditText)findViewById(R.id.idE);
        upe=(Button)findViewById(R.id.buttonE);

        upe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NFCReadFragment.message=ide.getText().toString();
                startActivity(new Intent(getApplicationContext(),FinalEditRecord.class));
                finish();
            }
        });


        ////////////////////////////////////

        mBtRead = (Button) findViewById(R.id.btn_readE);

        mBtRead.setOnClickListener(view -> showReadFragment());

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }


    private void showReadFragment() {
        try {
            isEdit = 1;

            mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);

            if (mNfcReadFragment == null) {

                mNfcReadFragment = NFCReadFragment.newInstance();
            }
            mNfcReadFragment.show(getFragmentManager(), NFCReadFragment.TAG);
        }catch(Exception e){
            Toast.makeText(this, "show fragment => "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogDisplayed() {
        try {

            isDialogDisplayed = true;
        }catch (Exception e){
            Toast.makeText(this, "displayed "+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDialogDismissed() {

        isDialogDisplayed = false;
        isWrite = false;
    }

    @Override
    protected void onResume() {
        try {
            super.onResume();
            IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
            IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
            IntentFilter techDetected = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
            IntentFilter[] nfcIntentFilter = new IntentFilter[]{techDetected, tagDetected, ndefDetected};

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
            if (mNfcAdapter != null)
                mNfcAdapter.enableForegroundDispatch(this, pendingIntent, nfcIntentFilter, null);
        }catch(Exception e){
            Toast.makeText(this, "resume => "+e.toString(), Toast.LENGTH_SHORT).show();
        }

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


                    mNfcReadFragment = (NFCReadFragment) getFragmentManager().findFragmentByTag(NFCReadFragment.TAG);
                    mNfcReadFragment.onNfcDetected(ndef);
                }
           }
        }catch(Exception e){
           // Toast.makeText(this, "new intent => "+e.toString(), Toast.LENGTH_SHORT).show();
        }
        }
    }



