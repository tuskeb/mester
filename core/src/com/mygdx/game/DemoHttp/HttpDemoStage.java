package com.mygdx.game.DemoHttp;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyBaseClasses.Http.HttpPreference;
import com.mygdx.game.MyBaseClasses.Scene2D.MyStage;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 02. 13..
 */

public class HttpDemoStage extends MyStage{
    HttpPreference preference;
    public HttpDemoStage(MyGdxGame game) {
        super(new ExtendViewport(1280, 720, new OrthographicCamera(1280, 720)), new SpriteBatch(), game);
        preference = new HttpPreference("http://www.csany-zeg.hu/",1,"12345");
        //preference.refresh();
    }

    @Override
    public void init() {

    }
}
