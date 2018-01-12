package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.Gdx;
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

import java.util.ArrayList;
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
        addCollisionShape("BaseRectangle",new MyRectangle(getWidth(),getHeight(),0,0,getOriginX(), getOriginY(), getRotation(), 0, true));
    }

    public void addBaseCollisionCircleShape() {
        addCollisionShape("BaseCircle", new MyCircle((float) Math.sqrt(getWidth() * getHeight()) / 2, 0, 0, getOriginX(), getOriginY(), getX(), getY(), true));
    }

    public void removeBaseCollisionRectangleShape(){
        removeCollisionShape("BaseRectangle");
    }

    public void removeBaseCollisionCircleShape(){
        removeCollisionShape("BaseCircle");
    }

    /**
     *
     * @param name
     * @param shape A pozíciója és a forgatása relatív az Actortól
     */
    public void addCollisionShape(String name, MyShape shape){
        if (shapeMap == null){
            shapeMap = new HashMap<String, MyShape>();
        }
        //shape.setOffset(shape.getX(), shape.getY());
        //shape.setPosition(getX(),getY());
        shape.setExtraData(this);
        shapeMap.put(name, shape);
    }

    public void removeCollisionShape(String name){
        if (shapeMap != null){
            shapeMap.remove(name);
        }
    }

    public MyShape getCollisionShape(String name){
        if (shapeMap != null){
            return shapeMap.get(name);
        }
        return null;
    }

    public static void drawDebugLines(Vector2[] v, ShapeRenderer shapes){
        for (int i = 0; i < v.length - 1; i++) {
            shapes.line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
        }
        shapes.line(v[v.length - 1].x, v[v.length - 1].y, v[0].x, v[0].y);
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        super.drawDebugBounds(shapes);
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                float w = (int)(0.8f + (float)Math.cos(elapsedTime * 10f)/5f+0.3f);
                shapes.setColor(new Color(w, w,w, w));
                drawDebugLines(shape.getCorners(), shapes);
                shapes.setColor(Color.CYAN);
                shapes.circle(shape.originX + shape.centerX + shape.offsetX, shape.originY + shape.centerY + shape.offsetY, getWidth()/20,5);
                shapes.setColor(Color.RED);
                shapes.circle(shape.realCenterX, shape.realCenterY, getWidth()/30,3);
            }
        }
    }


    public MyActor() {
        super();
        debug();
    }

    @Override
    public void init() {
        setSize(1,1);
        setOrigin(0.5f, 0.5f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        //setOrigin(getWidth() / 2, getHeight() / 2);
        rectangle.setSize(getWidth(), getHeight());
        circle.setRadius((getWidth() + getHeight()) / 2f);
        /*
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setSize(getWidth(),getHeight());
            }
        }
        */
    }


    @Override
    public void setSize(float width, float height) {
        float w = width / getWidth();
        float h = height / getHeight();

        if (shapeMap!=null) {
            for (MyShape shape : shapeMap.values()) {
                //shape.originX = shape.originX - (width - getWidth());
                //shape.originY = shape.originY - (height - getHeight());
                //shape.originX *= w;
                //shape.originY *= h;
                shape.setSize(shape.getWidth() * w, shape.getHeight() * h);
                shape.offsetX *= w;
                shape.offsetY *= h;
                shape.originX *= w;
                shape.originY *= h;
                //shape.originX -= getOriginX();
                //shape.originY -= getOriginY();
                //shape.originX -= (width-getWidth())/2;
                //shape.originY -= (height-getHeight())/2;
                //shape.calculateCenterXY();
                //shape.setOriginFromCenter(shape.originX +  shape.offsetX, -shape.originY + shape.offsetY);
                //shape.setOrigin(shape.originX*w - shape.offsetX + shape.width/2, shape.originY*h - shape.offsetY +  shape.height/2);

                //shape.originX -= shape.width / 2;
                //shape.originY -= height - getHeight();

            }

        }
        setOrigin(getOriginX(), getOriginY());
        super.setSize(width, height);
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
        if (actorA.getCollisionShapeMap() == null) return false;
        if (actorB.getCollisionShapeMap() == null) return false;
        for(MyShape shapeA : actorA.getCollisionShapeMap().values()){
            for(MyShape shapeB : actorB.getCollisionShapeMap().values()){
                if(shapeA.overlaps(shapeB)){
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<String> getActorAOverlappedShapeKeys(MyActor actorA, MyActor actorB){
        ArrayList<String> strings = new ArrayList<String>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if(((MyShape)shapeA.getValue()).overlaps((MyShape)shapeB.getValue())){
                    strings.add((String)(shapeA.getKey()));
                }
            }
        }
        return strings;
    }

    public static ArrayList<String> getActorBOverlappedShapeKeys(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeKeys(actorB,actorA);
    }

    public ArrayList<String> getMyOverlappedShapeKeys(MyActor anotherActor){
        return getActorAOverlappedShapeKeys(this, anotherActor);
    }

    public ArrayList<String> getOtherOverlappedShapeKeys(MyActor anotherActor){
        return getActorAOverlappedShapeKeys(anotherActor, this);
    }

    public static ArrayList<MyShape> getActorAOverlappedShapeValues(MyActor actorA, MyActor actorB){
        ArrayList<MyShape> strings = new ArrayList<MyShape>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if(((MyShape)shapeA.getValue()).overlaps((MyShape)shapeB.getValue())){
                    strings.add((MyShape)(shapeA.getValue()));
                }
            }
        }
        return strings;
    }

    public static ArrayList<MyShape> getActorBOverlappedShapeValues(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeValues(actorB,actorA);
    }

    public ArrayList<MyShape> getMyOverlappedShapeValues(MyActor anotherActor){
        return getActorAOverlappedShapeValues(this, anotherActor);
    }

    public ArrayList<MyShape> getOtherOverlappedShapeValues(MyActor anotherActor){
        return getActorAOverlappedShapeValues(anotherActor, this);
    }

    public static ArrayList<Map.Entry<String, MyShape>> getActorAOverlappedShapeEntries(MyActor actorA, MyActor actorB){
        ArrayList<Map.Entry<String, MyShape>> strings = new ArrayList<Map.Entry<String, MyShape>>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if(((MyShape)shapeA.getValue()).overlaps((MyShape)shapeB.getValue())){
                    strings.add((Map.Entry<String, MyShape>)(shapeA));
                }
            }
        }
        return strings;
    }

    public static ArrayList<Map.Entry<String, MyShape>> getActorBOverlappedShapeEntries(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeEntries(actorB,actorA);
    }

    public ArrayList<Map.Entry<String, MyShape>> getMyOverlappedShapeEntries(MyActor anotherActor){
        return getActorAOverlappedShapeEntries(this, anotherActor);
    }

    public ArrayList<Map.Entry<String, MyShape>> getOtherOverlappedShapeEntries(MyActor anotherActor){
        return getActorAOverlappedShapeEntries(anotherActor, this);
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


    @Override
    public void setOriginX(float originX) {
        super.setOriginX(originX);
        originChanged();
    }

    @Override
    public void setOriginY(float originY) {
        super.setOriginY(originY);
        originChanged();
    }

    @Override
    public void setOrigin(float originX, float originY) {
        if (shapeMap != null) {
            for (MyShape shape : getCollisionShapeMap().values()) {
                //shape.setOriginFromCenter(shape.originX + (originX-getOriginX()) + shape.offsetX, shape.originY + (originY-getOriginY())+shape.offsetY);
                //shape.setOriginFromCenter(shape.originX + (originX-getOriginX()) + shape.offsetX, shape.originY + (originY-getOriginY())+shape.offsetY);
                shape.setOrigin(originX, originY);
            }
        }
        super.setOrigin(originX, originY);
        originChanged();
    }

    @Override
    public void setOrigin(int alignment) {
        super.setOrigin(alignment);
        originChanged();
    }

    protected void originChanged(){

    }

    public boolean isInFrustum(){
        MyStage m = (MyStage)getStage();
        return m.isActorShowing(this);
    }
    public boolean isInFrustum(float zoom){
        MyStage m = (MyStage)getStage();
        return m.isActorShowing(this, zoom);
    }

    public void setWidthWhithAspectRatio(float width){
        setSize(width, getHeight()*(width/getWidth()));
    }

    public void seHeightWhithAspectRatio(float height){
        setSize(getWidth()*(height/getHeight()), height);
    }

    public void magnify(float mul){
        setSize(getWidth()*mul, getHeight()*mul);
    }
}
