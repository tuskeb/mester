package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class ExplosionActor extends OneSpriteAnimatedActor {
    public ExplosionActor() {
        //super("explosion.atlas");
        super(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS));
        setFps(30);

        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                running = !running;
            }
        });
    }

}
