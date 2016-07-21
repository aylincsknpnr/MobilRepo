package com.example.aylin.avukep.Hatirlatici.takvimuygulamasi;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.aylin.avukep.Hatirlatici.dao.AlarmDAO;
import com.example.aylin.avukep.Hatirlatici.model.Alarm;
import com.example.aylin.avukep.Kep.KepDetayActivity;
import com.example.aylin.avukep.Kep.KepFragment;
import com.example.aylin.avukep.R;

import java.util.Calendar;

public class HatirlaticiKur extends AppCompatActivity {
    private Calendar takvim;
    public static Intent intent;
    public final static String TAG_FRAGMENT = "TAG_FRAGMENT";
    public TextView ptxtAlarmBaslik,ptxtAlarmNot;
    private Button btnTarihAyarla,btnSaatAyarla,btnIptalEt,btnAlarmKur;
    private int yil,ay,gun,saat,dakika;
    public static int sayac = 0;
    public static String baslik,not;
    public static String check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hatirlatici_kur);
        ptxtAlarmBaslik = (TextView)findViewById(R.id.ptxtAlarmBaslik);
        ptxtAlarmNot = (TextView)findViewById(R.id.ptxtAlarmNot);
        btnTarihAyarla = (Button)findViewById(R.id.btnTarihAyarla);
        btnSaatAyarla = (Button)findViewById(R.id.btnSaatAyarla);
        btnIptalEt=(Button)findViewById(R.id.btnIptalEt);
        btnAlarmKur=(Button)findViewById(R.id.btnAlarmKur);
        intent = getIntent();
         baslik =  intent.getStringExtra("baslik2");
         not=intent.getStringExtra("not2");
        System.out.println(not+"set ederken");
        ptxtAlarmBaslik.setText(baslik);
        ptxtAlarmNot.setText(not);
        takvim = Calendar.getInstance();
        yil = takvim.get(Calendar.YEAR);
        ay = takvim.get(Calendar.MONTH);
        gun = takvim.get(Calendar.DAY_OF_MONTH);
        saat = takvim.get(Calendar.HOUR);
        dakika = takvim.get(Calendar.MINUTE);

        btnIptalEt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlarmDAO db = new AlarmDAO(getApplicationContext());
                finish();
                final RemindFragment fragment = (RemindFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
            }

        });
        btnAlarmKur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hatirlaticiyiKur(v);
                RemindFragment remindFragment = new RemindFragment();
                android.support.v4.app.FragmentTransaction ftrem = getSupportFragmentManager().beginTransaction();
                ftrem.replace(R.id.frameha, remindFragment);
                ftrem.commit();
               // final RemindFragment fragment2 = (RemindFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT);
                check="1";
            }

        });
    }

    public void setTarih(View view){
        showDialog(1);
        Toast.makeText(HatirlaticiKur.this, "Tarih Ayarlanıyor..", Toast.LENGTH_LONG).show();
    }

    public void setSaat(View view){
        showDialog(2);
        Toast.makeText(HatirlaticiKur.this, "Saat Ayarlanıyor..", Toast.LENGTH_LONG).show();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this, myDateListener, yil, ay, gun);
        }
        if (id == 2)
            return new TimePickerDialog(this, myTimeListener, saat, dakika, true);
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2, arg3);
        }
    };

    private TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            showTime(hourOfDay, minute, true);
        }
    };

    private void showDate(int yil, int ay, int gun) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        btnTarihAyarla.setText(new StringBuilder().append(gun).append("/").append(ay + 1).append("/").append(yil));
    }

    private void showTime(int saat, int dakika, boolean pm) {
        this.saat = saat;
        this.dakika = dakika;
        btnSaatAyarla.setText(new StringBuilder().append(saat).append(":").append(dakika));
    }

    private int farkHesapla(int yil,int ay,int gun,int saat,int dakika) {
        int toplamFark = 0;
        Calendar takvimm = Calendar.getInstance();
        toplamFark += (dakika- takvimm.get(Calendar.MINUTE));
        toplamFark += (saat - takvimm.get(Calendar.HOUR)) * 60;
        toplamFark += (gun - takvimm.get(Calendar.DAY_OF_MONTH)) * 1440;
        toplamFark += (ay - takvimm.get(Calendar.MONTH)) * 43200;
        toplamFark += (yil - takvimm.get(Calendar.YEAR)) * 518400;

        return toplamFark * 45;
    }

    public void sayacAzalt(){
        sayac -= 1;
    }

    public void hatirlaticiyiKur(View view){
        boolean alarmUp = (PendingIntent.getBroadcast(getApplicationContext(), 0,
                new Intent(this, MyBroadcastReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        if (alarmUp)
        {
            System.out.println( "Alarm is already active");
        }
        intent = getIntent();
        baslik =  intent.getStringExtra("baslik2");
        not=intent.getStringExtra("not2");
        showDate(yil, ay, gun);
        showTime(saat, dakika, true);
        int i = farkHesapla(yil, ay, gun, saat, dakika);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.putExtra("baslik",baslik );
        intent.putExtra("not", not);
        intent.putExtra("tarih", gun + "/" + (ay + 1) + "/" + yil);
        intent.putExtra("saat", saat + ":" + dakika);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), sayac, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (i * 1000), pendingIntent);
        dbYaz();
        sayac += 1;
        Toast.makeText(this, "Takvime not ekleme başarıyla gerçekleşmiştir ", Toast.LENGTH_LONG).show();


    }

    public void dbYaz(){
        intent = getIntent();
        baslik =  intent.getStringExtra("baslik2");
        not=intent.getStringExtra("not2");
        AlarmDAO db = new AlarmDAO(getApplicationContext());
        Alarm alarm = new Alarm();
        alarm.setAlarmBasligi(baslik);
        alarm.setAlarmNotu(not);
        alarm.setAlarmYil(yil);
        alarm.setAlarmAy(ay);
        alarm.setAlarmGun(gun);
        alarm.setAlarmSaat(saat);
        alarm.setAlarmDakika(dakika);
        db.onInsert(alarm);

    }
    public String pushDurum(){
        return check;
    }
}
