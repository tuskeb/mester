package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

/**
 * Created by tuskeb on 2017. 01. 16..
 */

abstract public class BluetoothChooseServerClientStage extends MyStage {

    public BluetoothChooseServerClientStage(MyGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    abstract public void startServer();
    abstract public void startClient();

    @Override
    public void init() {
        Gdx.app.error("BTM", "Choose client/server");
        addBackEventStackListener();
        addActor(new MyButton("Start server", Styles.getTextButtonStyle()) {
            @Override
            public void init() {
                super.init();
                setPosition(200, 380);
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        startServer();
                    }
                });
            }
        });
        addActor(new MyButton("Start client", Styles.getTextButtonStyle()) {
            @Override
            public void init() {
                super.init();
                setPosition(700, 380);
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        startClient();
                    }
                });
            }
        });
    }
}



