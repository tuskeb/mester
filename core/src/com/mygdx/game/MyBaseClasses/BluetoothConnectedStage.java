package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 18..
 */

abstract public class BluetoothConnectedStage extends BluetoothStage {
    private static final String HELLO_MSG="HelloBT";
    private static final float HELLO_INTERVAL=1;
    private static final float HELLO_TIMEOUT=2.5f;
    private float lastReceiveHello = 0;
    private float lastSendHello = 0;

    private Queue<String> messages = new Queue<String>();

    public Queue<String> getMessages() {
        return messages;
    }


    public BluetoothConnectedStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    private void sendHello(){
        getBluetoothManager().sendMessage(HELLO_MSG);
    }

    abstract public void disconnected();

    public String getMessage(){
        if (messages.size>0){
            return messages.removeFirst();
        }
        return null;
    }

    public void sendMessage(String msg){
        getBluetoothManager().sendMessage(msg);
    }


    @Override
    public void act(float delta) {
        String s;
        if ((s = getBluetoothManager().getMessage())!= null) {
            if (s.compareTo(HELLO_MSG) == 0) {
                lastReceiveHello = getElapsedTime();
            } else {
                messages.addLast(s);
            }
        }
        if (getElapsedTime() - HELLO_INTERVAL> lastSendHello) {
            lastSendHello = getElapsedTime();
            sendHello();
        }
        if (getElapsedTime() - HELLO_TIMEOUT> lastReceiveHello){
            getBluetoothManager().stop();
            disconnected();
        }
        super.act(delta);
    }
}
