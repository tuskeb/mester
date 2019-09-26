package hu.csanyzeg.master.Demos.DemoLion;

import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tanulo on 2017. 10. 09..
 */

public class DemoTree extends OneSpriteAnimatedActor {
    boolean exploding = false;

    public DemoTree() {
        super(Assets.manager.get(Assets.TREE_TEXTURE));
        stop();
        setLooping(false);
        setFps(10);
        setSize(1024,1024);
    }

    public void explode(){
        if (exploding) return;
        exploding = true;
        start();
    }

    @Override
    protected void repeated() {
        super.repeated();
        getStage().getActors().removeValue(this, true);
    }

}
