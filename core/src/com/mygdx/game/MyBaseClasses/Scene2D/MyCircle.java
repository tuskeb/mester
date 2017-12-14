package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tanulo on 2017. 12. 13..
 */

public class MyCircle extends MyShape {
    protected float radius = 0;
    public static int debugLineNumbers = 32;

    public MyCircle(float x, float y, float radius, float originX, float originY, float offsetX, float offsetY, boolean alignToLeftBottom) {
        super(x, y, radius, radius, 0, originX, originY, offsetX, offsetY, alignToLeftBottom);
        setRadius(radius);
    }

    public MyCircle(float offsetX, float offsetY, float radius, float originX, float originY,  boolean alignToLeftBottom) {
        super(0, 0, radius, radius, 0, originX, originY, offsetX, offsetY, alignToLeftBottom);
        setRadius(radius);
    }

    public MyCircle(float offsetX, float offsetY, float radius,  boolean alignToLeftBottom) {
        super(0, 0, radius, radius, 0, 0,0, offsetX, offsetY, alignToLeftBottom);
        setRadius(radius);
        setOriginToCenter();
    }

    public MyCircle(float x, float y, float radius, float originX, float originY, float offsetX, float offsetY) {
        super(x, y, radius, radius, 0, originX, originY, offsetX, offsetY, true);
        setRadius(radius);
    }

    public MyCircle(float offsetX, float offsetY, float radius, float originX, float originY) {
        super(0, 0, radius, radius, 0, originX, originY, offsetX, offsetY, true);
        setRadius(radius);
    }

    public MyCircle(float offsetX, float offsetY, float radius) {
        super(0, 0, radius, radius, 0, 0,0, offsetX, offsetY, true);
        setRadius(radius);
        setOriginToCenter();
    }

    @Override
    public Vector2[] getCorners() {
        Vector2[] vector2 = new Vector2[debugLineNumbers];
        for(int i=0; i<debugLineNumbers;i++){
            vector2[i] = new Vector2(radius/2, 0);
            vector2[i].rotate(360.0f/debugLineNumbers*i);
            vector2[i].add(realCenterX, realCenterY);
        }
        return vector2;
    }


    @Override
    public boolean overlaps(MyShape other) {
        return false;
    }

    @Override
    public void setSize(float width, float height) {
        radius = (float)Math.sqrt(width*height)*2;
        setRadius(radius);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        this.centerX += (this.width - radius) / 2f;
        this.centerY += (this.height - radius) / 2f;
        this.width = radius;
        this.height = radius;
        calculateCenterXY();
        System.out.println("Circle Origin (" + originX + " : " + originY+")  CenterXY (" + centerX + " : " + centerY + ")");
    }

    public void setRadiusByCenter(float radius) {
        this.radius = radius;
        this.width = radius*2;
        this.height = radius*2;
        calculateCenterXY();
    }
}
