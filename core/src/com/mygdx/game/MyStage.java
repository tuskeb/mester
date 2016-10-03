package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage {
    protected Game game;

    public MyStage(Game game) {
        this.game = game;
        init();
    }

    public MyStage(Viewport viewport, Game game) {
        super(viewport);
        this.game = game;
        init();
    }

    public MyStage(Viewport viewport, Batch batch, Game game) {
        super(viewport, batch);
        this.game = game;
        init();
    }

    abstract protected void init();

}
