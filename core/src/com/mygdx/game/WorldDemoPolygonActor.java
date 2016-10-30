package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoPolygonActor extends WorldActorGroup {
    public WorldDemoPolygonActor(World world, WorldBodyEditorLoader loader) {
        super(world, loader, "ufo2.png", BodyDef.BodyType.DynamicBody, 1.2f, 1.2f, 3.2f, false);
    }

    @Override
    public void init() {
        super.init();
        setSize(20,20);
        setPosition(2,10);
        OneSpriteStaticActor oneSpriteStaticActor;

        addActor(oneSpriteStaticActor = new OneSpriteStaticActor("box2d/ufo2.png"));
        oneSpriteStaticActor.setSize(20,20);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                getBody().applyForce((getWidth()/2-x)*1000,(getHeight()/2-y)*1000,x,y, true);
                //System.out.println(x + ":" + y);
            }
        });

    }
}
