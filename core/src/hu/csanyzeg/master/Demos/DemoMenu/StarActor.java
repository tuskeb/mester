package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class StarActor extends OneSpriteAnimatedActor {
    Sound sound = Assets.manager.get(Assets.STAR_SOUND);

    public StarActor() {
        super(Assets.manager.get(Assets.STAR_TEXTUREATLAS));
        setFps(8);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PlaySound();
            }
        });
        //setOrigin(128,128);
    }

    public void PlaySound()
    {
        sound.play();
        getStage().getActors().removeValue(this, true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(0.1f);
    }
}
