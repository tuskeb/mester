package com.mygdx.game;

import com.badlogic.gdx.Game;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(Game game) {
		super(game);
    }

    @Override
	public void show() {
	    Assets.manager.finishLoading();
		Assets.load();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (Assets.manager.update()) {
            Assets.afterLoaded();
            //game.setScreen(new MenuScreen(game));
			game.setScreen(new WorldDemoScreen(game));
		}
		spriteBatch.begin();
		Globals.FONT_HOBO_STD.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",0,50);
		spriteBatch.end();
	}

	@Override
	public void hide() {

	}

	@Override
	public void init() {
		setBackGroundColor(0f, 0.5f, 0f);
	}
}
