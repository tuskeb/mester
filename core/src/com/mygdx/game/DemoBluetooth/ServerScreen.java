package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.BluetoothSingleton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.iBluetooth;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

public class ServerScreen extends MyScreen {
    MyStage stage;
    BTGameStage btGameStage = null;
    iBluetooth btm;


    public ServerScreen(MyGdxGame game) {
        super(game);

    }

    @Override
    public void init() {
        super.init();
        btm = BluetoothSingleton.getInstance().bluetoothManager;

        stage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {
            @Override
            public void init() {
                addBackEventStackListener();
                btm.enableBluetooth();
                btm.enableDiscoveribility();
                btm.startServer();
                addActor(new MyLabel("", game.getLabelStyle()){
                    @Override
                    public void init() {
                        setPosition(300,400);
                    }

                    @Override
                    public void act(float delta) {
                        super.act(delta);

                        if (btm.isConnected()){
                            btm.stopDiscovering();
                            setBackGroundColor(0,0,1);
                            btGameStage = new BTGameStage(game, btm);
                            //game.setScreen(new BTGameScreen(game, btm));
                        }
                        else
                        {
                            setText("Waiting for client " + "....".substring(4 - ((int) elapsedtime) % 4));
                        }
                    }
                });
            }
        };
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (btGameStage==null){
            stage.act(delta);
            stage.draw();
        }
        else {
            btGameStage.act(delta);
            btGameStage.draw();
        }
    }


    @Override
    public void dispose() {
        //btm.stop();

        super.dispose();
    }
}
