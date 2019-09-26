//https://algorhymes.wordpress.com/2013/02/07/java-bluetooth-on-android-with-and-wo-libgdx/
/*
SERVER -> Enable bluetooth on the device.
SERVER -> Enable disoverability (when you will enable this option, your Device will ask for your permission to make this device discoverable for other devices. This lasts for limited time only. On older Android OS your phone can only be discoverable to 300 seconds, on newer I've read that you can extend this time to 3600 seconds. Only during that time your host device can be found by other devices).
CLIENT -> Enable bluetooth on the device.
CLIENT -> Start the discovering
CLIENT -> List discovered devices and chose the one that we want to connect to
BOTH -> Pair devices (You can read about pairing here)
CLIENT -> Start connection
SERVER -> Accept the connection
BOTH -> Start listening for messages
BOTH -> Exchange data
 */
package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

/**
 * Created by tuskeb on 2017. 01. 12..
 */
public interface iBluetooth {

    public void enableBluetooth();
    public void enableDiscoveribility();
    public void discoverDevices();
    public void stopDiscovering();
    public boolean startServer();
    public void connectToServer();
    public String getTest();
    public void sendMessage(String message);
    public String getMessage();
    public boolean isConnected();
    public boolean canConnect();
    public void switchToNextDevice();
    public void switchToPrevDevice();
    public String getDevice();
    public void stop();
    public boolean isFirst();
    public boolean isLast();
    public boolean isDiscovering();
}
