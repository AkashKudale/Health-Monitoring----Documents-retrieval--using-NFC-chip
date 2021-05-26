package com.example.owner.nfcdoctorapplication;

/**
 * Created by PHN Technology on 23-03-2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] title;


    public Adapter(Activity context, String[] title) {
        super(context, R.layout.layout_adapter, title);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.title=title;

    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.layout_adapter, null,true);

        TextView t = (TextView) rowView.findViewById(R.id.title);


        t.setText(title[position]);


        return rowView;

    };

}
