package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class OneSpriteActor extends MyActor implements InitableInterface {
    protected Sprite sprite;

    public OneSpriteActor(Sprite sprite) {
        if (sprite!=null) {
            this.sprite = sprite;
            init();
        }
    }

    @Override
    public void init()
    {
        setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        sprite.setRotation(getRotation());

    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        sprite.setSize(getWidth(), getHeight());
        sprite.setOrigin(getWidth()/2, getHeight()/2);
    }
}
