package com.example.aylin.customdatatable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by aylin on 29.04.2016.
 */

public class listviewAdapter extends BaseAdapter {

    public ArrayList<Model> basvuruList;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<Model> basvuruList) {
        super();
        this.activity = activity;
        this.basvuruList = basvuruList;
    }

    @Override
    public int getCount() {
        return basvuruList.size();
    }

    @Override
    public Object getItem(int position) {
        return basvuruList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView id2;
        TextView call;
        TextView av;
        TextView sicil;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.id2 = (TextView) convertView.findViewById(R.id.id2);
            holder.call = (TextView) convertView.findViewById(R.id.call);
            holder.av = (TextView) convertView
                    .findViewById(R.id.av);
            holder.sicil = (TextView) convertView.findViewById(R.id.sicil);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Model item = basvuruList.get(position);
        holder.id2.setText(item.getsNo().toString());
        holder.call.setText(item.getProduct().toString());
        holder.av.setText(item.getCategory().toString());
        holder.sicil.setText(item.getPrice().toString());

        return convertView;
    }
}
