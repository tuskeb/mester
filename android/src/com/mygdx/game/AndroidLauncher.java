package com.mygdx.game;

import android.os.Bundle;
import android.os.Handler;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import hu.csanyzeg.master.MyBaseClasses.Bluetooth.BluetoothSingleton;
import hu.csanyzeg.master.MasterDemo;

public class AndroidLauncher extends AndroidApplication {


	public static final int MESSAGE_STATE_CHANGE = 1;
	public static final int MESSAGE_READ = 2;
	public static final int MESSAGE_WRITE = 3;
	public static final int MESSAGE_DEVICE_NAME = 4;
	public static final int MESSAGE_TOAST = 5;

	public static final String DEVICE_NAME = "device_name";
	public static final String TOAST = "toast";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		BluetoothManager btm = new BluetoothManager(this, new Handler());
		btm.getAdapter();
		BluetoothSingleton.getInstance().bluetoothManager = btm;

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		initialize(new MasterDemo(), config);
	}

}
