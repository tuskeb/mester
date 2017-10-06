package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyBaseClasses.Bluetooth.BluetoothChooseServerClientStage;
import com.mygdx.game.MyBaseClasses.Bluetooth.BluetoothClientConnectionStage;
import com.mygdx.game.MyBaseClasses.Bluetooth.BluetoothDisconectionStage;
import com.mygdx.game.MyBaseClasses.Bluetooth.BluetoothServerListenStage;
import com.mygdx.game.MyBaseClasses.Scene2D.MyScreen;
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
    BTGameStage bluetoothGameStage;

    BluetoothState bluetoothState = BluetoothState.Choose;


    public BluetoothScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();




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
                        bluetoothGameStage = new BTGameStage(game){
                            @Override
                            public void disconnected() {
                                bluetoothDisconectionStage = new BluetoothDisconectionStage(game) {

                                    @Override
                                    public void end() {
                                        game.setScreenBackByStackPop();
                                    }
                                };
                                Gdx.input.setInputProcessor(bluetoothDisconectionStage);
                                bluetoothState = BluetoothState.Disconnected;
                            }
                        };
                        Gdx.input.setInputProcessor(bluetoothGameStage);
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
                        bluetoothGameStage = new BTGameStage(game){
                            @Override
                            public void disconnected() {
                                bluetoothDisconectionStage = new BluetoothDisconectionStage(game) {

                                    @Override
                                    public void end() {
                                        game.setScreenBackByStackPop();
                                    }
                                };
                                bluetoothState = BluetoothState.Disconnected;
                                Gdx.input.setInputProcessor(bluetoothDisconectionStage);
                            }
                        };
                        Gdx.input.setInputProcessor(bluetoothGameStage);
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
                bluetoothGameStage.act(delta);
                bluetoothGameStage.draw();
                break;
            case Disconnected:
                bluetoothDisconectionStage.act(delta);
                bluetoothDisconectionStage.draw();
                break;
        }
    }

    @Override
    public void dispose() {
        bluetoothChooseServerClientStage.dispose();
        super.dispose();
    }
}
