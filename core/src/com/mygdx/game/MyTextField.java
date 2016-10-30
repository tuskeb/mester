package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class MyTextField extends TextField implements InitableInterface{
    static TextFieldStyle style;

    static
    {
        style = new TextFieldStyle();
        style.background = new TextureRegionDrawable(new TextureRegion(new Texture("textbox.png")));
        style.background.setLeftWidth(style.background.getLeftWidth()+20);
        style.background.setRightWidth(style.background.getRightWidth()+20);
        style.font = Globals.FONT_HOBO_STD;
        style.cursor = new TextureRegionDrawable(new TextureRegion(new Texture("cursor.png")));
        style.cursor.setMinWidth(50);
        style.fontColor = Color.BLACK;
        Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
        p.setColor(0.4f,0.2f,0.8f, 0.5f);
        p.fill();
        style.selection = new TextureRegionDrawable(new TextureRegion(new Texture(p)));

    }

    public MyTextField(String text) {
        super(text, style);
        setWidth(300);
        setHeight(50);
        init();
    }

    @Override
    public void init() {

    }
}
