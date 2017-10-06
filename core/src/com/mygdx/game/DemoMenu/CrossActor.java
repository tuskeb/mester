package com.mygdx.game.DemoMenu;

import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class CrossActor extends OneSpriteStaticActor {
    public CrossActor() {
        //super("badlogic.jpg");
        super(Assets.manager.get(Assets.BADLOGIC_TEXTURE));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1, getY()+1);
        rotateBy(1);
    }
}
