package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by tuskeb on 2017. 02. 11..
 */

public class HttpPreference extends HttpConnect implements Preferences {

    private boolean locked = false;
    private Map<String, Object> map = new HashMap<String, Object>();


    public HttpPreference(String Url, int UserID, String Password) {
        super(Url, UserID, Password);
        refresh();
        Gdx.app.getPreferences("dsdd");
    }

    public boolean isLocked() {
        return locked;
    }

    public void clear() {
        map.clear();
    }

    public Preferences putBoolean(String key, boolean val) {
        map.put(key, new Boolean(val));
        return this;
    }

    public Preferences putInteger(String key, int val) {
        map.put(key, new Integer(val));
        return this;
    }

    public Preferences putLong(String key, long val) {
        map.put(key, new Long(val));
        return this;
    }

    public Preferences putFloat(String key, float val) {
        map.put(key, new Float(val));
        return this;
    }

    public Preferences putString(String key, String val) {
        map.put(key, new String(val));
        return this;
    }

    public Preferences put(Map<String, ?> vals) {
        map.putAll(vals);
        return this;
    }

    public boolean getBoolean(String key) {
        return ((Boolean) map.get(key)).booleanValue();
    }

    public int getInteger(String key) {
        return ((Integer) map.get(key)).intValue();
    }

    public long getLong(String key) {
        return ((Long) map.get(key)).longValue();
    }

    public float getFloat(String key) {
        return ((Float) map.get(key)).floatValue();
    }

    public String getString(String key) {
        return ((String) map.get(key)).toString();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return ((Boolean) map.get(key)).booleanValue();
    }

    public int getInteger(String key, int defValue) {
        return map.get(key) == null ? defValue : ((Integer) map.get(key)).intValue();
    }

    public long getLong(String key, long defValue) {
        return map.get(key) == null ? defValue : ((Long) map.get(key)).longValue();
    }

    public float getFloat(String key, float defValue) {
        return map.get(key) == null ? defValue : ((Float) map.get(key)).floatValue();
    }

    public String getString(String key, String defValue) {
        return map.get(key) == null ? defValue : ((String) map.get(key)).toString();
    }

    public Map<String, ?> get() {
        return map;
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public void flush() {
        System.out.println("Flush start");
        httpRequest.setContent("USER:" + this.userID + "\nPASSWORD:" + this.password + "\nDATA:FLUSH\n" + dataToString());
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println("Result:\n" + httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                System.out.println("Flush failed: " + t.getMessage());
                HttpPreference.this.failed(HttpErrors.timeout);
            }

            @Override
            public void cancelled() {
                System.out.println("Flush cancelled");
                HttpPreference.this.failed(HttpErrors.cancelled);
            }
        });
        System.out.println("Flush done");
    }

    private void dataFromString(String data) {
        System.out.println("Not implemented yet.");
    }

    private String dataToString() {
        System.out.println("Not implemented yet.");
        return "";
    }

    public void refresh() {
        System.out.println("Refresh thread start");
        waitingWhileLocked();
        locked = true;
        httpRequest.setContent("USER:" + this.userID + "\nPASSWORD:" + this.password + "\nDATA:GET");
        Gdx.net.sendHttpRequest(httpRequest, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                locked = false;
                System.out.println("Refresh thread done");
                System.out.println("Result:\n" + httpResponse.getResultAsString());
                dataFromString(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                locked = false;
                System.out.println("Refresh failed: " + t.getMessage());
                HttpPreference.this.failed(HttpErrors.timeout);
            }

            @Override
            public void cancelled() {
                locked = false;
                System.out.println("Refresh cancelled");
                HttpPreference.this.failed(HttpErrors.cancelled);
            }
        });
        System.out.println("Refresh function exit.");
    }

    protected void failed(HttpErrors httpErrors) {

    }

    protected void waitingWhileLocked() {
        while (isLocked()) {
            try {
                Thread.sleep(50);
                System.out.println("Waiting (Http req)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
