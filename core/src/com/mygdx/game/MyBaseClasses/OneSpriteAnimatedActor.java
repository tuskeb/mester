package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OneSpriteAnimatedActor extends OneSpriteActor {

    protected final TextureAtlas textureAtlas;
    protected float fps = 30;
    protected boolean running = true;
    protected boolean looping = true;


    public boolean isLooping() {
        return looping;
    }

    public void setLooping(boolean looping) {
        this.looping = looping;
    }


    public OneSpriteAnimatedActor(String file) {
        super(null);
        textureAtlas = new TextureAtlas(Gdx.files.internal(file));
        sprite = new Sprite(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    public OneSpriteAnimatedActor(TextureAtlas textureAtlas) {
        super(null);
        this.textureAtlas = textureAtlas;
        sprite = new Sprite(textureAtlas.getRegions().get(0).getTexture());
        init();
    }

    @Override
    public void init() {
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
        if (running) {
            if (!looping){
                if (textureAtlas.getRegions().size <=((int) (elapsedTime * fps))) {
                    stop();
                    return;
                }
            }
            setFrame(((int) (elapsedTime * fps)));
        }
    }

    public void setFrame(int frame)
    {
        sprite.setRegion(textureAtlas.getRegions().get(frame % textureAtlas.getRegions().size));
    }

    public void setFramePercent(float percent) {
        setFrame((int)(getFrameCount()*percent));
    }

    public int getFrameCount()
    {
        return textureAtlas.getRegions().size;
    }

    public void start()
    {
        running = true;
    }

    public void stop()
    {
        running = false;
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

}
