package com.example.aylin.avukep.Hatirlatici.takvimuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aylin.avukep.Hatirlatici.model.Alarm;
import com.example.aylin.avukep.R;

import java.util.List;

/**
 * Created by kbhkn on 11.01.2016.
 */
public class AlarmAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Alarm> alarmListesi;


    public AlarmAdapter(Activity activity, List<Alarm> alarmlar){
        mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        alarmListesi = alarmlar;
    }

    @Override
    public int getCount() {
        return alarmListesi.size();
    }

    @Override
    public Alarm getItem(int position) {
        return alarmListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView=convertView;

        if (convertView == null){
            satirView = mInflater.inflate(R.layout.satir,null);}

        LinearLayout arkaplan=(LinearLayout) satirView.findViewById(R.id.satir);
        arkaplan.setBackgroundColor(Color.GREEN);

        TextView tarih = (TextView) satirView.findViewById(R.id.tarih);
        TextView not = (TextView) satirView.findViewById(R.id.not);
        TextView baslik = (TextView) satirView.findViewById(R.id.baslik);

        Alarm alarm = alarmListesi.get(position);
        System.out.println("yazıncaaa:..."+alarm.getAlarmBasligi());
        baslik.setText(alarm.getAlarmBasligi());
        not.setText(alarm.getAlarmNotu());
        tarih.setText(alarm.getAlarmGun() + "/" + (alarm.getAlarmAy()+1) + "/" + alarm.getAlarmYil() + " " + alarm.getAlarmSaat() + ":" + alarm.getAlarmDakika());

        return satirView;
    }
}
