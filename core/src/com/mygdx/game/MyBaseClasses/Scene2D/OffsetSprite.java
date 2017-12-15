package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

/**
 * Created by M on 12/14/2017.
 */

public class OffsetSprite extends Sprite {
    private Vector2 offsetVector;
    static protected float PI = (float) Math.PI;


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

    public OffsetSprite(Texture texture, float xOffset, float yOffset) {
        super(texture);
        offsetVector = new Vector2(xOffset, yOffset);
        offsetX = offsetVector.x;
        offsetY = offsetVector.y;
    }

    public Vector2 getOffsetVector() {
        return offsetVector;
    }


    public Vector2[] getCorners() {
        Vector2[] vector2 = new Vector2[4];
        float w2 = width/2;
        float h2 = height/2;
        float radius = (float) Math.sqrt(h2*h2 + w2*w2);
        float radrot = (float) Math.toRadians(rotation);
        float angle = (float) Math.asin(h2 / radius);
        vector2[0] = new Vector2( realCenterX + radius * (float) Math.cos(radrot - angle), realCenterY + radius * (float) Math.sin(radrot - angle));
        vector2[1] = new Vector2(realCenterX + radius * (float) Math.cos(radrot + angle),  realCenterY + radius * (float) Math.sin(radrot + angle));
        vector2[2] = new Vector2( realCenterX + radius * (float) Math.cos(radrot + PI - angle),  realCenterY + radius * (float) Math.sin(radrot + PI - angle));
        vector2[3] = new Vector2( realCenterX + radius * (float) Math.cos(radrot + PI + angle),  realCenterY + radius * (float) Math.sin(radrot + PI + angle));
        return vector2;
    }



    public void setSize(float width, float height) {
        super.setSize(width, height);
        this.centerX -= (this.width - width) / 2f;
        this.centerY -= (this.height - height) / 2f;
        this.width = width;
        this.height = height;
        calculateCenterXY();
    }

    protected void calculateCenterXY(){
        Vector2 origCenter = new Vector2(centerX , centerY);
        Vector2 origin =  new Vector2(originX + centerX + offsetX, originY + centerY + offsetY);
        Vector2 v = origCenter.sub(origin);
        v.rotate(rotation);
        Vector2 s = v.add(origin);
        this.realCenterX = s.x;
        this.realCenterY = s.y;
    }


    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        this.centerX = x + width/2;
        this.centerY = y + height/2;
        calculateCenterXY();

    }


    public void setRotation(float degree) {
        super.setRotation(degree);
        rotation = degree;
        calculateCenterXY();
    }

    public void setOrigin(float x, float y){
        super.setOrigin(x,y);
        originX = x - width / 2 - offsetX;
        originY = y - height / 2 - offsetY;
        calculateCenterXY();
        System.out.println("SetOrigin " + x + " - " + y);
        System.out.println("Shape Origin (" + originX + " : " + originY+")  CenterXY (" + centerX + " : " + centerY + ")");

    }


}
