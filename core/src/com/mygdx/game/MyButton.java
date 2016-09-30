package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MyButton extends TextButton{
    static TextButton.TextButtonStyle textButtonStyle;
    static
    {
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = MyScreen.FONT_HOBO_STD;
    }
    public MyButton(String text) {
        super(text, textButtonStyle);
    }


    private void init()
    {
    }
}
