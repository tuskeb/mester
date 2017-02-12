package com.mygdx.game.MyBaseClasses;

import com.badlogic.gdx.Net;

/**
 * Created by tuskeb on 2017. 02. 12..
 */

public class HttpCommand extends HttpConnect {

    public HttpCommand(String Url, int UserID, String Password) {
        super(Url, UserID, Password);
    }

    public String sendCommand(){
        return "";
    }


}
