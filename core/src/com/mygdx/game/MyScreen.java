package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen {
    public final float WORLD_WIDTH = 640, WORLD_HEIGHT = 480;
    protected Viewport viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT);
    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected Stage stage = new Stage(viewport, spriteBatch);
    protected OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        //spriteBatch.begin();
        stage.act(delta);
        stage.draw();
        //spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }
}
