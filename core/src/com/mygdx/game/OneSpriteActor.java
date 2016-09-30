package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class OneSpriteActor extends MyActor {
    private Texture texture;
    private Sprite sprite;

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

    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x,y);
    }
}
