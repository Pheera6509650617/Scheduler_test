package com.example.scheduler_test;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    SampleAlarmReceiver alarm = new SampleAlarmReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm.setAlarm(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("MenuTest", "Menu created");
        getMenuInflater().inflate(R.menu.memu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.start_action) {
            Toast.makeText(this, "push Start Alarm", Toast.LENGTH_SHORT).show();
            Log.d("MenuTest", "push Start Alarm");
            alarm.setAlarm(this);
            return true;
        }
        if(id == R.id.cancel_action) {
            Toast.makeText(this, "push Cancel Alarm", Toast.LENGTH_SHORT).show();
            Log.d("MenuTest", "push Cancel Alarm");
            alarm.cancelAlarm(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}