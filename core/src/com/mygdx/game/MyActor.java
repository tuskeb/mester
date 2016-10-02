package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyActor extends Actor {

    protected float elapsedTime = 0;

    public MyActor() {
        super();
        debug();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setOrigin(getWidth()/2, getHeight()/2);
    }

    public void resetElapsedTime()
    {
        elapsedTime = 0;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
