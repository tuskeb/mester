package com.mygdx.game.DemoHttp;

import com.badlogic.gdx.Net;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tuskeb on 2017. 02. 10..
 */

public class HttpDemoScreen extends MyScreen {
    public HttpDemoScreen(MyGdxGame game) {
        super(game);
        Net.HttpRequest httpRequest = new Net.HttpRequest(Net.HttpMethods.GET);
        httpRequest.setUrl("http://192.168.4.100");

    }



    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
