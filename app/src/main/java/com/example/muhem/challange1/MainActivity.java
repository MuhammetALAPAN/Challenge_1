package com.example.muhem.challange1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private LockScreenStateReceiver mLockScreenStateReceiver;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLockScreenStateReceiver = new LockScreenStateReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(mLockScreenStateReceiver, filter);
        tv = findViewById(R.id.tv);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String stateSaved = savedInstanceState.getString("saved_state");
        tv.setText(stateSaved);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String stateToSave = tv.getText().toString();
        outState.putString("saved_state", stateToSave);
    }

    public class LockScreenStateReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!(intent.getAction().equals(Intent.ACTION_SCREEN_OFF))) {
                tv = findViewById(R.id.tv);
                String str_count = tv.getText().toString();
                int count = Integer.parseInt(str_count);
                count++;
                str_count = Integer.toString(count);
                tv.setText(str_count);
                //("Screen unlocked");
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mLockScreenStateReceiver);
        super.onDestroy();
    }
}



