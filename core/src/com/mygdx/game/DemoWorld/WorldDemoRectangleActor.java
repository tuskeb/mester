package com.mygdx.game.DemoWorld;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyBaseClasses.WorldActorGroup;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyBaseClasses.ShapeType;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoRectangleActor extends WorldActorGroup {

    public WorldDemoRectangleActor(World world) {
        super(world, ShapeType.Rectangle, BodyDef.BodyType.DynamicBody, 0.2f, 0.2f, 5.2f, false);
    }

    @Override
    public void init() {
        super.init();
        setSize(5,5);
        setPosition(32,24);
        //setRotation(40);
        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //getBody().applyForce(-100,-100,x,y,true);
                getBody().applyForceToCenter(-20000,0,true);
            }
        });


        OneSpriteStaticActor a;
        addActor(a = new OneSpriteStaticActor(Assets.manager.get(Assets.BADLOGIC_TEXTURE)));
        a.setSize(5,5);
    }

    @Override
    protected void afterAddToWorld() {
        getBody().setAngularVelocity(5.5f);
    }
}
