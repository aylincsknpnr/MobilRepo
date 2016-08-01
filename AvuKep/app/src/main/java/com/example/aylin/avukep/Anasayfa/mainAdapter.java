package com.example.aylin.avukep.Anasayfa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aylin.avukep.Hatirlatici.takvimuygulamasi.HatirlaticiKur;
import com.example.aylin.avukep.R;

/**
 * Created by aylin on 29.07.2016.
 */
public class mainAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] time;
    private final String[] notes;
    public mainAdapter(Activity context,
                        String[] time, String[] notes) {
        super(context, R.layout.mainpage_content, notes);
        this.context = context;
        this.time=time;
        this.notes=notes;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mainpage_content, null, true);
        TextView timem = (TextView) rowView.findViewById(R.id.time);
        TextView notesm=(TextView)rowView.findViewById(R.id.notes);
       timem.setText(time[position]);
        notesm.setText(notes[position]);

        return rowView;
    }
}
