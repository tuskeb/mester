package com.mygdx.game.DemoGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.DestructionListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.WorldBodyEditorLoader;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class GameStage extends MyStage {
    private World world;
    private float ufoCreateTimer=0;
    private WorldBodyEditorLoader worldBodyEditorLoader;
    public static final String DEAD_UFO_COUNT = "DEAD_UFO_COUNT";

    public GameStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    WheelActor wheelActor;
    UfoActor LastShutDownUfo = null;
    @Override
    public void init() {
        world = new World(new Vector2(0,-5),false);
        worldBodyEditorLoader = new WorldBodyEditorLoader(Gdx.files.internal("demogame/teszt.json"));



        addActor(new HillsActor(world, worldBodyEditorLoader){
            @Override
            public void init() {
                super.init();
                addToWorld();
            }
        });

        addActor(new HouseActor(world, worldBodyEditorLoader)
        {
            @Override
            public void init() {
                super.init();
                addToWorld();
            }
        });


        addActor(wheelActor = new WheelActor(world, worldBodyEditorLoader));
        wheelActor.addToWorld();


        addActor(new WheelActorKinematic(world, worldBodyEditorLoader));
        ((WheelActorKinematic)getLastAdded()).addToWorld();


        addBackEventStackListener();

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureA().getUserData() instanceof BulletActor && contact.getFixtureB().getUserData() instanceof UfoActor ||
                        contact.getFixtureB().getUserData() instanceof BulletActor && contact.getFixtureA().getUserData() instanceof UfoActor)
                {
                    UfoActor ufoActor = null;
                    BulletActor bulletActor = null;
                    if (contact.getFixtureA().getUserData() instanceof UfoActor){
                        ufoActor = (UfoActor) contact.getFixtureA().getUserData();
                        bulletActor =  (BulletActor) contact.getFixtureB().getUserData();
                    }else {
                        ufoActor = (UfoActor) contact.getFixtureB().getUserData();
                        bulletActor =  (BulletActor) contact.getFixtureA().getUserData();
                    }
                    if (!ufoActor.isShutdown()) {
                        ufoActor.shutDown();
                        LastShutDownUfo = ufoActor;
                        bulletActor.removeFromWorld();
                        bulletActor.removeFromStage();
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    public int countOfUfo()
    {
        int count = 0;
        for (Actor a: getActors()) {
            if (a instanceof UfoActor && !((UfoActor)a).isShutdown()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            world.setGravity(world.getGravity().rotate(60*delta));
            ((OrthographicCamera)getCamera()).rotate(-60*delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            world.setGravity(world.getGravity().rotate(-60 * delta));
            ((OrthographicCamera) getCamera()).rotate(60 * delta);
        }
        world.step(delta, 1, 1);
        super.act(delta);
        ufoCreateTimer += delta;
        if (ufoCreateTimer > 1 && countOfUfo() < 10) {
            addActor(new UfoActor(world, worldBodyEditorLoader) {
                @Override
                public void init() {
                    super.init();
                    addToWorld();
                }
            });
            ufoCreateTimer = 0;
        }
        /*System.out.println(wheelActor.getBody().getLinearVelocity());
        if (Math.abs(wheelActor.getBody().getLinearVelocity().y) > 0.01f || Math.abs(wheelActor.getBody().getLinearVelocity().x) > 0.01f) {
            setCameraMoveToXY(wheelActor.getX(), wheelActor.getY(), 0.5f, 2f);
        } else {
            setCameraMoveToXY(getCamera().viewportWidth / 2, getCamera().viewportHeight / 2, 1f, 2f);
        }*/
        if (LastShutDownUfo!=null) {
            setCameraMoveToXY(LastShutDownUfo.getX(), LastShutDownUfo.getY(), 0.5f, 3f);
            if (Math.abs(LastShutDownUfo.getBody().getLinearVelocity().y) < 0.01f && Math.abs(LastShutDownUfo.getBody().getLinearVelocity().x) < 0.01f){
                LastShutDownUfo = null;
            }
        } else {
            setCameraMoveToXY(getCamera().viewportWidth / 2, getCamera().viewportHeight / 2, 1f, 6f);
        }
    }



    public World getWorld() {
        return world;
    }

}
