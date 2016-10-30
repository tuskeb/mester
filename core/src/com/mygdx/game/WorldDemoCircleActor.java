package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoCircleActor extends WorldActorGroup {
    public WorldDemoCircleActor(World world, ShapeType shapeType, BodyDef.BodyType bodyType) {
        super(world, ShapeType.Circle, BodyDef.BodyType.DynamicBody, 0.2f, 0.2f, 0.2f, false);
    }
}
