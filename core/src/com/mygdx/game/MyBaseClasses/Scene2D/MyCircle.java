package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tanulo on 2017. 12. 13..
 */

public class MyCircle implements MyShape {
    float centerX = 0, centerY = 0;
    float radius = 0;

    public MyCircle(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public Vector2[] getCorners() {
        return new Vector2[0];
    }

    @Override
    public void rotate(float degree) {

    }

    @Override
    public void setRotation(float degree) {

    }

    @Override
    public boolean overlaps(MyShape other) {
        return false;
    }

    @Override
    public void setSize(float width, float height) {

    }

    @Override
    public void setPosition(float centerX, float centerY) {

    }
}
