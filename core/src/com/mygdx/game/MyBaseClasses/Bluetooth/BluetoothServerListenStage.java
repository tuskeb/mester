package com.mygdx.game.MyBaseClasses.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.UI.MyLabel;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BluetoothServerListenStage extends BluetoothStage {
    MyLabel waitingLabel;

    public BluetoothServerListenStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    abstract public void acceptConnection();

    public void init() {
        Gdx.app.error("BTM", "Server start listening");
        startBluetoothListening();
        addActor(waitingLabel = new MyLabel("", game.getLabelStyle()));
        waitingLabel.setPosition(300,300);
    }

    public void act(float delta) {
        super.act(delta);

        if (getBluetoothManager().isConnected()) {
            getBluetoothManager().stopDiscovering();
            acceptConnection();
        } else {
            waitingLabel.setText("Waiting for client " + "....".substring(4 - ((int) getElapsedTime()) % 4));
        }
    }

}


