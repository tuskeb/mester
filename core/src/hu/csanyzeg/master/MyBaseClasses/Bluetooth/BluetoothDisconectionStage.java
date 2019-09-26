package hu.csanyzeg.master.MyBaseClasses.Bluetooth;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;

/**
 * Created by tuskeb on 2017. 01. 18..
 */

abstract public class BluetoothDisconectionStage extends BluetoothStage {

    public BluetoothDisconectionStage(MyGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
    }

    @Override
    public void init() {
        addActor(new MyButton("Disconnected. Click here to exit.", Styles.getTextButtonStyle()) {
            @Override
            public void init() {
                super.init();
                setPosition(300, 380);
                addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        end();
                    }
                });
            }
        });
    }

    public abstract void end();

}
