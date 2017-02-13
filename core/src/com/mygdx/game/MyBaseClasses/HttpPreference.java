package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;

import java.util.Map;

/**
 * Created by tuskeb on 2017. 02. 11..
 */

public class HttpPreference extends HttpConnect implements Preferences {

    boolean locked = false;

    public HttpPreference(String Url, int UserID, String Password) {
        super(Url, UserID, Password);
        refresh();
    }



    public boolean isLocked() {
        return locked;
    }


    public void clear() {

    }

    public Preferences putBoolean(String key, boolean val) {
        return null;
    }

    public Preferences putInteger(String key, int val) {
        return null;
    }

    public Preferences putLong(String key, long val) {
        return null;
    }

    public Preferences putFloat(String key, float val) {
        return null;
    }

    public Preferences putString(String key, String val) {
        return null;
    }

    public Preferences put(Map<String, ?> vals) {
        return null;
    }

    public boolean getBoolean(String key) {
        return false;
    }

    public int getInteger(String key) {
        return 0;
    }

    public long getLong(String key) {
        return 0;
    }

    public float getFloat(String key) {
        return 0;
    }

    public String getString(String key) {
        return null;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return false;
    }

    public int getInteger(String key, int defValue) {
        return 0;
    }

    public long getLong(String key, long defValue) {
        return 0;
    }

    public float getFloat(String key, float defValue) {
        return 0;
    }

    public String getString(String key, String defValue) {
        return null;
    }

    public Map<String, ?> get() {
        return null;
    }

    public boolean contains(String key) {
        return false;
    }

    public void remove(String key) {

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

    private void dataFromString(String data){

    }

    private String dataToString(){
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

    protected void failed(HttpErrors httpErrors){

    }

    protected void waitingWhileLocked(){
        while (isLocked()){
            try {
                Thread.sleep(50);
                System.out.println("Waiting (Http req)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
