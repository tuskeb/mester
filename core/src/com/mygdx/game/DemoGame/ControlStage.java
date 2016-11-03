package com.mygdx.game.DemoGame;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class ControlStage extends MyStage {
    private MyButton exitButton;

    public ControlStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }

    @Override
    public void init() {
        setDebugAll(true);
        addActor(new MyLabel("Kattints a házra vagy az ufókra vagy a kerékre!", game.getLabelStyle()){
            @Override
            public void init() {
                super.init();

                setFontScale(0.5f);

                setPosition(60,10);
            }

        });
        addActor(exitButton = new MyButton("X", game.getTextButtonStyle()){
            @Override
            public void init() {
                super.init();
                setSize(50,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });
        ExtendViewport w = (ExtendViewport)getViewport();
        resized();
        //getLastAdded().setPosition(0,0);
        //getLastAdded().setPosition(w.getWorldWidth()-400-getLastAdded().getWidth(),w.getWorldHeight()-120-getLastAdded().getHeight());
    }

    @Override
    protected void resized() {
        setCameraResetToLeftBottomOfScreen();
        ExtendViewport w = (ExtendViewport)getViewport();
        exitButton.setPosition(w.getWorldWidth()-getLastAdded().getWidth(),w.getWorldHeight()-getLastAdded().getHeight());
    }
}
