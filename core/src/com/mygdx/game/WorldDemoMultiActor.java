package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoMultiActor extends WorldActorGroup {
    public WorldDemoMultiActor(World world, WorldBodyEditorLoader loader, String bodyID, BodyDef.BodyType bodyType) throws Exception {
        super(world, loader, bodyID, bodyType, 0.2f, 0.2f, 0.2f, false);
    }
}
