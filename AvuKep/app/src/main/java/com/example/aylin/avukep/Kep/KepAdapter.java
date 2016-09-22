package com.example.aylin.avukep.Kep;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aylin.avukep.Hatirlatici.takvimuygulamasi.HatirlaticiKur;
import com.example.aylin.avukep.R;

/**
 * Created by aylin on 29.06.2016.
 */

public class KepAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final String[] dateTime;
    private final String[] dateTime1;
    private final String[] Konu;
    public KepAdapter(Activity context,
                      String[] web, String[] datetime, String[] konu, String[] datetime1) {
        super(context, R.layout.kep_content, web);
        this.context = context;
        this.web = web;
        this.dateTime=datetime;
        this.Konu=konu;
        this.dateTime1=datetime1;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.kep_content, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView datetime=(TextView)rowView.findViewById(R.id.datetime);
        TextView konu=(TextView)rowView.findViewById(R.id.konu);
        TextView datetime1=(TextView)rowView.findViewById(R.id.datetime1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        datetime.setText(dateTime[position]);
        konu.setText(Konu[position]);
        datetime1.setText(dateTime1[position]);
        HatirlaticiKur hk=new HatirlaticiKur();
        return rowView;
    }
}