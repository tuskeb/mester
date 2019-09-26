package hu.csanyzeg.master.Demos.DemoGame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class BulletActor extends WorldActorGroup {
    @Override
    public void init() {
        super.init();
        setSize(0.3f, 0.3f);
        addToWorld();
        Actor a;
        addActor(a = new OneSpriteStaticActor(Assets.manager.get(AssetsGroupDemoGame.HOUSE_TEXTURE)));
        a.setSize(0.3f, 0.3f);
        a.setPosition(0, 0);
        getBody().setTransform(7, 3, 0);
        getBody().setLinearVelocity(0, 10);

    }

    public BulletActor(World world) {
        super(world, ShapeType.Rectangle, BodyDef.BodyType.KinematicBody, 1, 1, 1, true);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (getBody()!=null && getBody().getPosition().y>10){
            removeFromWorld();
            removeFromStage();
        }

    }
}
