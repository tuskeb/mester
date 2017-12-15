package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

/**
 * Created by M on 12/14/2017.
 */

public class OffsetSprite extends Sprite {
    private Vector2 offsetVector;

    public OffsetSprite(Texture texture, float xOffset, float yOffset) {
        super(texture);
        // TODO: 12/14/2017 Ez nem maradhat csak test
        setSize(50,50);
        offsetVector = new Vector2(xOffset, yOffset);
    }

    public Vector2 getOffsetVector() {
        return offsetVector;
    }

}
