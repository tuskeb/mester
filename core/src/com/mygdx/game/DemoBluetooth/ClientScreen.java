package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.BluetoothSingleton;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.iBluetooth;
import com.mygdx.game.MyGdxGame;
import com.sun.org.apache.xpath.internal.operations.String;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

public class ClientScreen extends MyScreen {
    MyStage stage;
    iBluetooth btm;
    BTGameStage btGameStage = null;

    public ClientScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        btm = BluetoothSingleton.getInstance().bluetoothManager;
        stage = new MyStage(new ExtendViewport(1280,720, new OrthographicCamera(1280,720)), spriteBatch, game) {

            MyLabel label;

            @Override
            public void init() {
                addBackEventStackListener();
                btm.enableBluetooth();
                btm.discoverDevices();

                addActor(new MyLabel("", game.getLabelStyle()){
                    @Override
                    public void init() {
                        setPosition(300,400);
                    }

                    @Override
                    public void act(float delta) {
                        super.act(delta);
                        if (btm.isDiscovering()) {
                            setText("Discovering devices " + "....".substring(4 - ((int) elapsedtime) % 4));
                        }
                    }
                });

                label = new MyLabel("", game.getLabelStyle()){
                    @Override
                    public void init() {
                        super.init();
                        setPosition(30,30);
                    }
                };
                addActor(label);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                label.setText(btm.getDevice());
                refreshDeviceButtons();
                if (btm.isConnected()){
                    btm.stopDiscovering();
                    setBackGroundColor(0,0,1);
                    btGameStage = new BTGameStage(game, btm);

                    //game.setScreen(new BTGameScreen(game, btm));
                }
            }

            public void refreshDeviceButtons(){
                for (Actor a : getActors()) {
                    if (a instanceof MyButton){
                        if (a.getUserObject() instanceof Integer){
                            getActors().removeValue(a,true);
                    /*if (((Integer)a.getUserObject() == 1)){

                    }*/
                        }
                    }
                }
                int y = 0;
                while (!btm.isFirst()){
                    btm.switchToPrevDevice();
                }
                boolean next = true;
                while (!btm.isLast() || next) {
                    if (btm.isLast()){
                        next = false;
                    }
                    final MyButton myButton = new MyButton(btm.getDevice(), game.getTextButtonStyle());
                    myButton.setPosition(0, 600 - y * 70);
                    myButton.setUserObject(new Integer(y));
                    myButton.addListener(new ClickListener(){
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            super.clicked(event, x, y);
                            while (!btm.isFirst()){
                                btm.switchToPrevDevice();
                            }
                            for(int i = 0; i<(Integer)myButton.getUserObject();i++){
                                btm.switchToNextDevice();
                            }
                            btm.connectToServer();
                        }
                    });
                    addActor(myButton);
                    btm.switchToNextDevice();
                    y++;
                };
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
