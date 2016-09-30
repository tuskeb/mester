package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuScreen extends MyScreen {
    private BadlActor actor;
    public MenuScreen() {
        actor = new BadlActor();
        stage.addActor(actor);
    }
}
