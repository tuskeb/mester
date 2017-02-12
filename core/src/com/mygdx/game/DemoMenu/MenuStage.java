package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoBluetooth.BluetoothScreen;
import com.mygdx.game.DemoHttp.HttpDemoScreen;
import com.mygdx.game.MyBaseClasses.BluetoothChooseServerClientStage;
import com.mygdx.game.DemoGame.GameScreen;
import com.mygdx.game.DemoInput.InputScreen;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.DemoOtherScr.OtherScreen;
import com.mygdx.game.MyBaseClasses.ShapeType;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {
    private BadlActor badlActor;
    private CrossActor crossActor;
    private TextButton textButton, textButton2, textButton3, textButton4, textButton5, textButton6;
    private ExplosionActor explosionActor;
    private Label utkozesMyLabel;


    public MenuStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
    }


    public void init()
    {
        addBackEventStackListener();
        badlActor = new BadlActor();
        crossActor = new CrossActor();
        textButton = new MyButton("Előre", game.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new OtherScreen(game));
            }
        });

        textButton2 = new MyButton("Űrlap", game.getTextButtonStyle());
        textButton2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new InputScreen(game));
            }
        });

        textButton3 = new MyButton("Box2D teszt", game.getTextButtonStyle());
        textButton3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new com.mygdx.game.DemoWorld.WorldDemoScreen(game));
            }
        });

        textButton4 = new MyButton("Box2d Demo", game.getTextButtonStyle());
        textButton4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new GameScreen(game));
            }
        });


        textButton5 = new MyButton("Bluetooth demo", game.getTextButtonStyle());
        textButton5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new BluetoothScreen(game));
            }
        });

        textButton6 = new MyButton("Http demo", game.getTextButtonStyle());
        textButton6.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new HttpDemoScreen(game));
            }
        });


        addActor(badlActor);
        addActor(crossActor);
        textButton.setPosition(200,100);
        textButton.debug();
        addActor(textButton);
        textButton2.setPosition(200,200);
        textButton2.debug();
        addActor(textButton2);
        textButton3.setPosition(200,300);
        textButton3.debug();
        addActor(textButton3);
        textButton4.setPosition(200,400);
        textButton4.debug();
        addActor(textButton4);
        textButton5.setPosition(200,500);
        textButton5.debug();
        textButton6.setPosition(200,600);
        textButton6.debug();
        addActor(textButton5);
        addActor(new StarActor());
        explosionActor = new ExplosionActor();
        explosionActor.setPosition(0, getHeight() - explosionActor.getHeight());
        addActor(explosionActor);
        utkozesMyLabel = new Label("",game.getLabelStyle());
        utkozesMyLabel.setPosition(getViewport().getWorldWidth()/2, getViewport().getWorldHeight()-25);
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
        addActor(new OneSpriteAnimatedActor("walk.atlas"));
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
