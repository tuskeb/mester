package hu.csanyzeg.master.Demos.DemoOtherScr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OtherScreen extends MyScreen {
    protected OtherStage otherStage;

    public OtherScreen(MyGame game) {
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
        otherStage = new OtherStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game);
        SinActor sinActor = new SinActor(5, 5);
        sinActor.setY(100);
        sinActor.setHeight(400);
        otherStage.addActor(sinActor);
        Gdx.input.setInputProcessor(otherStage);
    }
}
