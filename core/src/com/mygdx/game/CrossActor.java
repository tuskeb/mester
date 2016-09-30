package com.mygdx.game;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class CrossActor extends OneSpriteActor {
    public CrossActor() {
        super("badlogic.jpg");
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1, getY()+1);
    }
}
