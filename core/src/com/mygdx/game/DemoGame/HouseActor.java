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
 * Created by tuskeb on 2016. 10. 31..
 */

public class HouseActor extends WorldActorGroup {
    @Override
    public void init() {
        super.init();
        setPosition(5,2);
        setSize(3,3);
        Actor a;
        addActor(a = new OneSpriteStaticActor(Assets.manager.get(AssetsGroupDemoGame.HOUSE_TEXTURE)));
        a.setPosition(0,0);
        a.setSize(3,3);
        a.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getStage().addActor(new BulletActor(world));
            }
        });
    }

    public HouseActor(World world,  WorldBodyEditorLoader loader) {
        super(world, loader, "house.png", BodyDef.BodyType.StaticBody, 0.2f, 0.2f, 10, false);
    }
}
