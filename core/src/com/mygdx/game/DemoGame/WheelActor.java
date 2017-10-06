package com.mygdx.game.DemoGame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import com.mygdx.game.MyBaseClasses.Box2dWorld.WorldActorGroup;
import com.mygdx.game.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;

/**
 * Created by tuskeb on 2016. 11. 01..
 */

public class WheelActor extends WorldActorGroup{
    @Override
    public void init() {
        super.init();
        setSize(2f, 2f);

        Actor a;
        addActor(a = new OneSpriteStaticActor(Assets.manager.get(AssetsGroupDemoGame.WHEEL_TEXTURE)));
        a.setSize(2f, 2f);
        a.setPosition(0, 0);
        setPosition(9, 3);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getBody().applyAngularImpulse(30, true);
                getBody().applyForceToCenter(0,40,true);
            }
        });
    }

    public WheelActor(World world, WorldBodyEditorLoader loader) {
        super(world, loader, "wheel.png", BodyDef.BodyType.DynamicBody, 3, 0.3f,11, false);
    }

}
