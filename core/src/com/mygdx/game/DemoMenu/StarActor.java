package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import com.mygdx.game.GlobalClasses.Assets;

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
    }

    public void PlaySound()
    {
        sound.play();
        getStage().getActors().removeValue(this, true);
    }
}
