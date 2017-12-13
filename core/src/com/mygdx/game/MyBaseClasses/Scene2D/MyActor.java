package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyActor extends Actor implements InitableInterface {

    protected float elapsedTime = 0;
    protected Rectangle rectangle = new Rectangle();
    protected Circle circle = new Circle();
    protected HashMap<String, Rectangle> rectangleMap;

    public HashMap<String, Rectangle> getCollisionRectangleMap(){
        return rectangleMap;
    }

    public void addCollisionRectangle(String name, Rectangle rectangle){
        if (rectangleMap == null){
            rectangleMap = new HashMap<String, Rectangle>();
        }
        rectangleMap.put(name, rectangle);
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        super.drawDebugBounds(shapes);
    }

    public MyActor() {
        super();
        debug();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        setOrigin(getWidth() / 2, getHeight() / 2);
        rectangle.setSize(getWidth(), getHeight());
        circle.setRadius((getWidth() + getHeight()) / 2f);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        rectangle.setPosition(getX(), getY());
        circle.setPosition(getX(), getY());
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
    }

    public boolean overlaps(ShapeType shapeType, MyActor anotherActor)
    {
        switch (shapeType)
        {
            case Circle:
                return circle.overlaps(anotherActor.circle);
            case Rectangle:
                return rectangle.overlaps(anotherActor.rectangle);
        }
        return false;
    }

    public static boolean overlaps(ShapeType shapeType, MyActor actorA, MyActor actorB)
    {
        switch (shapeType)
        {
            case Circle:
                return actorA.circle.overlaps(actorB.circle);
            case Rectangle:
                return actorA.rectangle.overlaps(actorB.rectangle);
        }
        return false;
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



    public void fitToViewportRealWorldSize(){
        throw new NotImplementedException();
    }
    public void fitToViewportMinWorldSize(){
        throw new NotImplementedException();
    }
    public void stretchToViewportRealWorldSizeWithoutBlackBars(){
        throw new NotImplementedException();
    }
    public void stretchToViewportMinWorldSizeWithoutBlackBars(){
        throw new NotImplementedException();
    }
    public void fitToViewportRealWorldSizeWithoutBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null){
            ev = (ExtendViewport)s.getViewport();
            setSize(ev.getWorldHeight()*getWidth()/getHeight(), ev.getWorldHeight());
            if (getWidth() < ev.getWorldWidth()){
                float mul = ev.getWorldWidth()/getWidth();
                setSize(getWidth()*mul, getHeight()*mul);
            }
        }
    }
    public void fitToViewportMinWorldSizeWithoutBlackBars(){
        throw new NotImplementedException();
    }

    public void setPositionCenterOfActorToCenterOfViewport(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setPosition(ev.getWorldWidth()/2-getWidth()/2, ev.getWorldHeight()/2-getHeight()/2);
        }
    }

}
