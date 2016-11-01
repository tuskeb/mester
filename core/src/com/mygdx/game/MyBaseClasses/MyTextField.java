package com.mygdx.game.MyBaseClasses;

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

    public MyTextField(String text, TextFieldStyle style) {
        super(text, style);
        setWidth(300);
        setHeight(50);
        init();
    }

    @Override
    public void init() {

    }
}
