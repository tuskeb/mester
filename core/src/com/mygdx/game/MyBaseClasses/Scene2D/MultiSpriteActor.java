package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

import java.util.ArrayList;

/**
 * Created by M on 12/14/2017.
 */

public abstract class MultiSpriteActor extends MyActor implements InitableInterface {
    protected ArrayList<OffsetSprite> sprites = new ArrayList<OffsetSprite>();

    /*  OffsetSprite... olyam mint egy tömb de simán fel lehet sorolni a paramétereket. Nincs fix hossza.
        https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs */
    public MultiSpriteActor(OffsetSprite... offsetSprites) {
        for (OffsetSprite spite: offsetSprites) {
            addSprite(spite);
        }
        init();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (OffsetSprite sprite: sprites) {
            sprite.draw(batch);
        }
    }


    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        for (OffsetSprite sprite: sprites) {
            //sprite.setSize(getWidth(), getHeight());
            sprite.setOrigin(getWidth()/2 - sprite.getOffsetVector().x, getHeight()/2  - sprite.getOffsetVector().y);
        }
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        for (OffsetSprite sprite: sprites) {
            sprite.setPosition(x + sprite.getOffsetVector().x, y + sprite.getOffsetVector().y);
        }
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        for (OffsetSprite sprite: sprites) {
            sprite.setRotation(getRotation());
        }
    }

    public void addSprite(OffsetSprite sprite){
        sprites.add(sprite);
        sprite.setPosition(getX() + sprite.getOffsetVector().x, getY() + sprite.getOffsetVector().y);
        //addCollisionShape("SpriteRect"+ sprites.size(), new MyRectangle(sprite.getOffsetVector().x, sprite.getOffsetVector().y, sprite.getHeight(), sprite.getWidth(), sprite.getRotation(), getOriginX(), getOriginY()));
    }

}
