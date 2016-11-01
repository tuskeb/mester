package com.mygdx.game.DemoGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class GameScreen extends MyScreen {
    GameStage box2dStage;
    MyStage controlStage;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    InputMultiplexer inputMultiplexer;


    public GameScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void init() {
        super.init();
        box2dStage = new GameStage(new ExtendViewport(16,9,new OrthographicCamera(16,9)),spriteBatch,game);
        controlStage = new ControlStage(new ExtendViewport(WORLD_WIDTH,WORLD_HEIGHT,new OrthographicCamera(WORLD_WIDTH,WORLD_HEIGHT)),spriteBatch,game);

        setBackGroundColor(0.2f,0.4f,0.8f);

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
        box2DDebugRenderer.render(box2dStage.getWorld(),box2dStage.getCamera().combined);

        spriteBatch.setProjectionMatrix(controlStage.getCamera().combined);
        controlStage.act();
        controlStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        setCameraReset((ExtendViewport)box2dStage.getViewport(), width, height);
    }

}
