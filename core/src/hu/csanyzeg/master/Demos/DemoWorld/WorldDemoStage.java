package hu.csanyzeg.master.Demos.DemoWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;


/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldDemoStage extends MyStage {
    World world;

    public WorldDemoStage(Viewport viewport, Batch batch, MyGame game) {
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
        WorldDemoPolygonActor worldDemoPolygonActor = new WorldDemoPolygonActor(world,loader);
        worldDemoPolygonActor.addToWorld();
        addActor(worldDemoPolygonActor);


        addActor(new WorldDemoCircleActor(world));

        addActor(new WorldActorGroup(world, ShapeType.Rectangle, BodyDef.BodyType.DynamicBody, 0.4f, 4f, 2f, false){
            @Override
            public void init() {
                super.init();
                setSize(50,5);
                addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.BADLOGIC_TEXTURE)){
                    @Override
                    public void init() {
                        super.init();
                        setSize(40,5);
                    }
                });
                addActor(new OneSpriteStaticActor(Assets.manager.get(Assets.BADLOGIC_TEXTURE)){
                    @Override
                    public void init() {
                        super.init();
                        setSize(10,5);
                        setPosition(40,0);
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                removeFromWorld();
                            }
                        });
                    }
                });

                addToWorld();
                setPositionByLeftBottomCorner(0,0);
            }
        });
       // worldDemoPolygonActor.setSensor(true);
    }

    @Override
    public void act(float delta) {
        world.step(delta,1,1);
        super.act(delta);
    }
}
