//https://algorhymes.wordpress.com/2013/02/07/java-bluetooth-on-android-with-and-wo-libgdx/
package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

/**
 * Created by tuskeb on 2017. 01. 12..
 */

public class BluetoothSingleton {

    private static volatile BluetoothSingleton instance = null;
    public iBluetooth bluetoothManager;

    /* METHODS */
    public static BluetoothSingleton getInstance() {
        if (instance == null) {
            synchronized (BluetoothSingleton.class) {
                if (instance == null) {
                    instance = new BluetoothSingleton();
                }
            }
        }
        return instance;
    }

    private BluetoothSingleton() {

    }

}
