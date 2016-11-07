package com.mygdx.game.DemoWorld;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.WorldBodyEditorLoader;
import com.mygdx.game.MyGdxGame;


/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoStage extends MyStage {
    World world;

    public WorldDemoStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        setDebugAll(true);
        addBackEventStackListener();
        world = new World(new Vector2(0,0),false);
        WorldDemoRectangleActor worldDemoRectangleActor = new WorldDemoRectangleActor(world);
        worldDemoRectangleActor.addToWorld();
        addActor(worldDemoRectangleActor);


        WorldBodyEditorLoader loader = new WorldBodyEditorLoader(Gdx.files.internal("demogame/teszt.json"));
        com.mygdx.game.DemoWorld.WorldDemoPolygonActor worldDemoPolygonActor = new com.mygdx.game.DemoWorld.WorldDemoPolygonActor(world,loader);
        worldDemoPolygonActor.addToWorld();
        addActor(worldDemoPolygonActor);


        addActor(new WorldDemoCircleActor(world));
       // worldDemoPolygonActor.setSensor(true);
    }

    @Override
    public void act(float delta) {
        world.step(delta,1,1);
        super.act(delta);
    }
}
