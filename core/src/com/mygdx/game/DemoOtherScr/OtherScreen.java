package com.mygdx.game.DemoOtherScr;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OtherScreen extends MyScreen {
    protected OtherStage otherStage;

    public OtherScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        otherStage.act(delta);
        otherStage.draw();

    }

    @Override
    public void init() {
        r = 1;
        g = 0.5f;
        b = 0.3f;
        otherStage = new OtherStage(viewport, spriteBatch, game);
        SinActor sinActor = new SinActor(5, 5);
        sinActor.setY(100);
        sinActor.setHeight(400);
        otherStage.addActor(sinActor);
        Gdx.input.setInputProcessor(otherStage);
    }
}
