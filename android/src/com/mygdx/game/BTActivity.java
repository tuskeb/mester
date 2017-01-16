//https://algorhymes.wordpress.com/2013/02/07/java-bluetooth-on-android-with-and-wo-libgdx/
package com.mygdx.game;

/**
 * Created by tuskeb on 2017. 01. 12..
 */



import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

import com.mygdx.game.MyBaseClasses.BluetoothSingleton;

public class BTActivity extends Activity {

    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BluetoothManager btm = new BluetoothManager(this, new Handler());
        btm.getAdapter();
        BluetoothSingleton.getInstance().bluetoothManager = btm;
    }

    @Override
    public void onStart() {
        super.onStart();

        Intent intent = new Intent(BTActivity.this, AndroidLauncher.class);
        BTActivity.this.startActivity(intent);
    }
}
