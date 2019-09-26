package hu.csanyzeg.master.Demos.DemoLoading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import hu.csanyzeg.master.Demos.DemoMenu.MenuScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

import hu.csanyzeg.master.Demos.GlobalClasses.Assets;


public class LoadingScreen extends MyScreen {


    public LoadingScreen(MyGame game) {
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
		bitmapFont.draw(spriteBatch,"Betöltés: " + Assets.manager.getLoadedAssets() + "/" + (Assets.manager.getQueuedAssets()+ Assets.manager.getLoadedAssets()) + " (" + ((int)(Assets.manager.getProgress()*100f)) + "%)", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
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
