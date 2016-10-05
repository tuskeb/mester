package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen {
    public final static float WORLD_WIDTH = 640, WORLD_HEIGHT = 480;
    protected SpriteBatch spriteBatch = new SpriteBatch();
    protected OrthographicCamera camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
    protected ExtendViewport viewport = new ExtendViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

    public float r=0,g=0,b=0;

    protected Game game;



    public MyScreen(Game game) {
        this.game = game;
        camera.translate(WORLD_WIDTH/2, WORLD_HEIGHT/2);
        camera.update();
    }

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
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.translate(((viewport.getWorldWidth() - WORLD_WIDTH) / 2) < 0 ? 0 : -((viewport.getWorldWidth() - WORLD_WIDTH) / 2),
                ((viewport.getWorldHeight() - WORLD_HEIGHT) / 2) < 0 ? 0 : -((viewport.getWorldHeight() - WORLD_HEIGHT) / 2));
        camera.update();
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    public Game getGame() {
        return game;
    }

    public void setBackGroundColor(float r, float g, float b)
    {
        this.r=r;
        this.g = g;
        this.b = b;
    }

}
