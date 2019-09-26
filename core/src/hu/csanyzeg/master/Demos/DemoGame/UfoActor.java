package hu.csanyzeg.master.Demos.DemoGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import hu.csanyzeg.master.Demos.GlobalClasses.Assets;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldActorGroup;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;

/**
 * Created by tuskeb on 2016. 10. 31..
 */

public class UfoActor extends WorldActorGroup {
    private float elapsedTime = 0;
    private float forceTimer = 0;
    //https://github.com/libgdx/libgdx/wiki/Preferences
    private Preferences preferences = Gdx.app.getPreferences(GameScreen.PREFS);
    private boolean shutdown = false;

    public UfoActor(World world, WorldBodyEditorLoader loader) {
        super(world, loader, "ufo.png", BodyDef.BodyType.DynamicBody, 5, 0.2f, 5, false);
    }

    @Override
    public void init() {
        super.init();
        setSize(1,1);
        addActor(new OneSpriteStaticActor(Assets.manager.get(AssetsGroupDemoGame.UFO_TEXTURE)){
            @Override
            public void init() {
                super.init();
                this.setSize(1,1);
            }
        });
        setPosition(MathUtils.random(2,13),10);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                shutDown();
            }
        });
    }

    @Override
    protected void afterAddToWorld() {
        super.afterAddToWorld();
        getBody().setFixedRotation(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (!shutdown) {
            if (forceTimer >= 0.24f) {
                if (getY() < 7) {
                    getBody().applyForceToCenter(MathUtils.random(-20, 20) * 10, 300, true);
                    forceTimer = 0;
                }
                if (getX() < 2) {
                    getBody().applyForceToCenter(MathUtils.random(100) + 100, 0, true);
                    forceTimer = 0;
                }
                if (getX() > 14) {
                    getBody().applyForceToCenter(MathUtils.random(100) * -1 - 100, 0, true);
                    forceTimer = 0;
                }


            }
            forceTimer += delta;
        }
    }

    public void shutDown()
    {
        if (shutdown) return;
        preferences.putInteger(GameStage.DEAD_UFO_COUNT, preferences.getInteger(GameStage.DEAD_UFO_COUNT,0)+1);
        addActor(new OneSpriteAnimatedActor(Assets.manager.get(Assets.EXPLOSION_TEXTUREATLAS))        {
            @Override
            public void init() {
                super.init();
                setTouchable(Touchable.disabled);
                setSize(3,3);
                setPosition(-1,-1);
                setFps(10);
                setLooping(false);
            }

            @Override
            public void stop() {
                super.stop();
                remove();
            }
        });

        shutdown = true;
        getBody().setFixedRotation(false);
    }

    public boolean isShutdown() {
        return shutdown;
    }

}
