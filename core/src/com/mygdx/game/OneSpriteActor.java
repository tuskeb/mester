package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class OneSpriteActor extends MyActor {
    Texture texture;
    Sprite sprite;

    public OneSpriteActor(String file) {
        texture = new Texture(file);
        init();
    }

    public OneSpriteActor(Texture texture) {
        this.texture = texture;
        init();
    }

    private void init()
    {
        sprite = new Sprite(texture);
        setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }
}
