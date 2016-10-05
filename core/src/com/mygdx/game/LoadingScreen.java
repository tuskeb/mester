package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import javafx.scene.Camera;

public class LoadingScreen extends MyScreen {


    public LoadingScreen(Game game) {
		super(game);
        setBackGroundColor(0f, 0.5f, 0f);
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
            game.setScreen(new MenuScreen(game));
		}
		spriteBatch.begin();
		Globals.FONT_HOBO_STD.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)",0,50);
		spriteBatch.end();
	}

	@Override
	public void hide() {

	}
}
