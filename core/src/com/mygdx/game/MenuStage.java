package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {
    private BadlActor badlActor;
    private CrossActor crossActor;
    private TextButton textButton, textButton2;
    private ExplosionActor explosionActor;
    private MyLabel utkozesMyLabel;

    public MenuStage(Game game) {
        super(game);
    }

    public MenuStage(Viewport viewport, Batch batch, Game game) {
        super(viewport, batch, game);
    }

    public MenuStage(Viewport viewport, Game game) {
        super(viewport, game);
    }

    public void init()
    {
        badlActor = new BadlActor();
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

        addActor(badlActor);
        addActor(crossActor);
        textButton.setPosition(200,200);
        textButton.debug();
        addActor(textButton);
        textButton2.setPosition(200,300);
        textButton2.debug();
        addActor(textButton2);
        addActor(new StarActor());
        explosionActor = new ExplosionActor();
        explosionActor.setPosition(0, getHeight() - explosionActor.getHeight());
        addActor(explosionActor);
        utkozesMyLabel = new MyLabel("");
        utkozesMyLabel.setPosition(MyScreen.WORLD_WIDTH/2,MyScreen.WORLD_HEIGHT-25);
        addActor(utkozesMyLabel);

        addActor(new CrossActor());
        getActors().get(getActors().size-1).setPosition(-300, 100);

        OneSpriteAnimatedActor a = new OneSpriteAnimatedActor("walk.atlas"){
            @Override
            public void act(float delta) {
                super.act(delta);
                setX(getX()+delta*70);
            }
        };

        a.setFps(5);
        addActor(a);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
        if (badlActor.overlaps(ShapeType.Rectangle, crossActor))
        {
            utkozesMyLabel.setText("Ütközés!");
        }
        else
        {
            utkozesMyLabel.setText("Nincs ütközés.");
        }
    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
