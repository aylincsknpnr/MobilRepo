package com.example.aylin.avukep.Hatirlatici.takvimuygulamasi;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.aylin.avukep.R;

/**
 * Created by kbhkn on 12.01.2016.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    private String baslik;
    private String not;
    private String tarih;
    private String saat;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Hatırlatıcınızın vakti gelmiştir..", Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        baslik = intent.getExtras().getString("baslik").toString();
        not = intent.getExtras().getString("not").toString();
        tarih = intent.getExtras().getString("tarih").toString();
        saat = intent.getExtras().getString("saat").toString();
        Notification(context, baslik, not, tarih, saat);
    }

    public void Notification(Context context, String baslik, String not, String tarih, String saat) {
        Uri sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notify);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(context.getString((R.string.not), not, tarih, saat))
                .setContentTitle(baslik)
                .setSound(sound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(context.getString((R.string.not), not, tarih, saat)))
                .setContentText(context.getString((R.string.not), not, tarih, saat))
                .setAutoCancel(true);


        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationmanager.notify(0, builder.build());
    }
}
