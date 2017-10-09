package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class BadlActor extends OneSpriteStaticActor {
    public BadlActor() {
        super(Assets.manager.get(Assets.BADLOGIC_TEXTURE));
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                event.getStage().addActor(new BadlActor());
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(getX()+1, getY());
    }
}
