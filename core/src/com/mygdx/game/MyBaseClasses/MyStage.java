package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

import java.lang.reflect.InvocationTargetException;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage implements InitableInterface {
    public final MyGdxGame game;

    public MyStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch);
        this.game = game;
        init();
    }

    public void addBackEventStackListener()    {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.BACK) {
                    game.setScreenBackByStackPop();
                }
                return true;
            }
        });
    }

    public Actor getLastAdded() {
        return getActors().get(getActors().size-1);
    }
}
