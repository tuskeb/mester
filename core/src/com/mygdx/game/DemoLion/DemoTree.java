package com.mygdx.game.DemoLion;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2017. 10. 09..
 */

public class DemoTree extends OneSpriteAnimatedActor {
    boolean expliding = false;

    public DemoTree() {
        super(Assets.manager.get(Assets.TREE_TEXTURE));
        stop();
        setLooping(false);
        setFps(10);
        setSize(1024,1024);
    }

    public void explode(){
        if (expliding) return;
        expliding = true;
        start();
    }

    @Override
    protected void ended() {
        super.ended();
        getStage().getActors().removeValue(this, true);
    }
}
