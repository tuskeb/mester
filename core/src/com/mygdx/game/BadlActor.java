package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class BadlActor extends OneSpriteActor {
    public BadlActor() {
        super("badlogic.jpg");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1, getY());
    }
}
