package com.example.aylin.avukep.Hatirlatici.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aylin.avukep.Hatirlatici.model.Alarm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kbhkn on 11.01.2016.
 */
public class AlarmDAO extends SQLiteOpenHelper {
    private static final String VERITABANI = "takvimNOT";
    private static final String TABLO = "hatirlaticiListesi";

    public AlarmDAO(Context context) {
        super(context, VERITABANI, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLO + "(alarmID INTEGER PRIMARY KEY AUTOINCREMENT,alarmDakika INTEGER,alarmSaat INTEGER,alarmGun INTEGER, alarmAy INTEGER, alarmYil INTEGER, alarmBasligi TEXT, alarmNotu TEXT)";
        Log.d("AlarmDAO", "SQL: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO);
        onCreate(db);
    }

    public void onInsert(Alarm alarm) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("alarmDakika", alarm.getAlarmDakika());
        values.put("alarmSaat", alarm.getAlarmSaat());
        values.put("alarmGun", alarm.getAlarmGun());
        values.put("alarmAy", alarm.getAlarmAy());
        values.put("alarmYil", alarm.getAlarmYil());
        values.put("alarmBasligi", alarm.getAlarmBasligi());
        values.put("alarmNotu", alarm.getAlarmNotu());
        sqldb.insert(TABLO, null, values);
        sqldb.close();
    }

    public List<Alarm> onAlarmList() {
        List<Alarm> tumAlarmlar = new ArrayList<Alarm>();
        SQLiteDatabase sqldb = this.getWritableDatabase();
        String[] kolonlar = {"alarmID", "alarmDakika", "alarmSaat", "alarmGun", "alarmAy", "alarmYil", "alarmBasligi", "alarmNotu"};
        Cursor cursor = sqldb.query(TABLO, kolonlar, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Alarm alarm = new Alarm();
            alarm.setAlarmID(cursor.getInt(0));
            alarm.setAlarmDakika(cursor.getInt(1));
            alarm.setAlarmSaat(cursor.getInt(2));
            alarm.setAlarmGun(cursor.getInt(3));
            alarm.setAlarmAy(cursor.getInt(4));
            alarm.setAlarmYil(cursor.getInt(5));
            alarm.setAlarmBasligi(cursor.getString(6));
            alarm.setAlarmNotu(cursor.getString(7));
            tumAlarmlar.add(alarm);
        }
        return tumAlarmlar;
    }

    public void onDeleteAlarm(Alarm alarm) {
        SQLiteDatabase sqldb = this.getWritableDatabase();
        sqldb.delete(TABLO, "alarmID=" + alarm.getAlarmID(), null);
    }

}
