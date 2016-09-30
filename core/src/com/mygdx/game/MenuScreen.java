package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {
    protected MenuStage menuStage = new MenuStage(viewport, spriteBatch);


    public MenuScreen() {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        menuStage.act(delta);
        menuStage.draw();

    }
}
