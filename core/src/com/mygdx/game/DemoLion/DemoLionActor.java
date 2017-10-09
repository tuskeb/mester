package com.mygdx.game.DemoLion;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2017. 10. 09..
 */

public class DemoLionActor extends OneSpriteAnimatedActor {
    public DemoLionActor() {
        super(Assets.manager.get(Assets.LION_TEXTURE));
        setFps(5);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()+1000*delta);
    }

}
