package hu.csanyzeg.master.Demos.DemoLion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

/**
 * Created by tanulo on 2017. 10. 09..
 */

public class DemoLionScreen extends MyScreen {

    DemoLionStage demoLionStage;

    public DemoLionScreen(MyGame game) {
        super(game);
        demoLionStage = new DemoLionStage(new ExtendViewport(1024,1024, new OrthographicCamera(1024,1024)), spriteBatch, game);
        demoLionStage.addBackEventStackListener();
        Gdx.input.setInputProcessor(demoLionStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        demoLionStage.act(delta);
        demoLionStage.draw();
    }

    @Override
    public void init() {

    }
}
