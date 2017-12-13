package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    @Deprecated
    protected Rectangle rectangle = new Rectangle();
    @Deprecated
    protected Circle circle = new Circle();
    protected HashMap<String, MyShape> shapeMap;

    public HashMap<String, MyShape> getCollisionShapeMap(){
        return shapeMap;
    }

    public void addBaseCollisionRectangleShape(){
        addCollisionShape("Base",new MyRectangle(0,0,getWidth(),getHeight()));
    }

    public void addBaseCollisionCircleShape(){
        addCollisionShape("Base",new MyCircle(0,0,(float)Math.sqrt(getWidth()*getHeight())));
    }

    public void addCollisionShape(String name, MyShape shape){
        if (shapeMap == null){
            shapeMap = new HashMap<String, MyShape>();
        }
        shapeMap.put(name, shape);
    }

    public void removeCollisionShape(String name){
        if (shapeMap != null){
            shapeMap.remove(name);
        }
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        super.drawDebugBounds(shapes);
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                Vector2[] v = shape.getCorners();
                float w = (float) Math.cos(elapsedTime * 5f);
                Color c = new Color(w, w, w, w);
                shapes.setColor(c);
                for (int i = 0; i < v.length - 1; i++) {
                    shapes.line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
                }
                shapes.line(v[v.length - 1].x, v[v.length - 1].y, v[0].x, v[0].y);
                for (int i = 0; i < v.length - 2; i+=2) {
                    shapes.line(v[i].x, v[i].y, v[i + 2].x, v[i + 2].y);
                }
            }
        }
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
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setSize(getWidth(),getHeight());
            }
        }
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        rectangle.setPosition(getX(), getY());
        circle.setPosition(getX(), getY());
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setPosition(getX(),getY());
            }
        }
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setRotation(getRotation());
            }
        }
    }

    @Deprecated
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

    @Deprecated
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

    public static boolean overlaps(MyActor actorA, MyActor actorB){
        for(MyShape shapeA : actorA.getCollisionShapeMap().values()){
            for(MyShape shapeB : actorB.getCollisionShapeMap().values()){
                if(shapeA.overlaps(shapeB)){
                    return true;
                }
            }
        }
        return false;
    }


    public boolean overlaps(MyActor anotherActor){
        return overlaps(this, anotherActor);
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
