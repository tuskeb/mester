package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BluetoothClientConnectionStage extends BluetoothStage {

    MyLabel waitingLabel;

    public BluetoothClientConnectionStage(MyGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (getBluetoothManager().isDiscovering()) {
            waitingLabel.setText("Discovering devices " + "....".substring(4 - ((int) getElapsedTime()) % 4));
        }
        refreshDeviceButtons();
        if (getBluetoothManager().isConnected()) {
            getBluetoothManager().stopDiscovering();
            startConnection();
        }
    }


    @Override
    public void init() {
        Gdx.app.error("BTM", "Client start discovering");
        startBluetoothDiscovering();
        addActor(waitingLabel = new MyLabel("", Styles.getLabelStyle()));
        waitingLabel.setPosition(300,300);
    }

    abstract public void startConnection();



    public void refreshDeviceButtons() {
        for (Actor a : getActors()) {
            if (a instanceof MyButton) {
                if (a.getUserObject() instanceof Integer) {
                    getActors().removeValue(a, true);
                }
            }
        }
        ArrayList<java.lang.String> strings = getDiscoveredDevicesName();

        int d = 0;
        for (java.lang.String s : strings) {
            final MyButton myButton = new MyButton(s, Styles.getTextButtonStyle());
            myButton.setPosition(0, 600 - d  * 70);
            myButton.setUserObject(new Integer(d));
            myButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    startConnectingToDevice((Integer)myButton.getUserObject());
                }
            });
            d++;
            addActor(myButton);
        }
    }

}




