package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {
    private BadlActor actor;
    private CrossActor crossActor;
    private TextButton textButton, textButton2;
    private ExplosionActor explosionActor;

    public MenuStage(Game game) {
        super(game);
    }

    public MenuStage(Viewport viewport, Batch batch, Game game) {
        super(viewport, batch, game);
    }

    public MenuStage(Viewport viewport, Game game) {
        super(viewport, game);
    }

    public void init(final Game game)
    {
        actor = new BadlActor();
        crossActor = new CrossActor();
        textButton = new MyButton("Előre");
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OtherScreen(game));
            }
        });

        textButton2 = new MyButton("Űrlap");
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InputScreen(game));
            }
        });

        addActor(actor);
        addActor(crossActor);
        textButton.setPosition(200,200);
        addActor(textButton);
        textButton2.setPosition(200,300);
        addActor(textButton2);
        addActor(new StarActor());
        explosionActor = new ExplosionActor();
        explosionActor.setPosition(0, getHeight() - explosionActor.getHeight());
        addActor(explosionActor);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
