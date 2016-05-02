package com.example.aylin.menulogin;

/**
 * Created by aylin on 30.04.2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by aylin on 30.04.2016.
 */
public class ListAdapter  extends ArrayAdapter<String>{
    int vg;
    String[] items_list;
    Context context;
    public  ListAdapter(Context context, int vg, int id, String[] items_list){
        super(context, vg, id, items_list);
        this.context=context;
        this.items_list=items_list;
        this.vg=vg;
    }
    static class ViewHolder{
        public TextView txtModel;
        public TextView txtPrice;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView=convertView;
        if (rowView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(vg, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.txtModel = (TextView) rowView.findViewById(R.id.txtmodel);
            holder.txtPrice = (TextView) rowView.findViewById(R.id.txtprice);
            rowView.setTag(holder);
        }
        String[] items=items_list;
        ViewHolder holder=(ViewHolder)rowView.getTag();
        holder.txtModel.setText(items[0]);
        holder.txtPrice.setText(items[1]);
        return rowView;
    }
}

