package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class InputScreen extends MyScreen {

    MyStage myStage;

    public InputScreen(Game game) {
        super(game);

        //Ha nem akarunk annyi fájlt, akkor lehet egy anonim osztály is.
        myStage = new MyStage(viewport, spriteBatch, game)
        {
            private String random()
            {
                return String.valueOf((int)(Math.random()*10));
            }

            //Itt a MyStage osztályt folytatjuk. Referenciaként a myStage változó lesz, amiből például a render is dolgozik.
            private Table table;


            protected void init() {
                setBackGroundColor(0f,0.2f,0.4f);

                table = new Table();
                table.setWidth(640);
                table.setHeight(480);
                table.add(new MyButton(random())).width(100).align(Align.center);
                table.add(new MyLabel("+")).width(100).align(Align.center);
                table.add(new MyButton(random())).width(100).align(Align.center);
                table.add(new MyLabel("=")).width(100).align(Align.center);
                table.add(new MyTextField("?"));
                table.row();
                table.add(new MyButton(random())).width(100).align(Align.center);
                table.add(new MyLabel("*")).width(100).align(Align.center);
                table.add(new MyButton(random())).width(100).align(Align.center);
                table.add(new MyLabel("=")).width(100).align(Align.center);
                table.add(new MyTextField("?"));
                table.row();
                table.add(new MyButton("Vissza"){
                    @Override
                    protected void init() {
                        addListener(new ClickListener(){
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                super.clicked(event, x, y);
                                game.setScreen(new MenuScreen(game));
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

    @Override
    public void render(float delta) {
        super.render(delta);
        myStage.act(delta);
        myStage.draw();

    }

    @Override
    public void dispose() {
        super.dispose();
        myStage.dispose();
    }

}
