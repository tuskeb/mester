package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tanulo on 2017. 12. 13..
 */

public interface MyShape {
    public Vector2[] getCorners();
    public void rotate(float degree);
    public void setRotation(float degree);
    public boolean overlaps(MyShape other);
    public void setSize(float width, float height);
    public void setPosition(float centerX, float centerY);
}
