package com.mygdx.game.DemoMenu;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class BadlActor extends OneSpriteStaticActor {
    public BadlActor() {
        super(Assets.manager.get(Assets.BADLOGIC_TEXTURE));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1, getY());
    }
}
