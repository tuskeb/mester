package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

public class BluetoothScreen extends MyScreen {
    MyStage stage;

    public BluetoothScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        stage = new MyStage(new ExtendViewport(640,480, new OrthographicCamera(640,480)), spriteBatch, game) {
            @Override
            public void init() {
                addBackEventStackListener();
                addActor(new MyButton("Start server", game.getTextButtonStyle()){
                    @Override
                    public void init() {
                        super.init();
                        setPosition(0,0);
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                game.setScreen(new ServerScreen(game));
                            }
                        });
                    }
                });
                addActor(new MyButton("Start client", game.getTextButtonStyle()){
                    @Override
                    public void init() {
                        super.init();
                        setPosition(400,0);
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                game.setScreen(new ClientScreen(game));
                            }
                        });
                    }
                });
            }
        };
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }
}
