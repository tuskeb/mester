package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends Stage {
    private BadlActor actor;
    private CrossActor crossActor;

    public MenuStage() {
        init();
    }

    public MenuStage(Viewport viewport) {
        super(viewport);
        init();
    }

    public MenuStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
        init();
    }

    public void init()
    {
        actor = new BadlActor();
        crossActor = new CrossActor();
        addActor(actor);
        addActor(crossActor);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
