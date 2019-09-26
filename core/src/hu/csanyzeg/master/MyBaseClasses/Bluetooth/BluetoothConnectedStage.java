package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

/**
 * Created by tuskeb on 2017. 01. 18..
 */

abstract public class BluetoothConnectedStage extends BluetoothStage {
    private static final String HELLO_MSG="HelloBT";
    private static final float HELLO_INTERVAL=0.4f;
    private static final float HELLO_TIMEOUT=1.1f;
    private float lastReceiveHello = 0;
    private float lastSendHello = 0;

    private Queue<String> messages = new Queue<String>();

    public Queue<String> getMessages() {
        return messages;
    }


    public BluetoothConnectedStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
        Gdx.app.error("BTM", "Connected");
    }

    private void sendHello(){
        Gdx.app.error("BTM", "Send HELLO");
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
        while ((s = getBluetoothManager().getMessage())!= null) {
            s = s.trim();
            Gdx.app.error("BTM", "Receive " + s);
            if (s.compareTo(HELLO_MSG) == 0) {
                Gdx.app.error("BTM", "Receive HELLO");
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
            Gdx.app.error("BTM", "Timeout. Start disconnecting.");
            disconnected();
        }
        super.act(delta);
    }
}
