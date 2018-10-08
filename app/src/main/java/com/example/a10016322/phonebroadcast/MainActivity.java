package com.example.a10016322.phonebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new IncomingCall();
        IntentFilter phoneFilter = new IntentFilter();
        phoneFilter.addAction(Intent.ACTION_CALL);
        registerReceiver(broadcastReceiver, phoneFilter);

    }

    public class IncomingCall extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            try
            {
                String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

                if(state.equals(TelephonyManager.EXTRA_STATE_RINGING))
                    Toast.makeText(context, "Phone is ringing", Toast.LENGTH_SHORT).show();
                else if(state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK))
                    Toast.makeText(context, "Call received", Toast.LENGTH_SHORT).show();
                else if(state.equals(TelephonyManager.EXTRA_STATE_IDLE))
                    Toast.makeText(context, "Phone is idle", Toast.LENGTH_SHORT).show();

            }catch(Exception e){}
        }
    }
}
// link with instructions - http://www.learn-android-easily.com/2013/05/incomin-call-broadcast-reciever.html