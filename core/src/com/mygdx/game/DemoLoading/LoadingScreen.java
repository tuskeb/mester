package com.mygdx.game.DemoLoading;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.DemoMenu.MenuScreen;
import com.mygdx.game.MyBaseClasses.MyScreen;
import com.mygdx.game.GlobalClasses.*;
import com.mygdx.game.MyGdxGame;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(MyGdxGame game) {
		super(game);
    }
	BitmapFont bitmapFont = new BitmapFont();

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		spriteBatch.begin();
		bitmapFont.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",WORLD_WIDTH/2,WORLD_HEIGHT/2);
		spriteBatch.end();
		if (Assets.manager.update()) {
			Assets.afterLoaded();
			game.setScreen(new MenuScreen(game));
		}
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0f, 0f);
	}
}
