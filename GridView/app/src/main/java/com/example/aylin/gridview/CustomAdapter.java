package com.example.aylin.gridview;

/**
 * Created by aylin on 27.04.2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private final String[] sanatcilar;

    public CustomAdapter(Context context, String[] sanatcilar) {
        this.context = context;
        this.sanatcilar = sanatcilar;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // Elemanlarımız için kullanacağımız layoutu inflate edelim
            gridView = inflater.inflate(R.layout.item_layout, null);

            // TextView'ımızı setleyelim
            TextView textView = (TextView) gridView
                    .findViewById(R.id.textView1);
            textView.setText(sanatcilar[position]);


            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.imageView1);

            String sanatci = sanatcilar[position];
            //Textimize göre resmimizi ayarlayalım
            if (sanatci.equals("Müslüm Gürses")) {
                imageView.setImageResource(R.drawable.index);
            } else if (sanatci.equals("Şebnem Ferah")) {
                imageView.setImageResource(R.drawable.index2);
            } else if (sanatci.equals("Sezen Aksu")) {
                imageView.setImageResource(R.drawable.hqdefault);
            } else {
                imageView.setImageResource(R.drawable.index3);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return sanatcilar.length;
    }

    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

}