package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.sun.corba.se.spi.activation.ORBidHelper;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoScreen extends MyScreen {
    WorldDemoStage box2dStage;
    MyStage controlStage;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    InputMultiplexer inputMultiplexer;


    public WorldDemoScreen(Game game) {
        super(game);
    }

    @Override
    public void init() {
        controlStage = new MyStage(viewport,spriteBatch,game) {
            @Override
            public void init() {
                addActor(new MyLabel("Kattints a testekre!")
                {
                    @Override
                    public void init() {
                        setPosition(120,0);
                    }
                });
                addActor(new MyButton("Vissza"){
                    @Override
                    public void init() {
                        super.init();
                        setPosition(0,MyScreen.WORLD_HEIGHT-getHeight());
                        addListener(new ClickListener()
                        {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                game.setScreen(new MenuScreen(game));
                            }
                        });
                    }
                });
            }
        };


        box2dStage = new WorldDemoStage(new ExtendViewport(64,48,new OrthographicCamera(64,48)), spriteBatch, game);
        //((OrthographicCamera)stage.getCamera()).zoom = 0.25f;
        ((OrthographicCamera)box2dStage.getCamera()).zoom = 1;
        //((OrthographicCamera)box2dStage.getCamera()).translate(-5, -5);
        ((OrthographicCamera)box2dStage.getCamera()).update();

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(box2dStage);
        inputMultiplexer.addProcessor(controlStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.setProjectionMatrix(box2dStage.getCamera().combined);
        box2dStage.act();
        box2dStage.draw();
        box2DDebugRenderer.render(box2dStage.world,box2dStage.getCamera().combined);

        spriteBatch.setProjectionMatrix(camera.combined);
        controlStage.act();
        controlStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setCameraReset((ExtendViewport)box2dStage.getViewport(), width, height);
    }
}
