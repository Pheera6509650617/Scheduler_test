package com.example.scheduler_test;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

public class SampleAlarmReceiver extends BroadcastReceiver {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, SampleSchedulingService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // >= api26
            context.startForegroundService(service);
        } else {
            context.startService(service);
        }
    }

    public void setAlarm(Context context) {
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, SampleAlarmReceiver.class);
        if(android.os.Build.VERSION.SDK_INT  >= android.os.Build.VERSION_CODES.S) {
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            alarmIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmMgr.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 5 * 1000,
                    alarmIntent);
        } else {
            alarmMgr.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 5 * 1000,
                    alarmIntent);
        }
        Toast.makeText(context, "Set Alarm", Toast.LENGTH_LONG).show();

        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void cancelAlarm(Context context) {
        if(alarmIntent != null) {
            alarmMgr.cancel(alarmIntent);
        }
        Toast.makeText(context, "Cancel Alarm", Toast.LENGTH_SHORT).show();

        ComponentName receiver = new ComponentName(context, SampleBootReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
