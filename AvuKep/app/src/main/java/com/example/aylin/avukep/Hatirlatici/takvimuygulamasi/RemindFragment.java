package com.example.aylin.avukep.Hatirlatici.takvimuygulamasi;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aylin.avukep.Hatirlatici.dao.AlarmDAO;
import com.example.aylin.avukep.Hatirlatici.model.Alarm;
import com.example.aylin.avukep.Login.Caller;
import com.example.aylin.avukep.R;
import com.example.aylin.avukep.User.UserInfoModel;
import com.example.aylin.avukep.User.UserModel;

import java.util.List;

public class RemindFragment extends android.support.v4.app.Fragment {

    public static ListView listvNotlar;
    public static Button hatirEkle;
    private Alarm alarm;
    private PendingIntent alarmIntent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.remind_main, container, false);
        alarm = new Alarm();
        listvNotlar = (ListView)v.findViewById(R.id.listvNotlar);
        /*hatirEkle=(Button)v.findViewById(R.id.btnHatirlaticiEkle);

        hatirEkle.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),HatirlaticiKur.class);
                startActivity(intent);
            }

        });*/
        initialize();
        listvNotlar.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alarmbuilder = new AlertDialog.Builder(getActivity());
                alarmbuilder.setMessage("Bu alarmı silmek istiyor musunuz?");
                alarmbuilder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        AlarmDAO dao = new AlarmDAO(getActivity().getApplicationContext());
                        alarm.setAlarmID(dao.onAlarmList().get(position).getAlarmID());
                        dao.onDeleteAlarm(alarm);
                        alarmIntent = PendingIntent.getBroadcast(getActivity().getApplicationContext(), alarm.getAlarmID(), new Intent(getActivity().getApplicationContext(), MyBroadcastReceiver.class), PendingIntent.FLAG_CANCEL_CURRENT);
                        new HatirlaticiKur().sayacAzalt();
                        initialize();
                    }
                });
                alarmbuilder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alarmbuilder.show();
                return false;
            }
        });
        return v;
    }

    public void initialize(){
        AlarmDAO alarmlistesi = new AlarmDAO(getActivity().getApplicationContext());
        List<Alarm> alarmlar = alarmlistesi.onAlarmList();
        AlarmAdapter adapter = new AlarmAdapter(getActivity(),alarmlar);
        listvNotlar.setAdapter(adapter);
    }
}