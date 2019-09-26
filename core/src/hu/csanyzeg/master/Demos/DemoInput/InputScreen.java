package hu.csanyzeg.master.Demos.DemoInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import hu.csanyzeg.master.Demos.GlobalClasses.Styles;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.UI.MyTextField;

/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class InputScreen extends MyScreen {

    MyStage myStage;

    public InputScreen(MyGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        myStage.act(delta);
        myStage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void init() {

        //Ha nem akarunk annyi fájlt, akkor lehet egy anonim osztály is.
        myStage = new MyStage(new ExtendViewport(1280,720,new OrthographicCamera(1280,720)), spriteBatch, game)
        {
            private String random()
            {
                return String.valueOf((int)(Math.random()*10));
            }

            //Itt a MyStage osztályt folytatjuk. Referenciaként a myStage változó lesz, amiből például a render is dolgozik.
            private Table table;


            public void init() {
                addBackEventStackListener();
                setBackGroundColor(0f,0.2f,0.4f);

                table = new Table();
                table.setWidth(640);
                table.setHeight(480);
                table.add(new MyButton(random(), Styles.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("+", Styles.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyButton(random(), Styles.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("=", Styles.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyTextField("?", Styles.getTextFieldStyle()));
                table.row();
                table.add(new MyButton(random(), Styles.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("*", Styles.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyButton(random(), Styles.getTextButtonStyle())).width(100).align(Align.center);
                table.add(new MyLabel("=", Styles.getLabelStyle())).width(100).align(Align.center);
                table.add(new MyTextField("?", Styles.getTextFieldStyle()));
                table.row();
                table.add(new MyButton("Vissza", Styles.getTextButtonStyle()){
                    @Override
                    public void init() {
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                game.setScreenBackByStackPop();
                            }
                        });
                    }
                }).colspan(5).align(Align.center);
                table.debug();
                addActor(table);

            }
        };
        Gdx.input.setInputProcessor(myStage);
    }
}
