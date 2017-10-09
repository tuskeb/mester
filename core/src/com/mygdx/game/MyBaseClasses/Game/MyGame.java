package com.mygdx.game.MyBaseClasses.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyBaseClasses.Scene2D.MyScreen;
import com.mygdx.game.MyGdxGame;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * Created by tanulo on 2017. 10. 06..
 */

abstract public class MyGame extends Game {

    /*
	public iBluetooth getBluetooth() {
		return bluetooth;
	}

	private iBluetooth bluetooth = null;

	public MyGdxGame() {
		bluetooth = BluetoothSingleton.getInstance().bluetoothManager;
	}
*/

    @Override
    public void create() {
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    }

    public final Stack<Class> backButtonStack = new Stack();

    @Override
    public void setScreen(Screen screen) {
        setScreen(screen,true);
    }

    public void setScreenBackByStackPop(){
        if (backButtonStack.size()>1){
            try {
                setScreen((MyScreen) backButtonStack.pop().getConstructor(MyGdxGame.class).newInstance(this),false);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Gdx.app.exit();
        }
    }


    public void setScreen(Screen screen, boolean pushToStack) {
        Screen prevScreen = getScreen();
        if (prevScreen!=null) {
            if (pushToStack) {backButtonStack.push(prevScreen.getClass());}
            prevScreen.dispose();
        }
        super.setScreen(screen);
    }

}
