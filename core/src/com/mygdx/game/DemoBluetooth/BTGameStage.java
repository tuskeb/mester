package com.mygdx.game.DemoBluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.DemoMenu.StarActor;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.BluetoothStage;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.OneSpriteAnimatedActor;
import com.mygdx.game.MyBaseClasses.iBluetooth;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

public class BTGameStage extends BluetoothStage {

    public BTGameStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    @Override
    public void init() {
        Gdx.input.setInputProcessor(this);
        addBackEventStackListener();
        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //return super.touchDown(event, x, y, pointer, button);
                addStar(x,y);
                Gdx.app.log("BTM", "Send: " + x + ";" + y);
                getBluetoothManager().sendMessage(x + ";" + y);
                return true;
            }
        });
    }

    protected void addStar(final float x, final float y){
        /*StarActor starActor = new StarActor();
        starActor.setFps(30);
        starActor.setPosition(x,y);
        addActor(starActor);*/
        addActor(new OneSpriteAnimatedActor(Assets.manager.get(Assets.STAR_TEXTUREATLAS)){
            @Override
            public void init() {
                super.init();
                addBackEventStackListener();
                setPosition(x,y);
                setSize(20,20);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        String s;
        if ((s = getBluetoothManager().getMessage()) != null){
            Gdx.app.log("BTM","Receive: " + s);
            String[] v = s.split(";");
            if (v.length==2) {
                addStar(Float.valueOf(v[0]), Float.valueOf(v[1]));
            }
        }
    }
}
