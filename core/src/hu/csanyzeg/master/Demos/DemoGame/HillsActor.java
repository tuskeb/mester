package hu.csanyzeg.master.Demos.DemoGame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class HillsActor extends WorldActorGroup {
    public HillsActor(World world, WorldBodyEditorLoader loader) {
        super(world, loader, "hills.png", BodyDef.BodyType.StaticBody, 1, 0.1f, 1, false);
    }

    @Override
    public void init() {
        super.init();
        setSize(16,9);
        addActor(new OneSpriteStaticActor(Assets.manager.get(AssetsGroupDemoGame.HILLS_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(16,9);
            }
        });
    }
}
