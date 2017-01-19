package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyBaseClasses.BluetoothChooseServerClientStage;
import com.mygdx.game.MyBaseClasses.BluetoothClientConnectionStage;
import com.mygdx.game.MyBaseClasses.BluetoothDisconectionStage;
import com.mygdx.game.MyBaseClasses.BluetoothServerListenStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 17..
 */

public class BluetoothScreen extends MyScreen {

    public enum BluetoothState{
        Choose, Listening, Discovering, Connected, Disconnected
    }

    BluetoothChooseServerClientStage bluetoothChooseServerClientStage;
    BluetoothServerListenStage bluetoothServerListenStage;
    BluetoothClientConnectionStage bluetoothClientConnectionStage;
    BluetoothDisconectionStage bluetoothDisconectionStage;
    BTGameStage btGameStage;

    BluetoothState bluetoothState = BluetoothState.Choose;


    public BluetoothScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();

        btGameStage = new BTGameStage(game){
            @Override
            public void disconnected() {
                bluetoothDisconectionStage = new BluetoothDisconectionStage(game) {

                    @Override
                    public void end() {
                        game.setScreenBackByStackPop();
                    }
                };
                bluetoothState = BluetoothState.Disconnected;
            }
        };


        bluetoothChooseServerClientStage = new BluetoothChooseServerClientStage(game) {
            @Override
            public void init() {
                super.init();
                addBackEventStackListener();
            }


            @Override
            public void startServer() {
                bluetoothState = BluetoothState.Listening;
                bluetoothServerListenStage = new BluetoothServerListenStage(game) {
                    @Override
                    public void init() {
                        super.init();
                        addBackEventStackListener();
                    }
                    @Override
                    public void acceptConnection() {
                        bluetoothState = BluetoothState.Connected;
                        Gdx.input.setInputProcessor(btGameStage);
                    }
                };
                Gdx.input.setInputProcessor(bluetoothServerListenStage);
            }

            @Override
            public void startClient() {
                bluetoothState = BluetoothState.Discovering;
                bluetoothClientConnectionStage = new BluetoothClientConnectionStage(game) {
                    @Override
                    public void init() {
                        super.init();
                        addBackEventStackListener();
                    }
                    @Override
                    public void startConnection() {
                        bluetoothState = BluetoothState.Connected;
                        Gdx.input.setInputProcessor(btGameStage);
                    }
                };
                Gdx.input.setInputProcessor(bluetoothClientConnectionStage);
            }
        };




        Gdx.input.setInputProcessor(bluetoothChooseServerClientStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        switch (bluetoothState){
            case Choose:
                bluetoothChooseServerClientStage.act(delta);
                bluetoothChooseServerClientStage.draw();
                break;
            case Listening:
                bluetoothServerListenStage.act(delta);
                bluetoothServerListenStage.draw();
                break;
            case Discovering:
                bluetoothClientConnectionStage.act(delta);
                bluetoothClientConnectionStage.draw();
                break;
            case Connected:
                btGameStage.act(delta);
                btGameStage.draw();
                break;
            case Disconnected:
                break;
        }
    }

    @Override
    public void dispose() {
        bluetoothChooseServerClientStage.dispose();
        super.dispose();
    }
}
