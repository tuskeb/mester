package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


/**
 * Created by tuskeb on 2016. 10. 01..
 */
public class MyLabel extends Label {
    float elapsedtime =0;
    public static com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle style;

    static
    {

    }

    static
    {
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = Globals.FONT_HOBO_STD;
        style.fontColor = Color.YELLOW;
        Pixmap p = new Pixmap(1,1, Pixmap.Format.RGB888);
        p.setColor(0.4f,0.2f,0.8f, 0.5f);
        p.fill();
    }

    public MyLabel(String text) {
        super(text, style);
        setAlignment(Align.center);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedtime += delta;
        setFontScale(Math.abs((float)Math.sin(elapsedtime*2f))/2f+0.8f);
    }
}
