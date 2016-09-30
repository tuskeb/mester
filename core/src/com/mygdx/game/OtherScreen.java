package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OtherScreen extends MyScreen {
    protected OtherStage otherStage;
    public OtherScreen(Game game) {
        super(game);
        r = 1;
        g = 0.5f;
        b = 0.3f;
        otherStage = new OtherStage(viewport, spriteBatch, game);
        Gdx.input.setInputProcessor(otherStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        otherStage.act(delta);
        otherStage.draw();

    }
}
