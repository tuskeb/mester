package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;
import com.mygdx.game.MyGdxGame;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyStage extends Stage implements InitableInterface {
    public final MyGdxGame game;
    protected float elapsedTime = 0;

    public MyStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch);
        this.game = game;
        setCameraResetToCenterOfScreen();
        init();
    }

    public void addBackEventStackListener()    {
        addListener(new InputListener() {

            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode== Input.Keys.BACK) {
                    game.setScreenBackByStackPop();
                }
                return true;
            }
        });
    }

    public Actor getLastAdded() {
        return getActors().get(getActors().size-1);
    }

    public void setCameraZoomXY(float x, float y, float zoom)
    {
        OrthographicCamera c = (OrthographicCamera)getCamera();
        c.zoom=zoom;
        c.position.set(x,y,0);
        c.update();
    }

    private float cameraTargetX = 0;
    private float cameraTargetY = 0;
    private float cameraTargetZoom = 0;
    private float cameraMoveSpeed = 0;
    private float cameraZoomSpeed = 0;

    public void setCameraMoveToXY(float x, float y, float zoom, float speed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
        cameraZoomSpeed = speed;
    }

    public void setCameraMoveToXY(float x, float y, float zoom, float zoomSpeed, float moveSpeed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = moveSpeed;
        cameraZoomSpeed = zoomSpeed;
    }


    public void setCameraResetToCenterOfScreen()
    {
        if (getViewport() instanceof ExtendViewport) {
            OrthographicCamera c = (OrthographicCamera) getCamera();
            ExtendViewport v = (ExtendViewport) getViewport();
            c.setToOrtho(false, getViewport().getWorldWidth(), getViewport().getWorldHeight());
            c.translate((v.getWorldWidth() - v.getMinWorldWidth() / 2) < 0 ? 0 : -((v.getWorldWidth() - v.getMinWorldWidth()) / 2),
                    ((v.getWorldHeight() - v.getMinWorldHeight()) / 2) < 0 ? 0 : -((v.getWorldHeight() - v.getMinWorldHeight()) / 2));
            c.update();
        }
    }
    public void setCameraResetToLeftBottomOfScreen(){
        OrthographicCamera c = (OrthographicCamera)getCamera();
        Viewport v = getViewport();
        setCameraZoomXY(v.getWorldWidth()/2, v.getWorldHeight()/2,1);
        c.update();

    }

    public void resize(int screenWidth, int screenHeight){
        getViewport().update(screenWidth, screenHeight, true);
        resized();
    }

    protected void resized(){
        setCameraResetToCenterOfScreen();
    };

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
        OrthographicCamera c = (OrthographicCamera)getCamera();
        if (cameraTargetX!=c.position.x || cameraTargetY!=c.position.y || cameraTargetZoom!=c.zoom){
            if (Math.abs(c.position.x-cameraTargetX)<cameraMoveSpeed*delta) {
                c.position.x = (c.position.x + cameraTargetX) / 2;
            } else {
                if (c.position.x<cameraTargetX){
                    c.position.x += cameraMoveSpeed*delta;
                }else{
                    c.position.x -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.position.y-cameraTargetY)<cameraMoveSpeed*delta) {
                c.position.y = (c.position.y + cameraTargetY) / 2;
            } else {
                if (c.position.y<cameraTargetY){
                    c.position.y += cameraMoveSpeed*delta;
                }else{
                    c.position.y -= cameraMoveSpeed*delta;
                }
            }
            if (Math.abs(c.zoom-cameraTargetZoom)<cameraZoomSpeed*delta) {
                c.zoom = (c.zoom + cameraTargetZoom) / 2;
            } else {
                if (c.zoom<cameraTargetZoom){
                    c.zoom += cameraZoomSpeed*delta;
                }else{
                    c.zoom -= cameraZoomSpeed*delta;
                }
            }
            c.update();

        }

    }


    public void resetElapsedTime()
    {
        elapsedTime = 0;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
