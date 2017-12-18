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


    public OffsetSprite(Texture texture, float xOffset, float yOffset) {
        super(texture);
        offsetVector = new Vector2(xOffset, yOffset);
    }

    public Vector2 getOffsetVector() {
        return offsetVector;
    }


    public Vector2[] getCorners() {

        Vector2 center = new Vector2(getX() + getWidth()/2, getY() + getHeight()/2); //A sprite középpontja
        Vector2 origin = new Vector2(getOriginX() + getX(), getOriginY() + getY()); //Amelyik pont körül forog
        Vector2 rotOrigin = center.sub(origin); // Origóra helyezve
        Vector2 rot = rotOrigin.rotate(getRotation()); //Elforgatva
        Vector2 rotCenter = rot.add(origin); // Visszatolva

        //System.out.println("center: " + center + " origin: " + origin + " rotOrigin: " + rotOrigin + " rot: " + rot);

        Vector2[] vector2 = new Vector2[4];
        float w2 = getWidth()/2;
        float h2 = getHeight()/2;
        float radius = (float) Math.sqrt(h2*h2 + w2*w2);
        float radrot = (float) Math.toRadians(getRotation());
        float angle = (float) Math.asin(h2 / radius);
        vector2[0] = new Vector2( rotCenter.x + radius * (float) Math.cos(radrot - angle), rotCenter.y + radius * (float) Math.sin(radrot - angle));
        vector2[1] = new Vector2( rotCenter.x + radius * (float) Math.cos(radrot + angle),  rotCenter.y + radius * (float) Math.sin(radrot + angle));
        vector2[2] = new Vector2( rotCenter.x + radius * (float) Math.cos(radrot + PI - angle),  rotCenter.y + radius * (float) Math.sin(radrot + PI - angle));
        vector2[3] = new Vector2( rotCenter.x + radius * (float) Math.cos(radrot + PI + angle),  rotCenter.y + radius * (float) Math.sin(radrot + PI + angle));
        return vector2;
    }


}
