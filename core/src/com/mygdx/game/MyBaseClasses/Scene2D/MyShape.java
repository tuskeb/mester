package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by tanulo on 2017. 12. 13..
 */

public abstract class MyShape {

    /**
     * Tényleges középpont. Ez alapján számolja a pozícióját. center=cx+offsetx forgatva origin körül
     */
    protected float realCenterX = 0;

    /**
     * Tényleges középpont. Ez alapján számolja a pozícióját. center=cx+offsetx forgatva origin körül
     */
    protected float realCenterY = 0;

    /**
     * Szélesség. Forgatásnál nem változik.
     */
    protected float width = 0;

    /**
     * Magasság. Forgatásnál nem változik.
     */
    protected float height = 0;

    /**
     * Forgatás fokban megadva.
     */
    protected float rotation = 0;

    /**
     * Relatív eltolás cx-től számítva. center=cx+offsetx
     */
    protected float offsetX = 0;

    /**
     * Relatív eltolás cy-tól számítva. center=cy+offsety
     */
    protected float offsetY=0;

    /**
     * A középpont abszolút pozíciója a játéktérben.
     */
    protected float centerX =0;

    /**
     * A középpont abszolút pozíciója a játéktérben.
     */
    protected float centerY =0;

    /**
     * A forgatás középpontja. Relatív a valódi helyétől (offsetxy+cxy) az alakzatnak.
     */
    protected float originX = 0;

    /**
     * A forgatás középpontja. Relatív a valódi helyétől (offsetxy+cxy) az alakzatnak.
     */
    protected float originY = 0;

    static protected float PI = (float) Math.PI;


    abstract public Vector2[] getCorners();
    abstract public boolean overlaps(MyShape other);

    public MyShape(float x, float y, float width, float height, float rotation, float originX, float originY, float offsetX, float offsetY, boolean alignToLeftBottom) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        if (alignToLeftBottom){
            setPosition(x,y);
            setOrigin(originX,originY);
        }else {
            setPositionFromCenter(x,y);
            setOriginFromCenter(originX,originY);
        }
    }


    public void setSizeByCenter(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void setSize(float width, float height) {
        this.centerX -= (this.width - width) / 2f;
        this.centerY -= (this.height - height) / 2f;
        this.width = width;
        this.height = height;
        calculateCenterXY();
    }

    protected void calculateCenterXY(){
        Vector2 origCenter = new Vector2(centerX + offsetX, centerY + offsetY);
        Vector2 origin =  new Vector2(originX + centerX + offsetX,originY + centerY + offsetY);
        Vector2 v = origCenter.sub(origin);
        v.rotate(rotation);
        Vector2 s = v.add(origin);
        //this.realCenterX = centerX + offsetX;
        //this.realCenterY = centerY + offsetY;
        this.realCenterX = s.x;
        this.realCenterY = s.y;
        System.out.println("Origin (" + originX + " : " + originY+")");
    }

    public void setPosition(float X, float Y) {
        this.centerX = X + width/2;
        this.centerY = Y + height/2;
        calculateCenterXY();
        //System.out.println(realCenterX + " - " +realCenterY);
        //System.out.println(X + " -yx " +Y);
    }

    public void setPositionFromCenter(float centerX, float centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
        calculateCenterXY();
    }

    public void setOffset(float offsetX, float offsetY){
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        calculateCenterXY();
    }

    public void rotate(float degree) {
        rotation += degree;
    }

    public void setRotation(float degree) {
        rotation = degree;
        calculateCenterXY();
    }

    public float getRealCenterX() {
        return realCenterX;
    }

    public float getRealCenterY() {
        return realCenterY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    /**
     * A bal alsó sarok abszolút pozíciója a játéktérben, eltolással (offsetXY), forgatással együtt
     * @return
     */
    public float getX() {
        return realCenterX - width/2;
    }

    /**
     * A bal alsó sarok abszolút pozíciója a játéktérben, eltolással (offsetXY), forgatással együtt
     * @return
     */
    public float getY() {
        return realCenterY - height/2;
    }


    public void setOriginToCenter(){
        originX = 0;
        originY = 0;
    }

    /**
     * Forgatási középpont beállítása a középponttól számítva
     * @param x
     * @param y
     */
    public void setOriginFromCenter(float x, float y){
        originX = x - offsetX;
        originY = y - offsetY;
        calculateCenterXY();
    }

    /**
     * Forgatási középpont beállítása a bal alsó saroktól mérve.
     * @param x
     * @param y
     */
    public void setOrigin(float x, float y){
        originX = x - width / 2 - offsetX;
        originY = y - height / 2 - offsetY;
        calculateCenterXY();
        System.out.println("SetOrigin " + x + " - " + y);
    }
}
