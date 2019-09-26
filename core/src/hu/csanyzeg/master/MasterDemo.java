package hu.csanyzeg.master;

import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.Demos.DemoLoading.LoadingScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class MasterDemo extends MyGame {



	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

}
