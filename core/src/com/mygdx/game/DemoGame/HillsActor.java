package com.mygdx.game.DemoGame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.OneSpriteStaticActor;
import com.mygdx.game.MyBaseClasses.WorldActorGroup;
import com.mygdx.game.MyBaseClasses.WorldBodyEditorLoader;

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
        addActor(new OneSpriteStaticActor(Assets.manager.get(AssetsGroup.HILLS_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(16,9);
            }
        });
    }
}
