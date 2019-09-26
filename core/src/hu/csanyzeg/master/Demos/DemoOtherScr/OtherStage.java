package hu.csanyzeg.master.Demos.DemoOtherScr;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class OtherStage extends MyStage {
    private TextButton textButton;


    public OtherStage(Viewport viewport, Batch batch, MyGame game) {
        super(viewport, batch, game);
    }


    public void init() {
        addBackEventStackListener();
        textButton = new MyButton("Vissza", Styles.getTextButtonStyle());
        textButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });

        addActor(textButton);

    }
}
