package com.example.aylin.d_av_a;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
/**
 * Created by aylin on 30.04.2016.
 */
public class ListAdapter extends ArrayAdapter<String> {
    int vg;
    String[] items_list;
    Context context;
    public ListAdapter(Context context, int vg, int id, String[] items_list){
        super(context, vg, id, items_list);
        this.context=context;
        this.items_list=items_list;
        this.vg=vg;
    }
    static class ViewHolder{
        public TextView avTxt;
        public TextView sicilTxt;
        public TextView telTxt;
        public TextView tcTxt;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        View rowView=convertView;
        if (rowView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(vg, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.avTxt = (TextView) rowView.findViewById(R.id.avTxt);
            holder.sicilTxt = (TextView) rowView.findViewById(R.id.sicilTxt);
            holder.telTxt = (TextView) rowView.findViewById(R.id.telTxt);
            holder.tcTxt = (TextView) rowView.findViewById(R.id.tcTxt);
            rowView.setTag(holder);
        }
        String[] items=items_list;
        ViewHolder holder=(ViewHolder)rowView.getTag();
        holder.avTxt.setText(items[2]);
        holder.sicilTxt.setText(items[3]);
        holder.telTxt.setText(items[4]);
        holder.tcTxt.setText(items[1]);
        System.out.println("itemss parça: "+ items_list);
        return rowView;
    }
}