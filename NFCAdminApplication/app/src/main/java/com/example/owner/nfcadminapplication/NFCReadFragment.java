package com.example.owner.nfcadminapplication;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;

public class NFCReadFragment extends DialogFragment {

    public static final String TAG = NFCReadFragment.class.getSimpleName();
    public static String message;

    public static NFCReadFragment newInstance() {

        return new NFCReadFragment();
    }

    private TextView mTvMessage;
    private Listener mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        try {
     view = inflater.inflate(R.layout.fragment_read, container, false);
    initViews(view);

}catch (Exception e){
    Toast.makeText(getActivity(), "onCreate View => "+e.toString(), Toast.LENGTH_SHORT).show();
}
        return view;
    }

    private void initViews(View view) {

        mTvMessage = (TextView) view.findViewById(R.id.tv_message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (EditRecord)context;
        mListener.onDialogDisplayed();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener.onDialogDismissed();
    }

    public void onNfcDetected(Ndef ndef){

        readFromNFC(ndef);
    }

    private void readFromNFC(Ndef ndef) {

        try {
            ndef.connect();
            NdefMessage ndefMessage = ndef.getNdefMessage();
             message = new String(ndefMessage.getRecords()[0].getPayload());




            Log.d(TAG, "readFromNFC: "+message);
            mTvMessage.setText(message);
            if(EditRecord.isEdit==1){
                startActivity(new Intent(getActivity(),FinalEditRecord.class));
                dismiss();

            }



            ndef.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
