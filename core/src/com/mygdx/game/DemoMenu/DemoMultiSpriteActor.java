package com.mygdx.game.DemoMenu;

import com.mygdx.game.MyBaseClasses.Scene2D.MultiSpriteActor;
import com.mygdx.game.MyBaseClasses.Scene2D.OffsetSprite;

/**
 * Created by M on 12/15/2017.
 */

public class DemoMultiSpriteActor extends MultiSpriteActor {
    public DemoMultiSpriteActor(OffsetSprite... offsetSprites) {
        super(offsetSprites);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(0.5f);
    }

    @Override
    public void init() {

    }

    @Override
    public void addSprite(OffsetSprite sprite, String key) {
        super.addSprite(sprite, key);
        sprite.setSize(50,50);
    }
}
