package hu.csanyzeg.master.Demos.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Bluetooth.BluetoothConnectedStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

import java.util.ArrayList;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BTGameStage extends BluetoothConnectedStage {

    public BTGameStage(MyGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    @Override
    public void init() {
        Gdx.input.setInputProcessor(this);
        addBackEventStackListener();
        addActor(new MyButton("Clear", Styles.getTextButtonStyle()){
            @Override
            public void init() {
                super.init();
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        clearStars();
                        sendMessage("clear");
                    }
                });
            }
        });
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //return super.touchDown(event, x, y, pointer, button);
                addStar(x,y);
                Gdx.app.log("BTM", "Send: " + x + ";" + y);
                sendMessage(x + ";" + y);
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                addStar(x,y);
                Gdx.app.log("BTM", "Send: " + x + ";" + y);
                sendMessage("star:" + x + ";" + y);
            }
        });
    }

    protected void addStar(final float x, final float y){
        addActor(new OneSpriteAnimatedActor(Assets.manager.get(Assets.STAR_TEXTUREATLAS)){
            @Override
            public void init() {
                super.init();
                setPosition(x,y);
                setSize(20,20);
            }
        });
    }

    public void clearStars(){
        ArrayList<Actor> actorList = new ArrayList<Actor>();
        for (Actor a: getActors()) {
            if (a instanceof OneSpriteAnimatedActor){
                actorList.add(a);
            }
        }
        for (Actor a: actorList) {
            getActors().removeValue(a, true);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        String s;
        while ((s = getMessage()) != null) {
            Gdx.app.log("BTM", "Receive: " + s);
            try {
                String[] u = s.split(":");
                if (u[0].compareTo("clear") == 0) {
                    clearStars();
                }
                if (u[0].compareTo("star") == 0) {
                    String[] v = u[1].split(";");
                    if (v.length == 2) {
                        addStar(Float.valueOf(v[0]), Float.valueOf(v[1]));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
