package com.example.aylin.alarmdeneme;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NotificationBarAlarm extends BroadcastReceiver {

	NotificationManager notifyManager;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	public void onReceive(Context context, Intent intent) {

		Log.d("NotificationAlarm", "onReceive");

		notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		// This Activity will be started when the user clicks the notification
		// in the notification bar
		Intent notificationIntent = new Intent(context, OnBootCompletedExampleActivity.class);

		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
		Notification notif = new Notification(R.drawable.ic_stat_rooster_col, "naber aylinn!", System.currentTimeMillis());

		// Play sound?
		// If you want you can play a sound when the notification shows up.
		// Place the MP3 file into the /raw folder.
		notif.sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.jingle);


		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			notif = new Notification();
			notif.icon = R.mipmap.ic_launcher;
			try {
				Method deprecatedMethod = notif.getClass().getMethod("setLatestEventInfo", Context.class, CharSequence.class, CharSequence.class, PendingIntent.class);
				deprecatedMethod.invoke(notif, context, "aylinnnn", null, contentIntent);
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {

			}
		} else {
			// Use new API
			Notification.Builder builder = new Notification.Builder(context)
					.setContentIntent(contentIntent)
					.setSmallIcon(R.mipmap.ic_launcher)
					.setContentTitle("aylinnn");
			notif = builder.build();
		}

		notifyManager.notify(1, notif);
	}

}