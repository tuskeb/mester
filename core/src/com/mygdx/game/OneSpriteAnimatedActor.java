package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class OneSpriteAnimatedActor extends OneSpriteActor{

    TextureAtlas textureAtlas;
    private float fps = 30;

    public OneSpriteAnimatedActor(String file) {
        super(null);
        textureAtlas = new TextureAtlas(Gdx.files.internal(file));
        sprite = new Sprite(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    @Override
    protected void init() {
        super.init();
        setSize(textureAtlas.getRegions().get(0).getRegionWidth(), textureAtlas.getRegions().get(0).getRegionHeight());
    }

    public float getFps() {
        return fps;
    }

    public void setFps(float fps) {
        this.fps = fps;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        sprite.setRegion(textureAtlas.getRegions().get(((int)(elapsedTime*fps)) % textureAtlas.getRegions().size));
    }

}
