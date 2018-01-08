package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by M on 12/14/2017.
 */

public abstract class MultiSpriteActor extends MyActor implements InitableInterface {
    protected HashMap<String, OffsetSprite> spriteMap = new HashMap<String, OffsetSprite>();
    public static int debugLineNumbers = 16;

    public MultiSpriteActor() {
        super();
        init();
    }

    /*  OffsetSprite... olyam mint egy tömb de simán fel lehet sorolni a paramétereket. Nincs fix hossza.
            https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs */
    public MultiSpriteActor(OffsetSprite... offsetSprites) {
        super();
        for (OffsetSprite spite: offsetSprites) {
            addSprite(spite);
        }
        init();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (OffsetSprite sprite: spriteMap.values()) {
            sprite.draw(batch);
        }
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        for (OffsetSprite sprite: spriteMap.values()) {
            //sprite.setSize(getWidth(), getHeight());
            sprite.setOrigin(getOriginX() - sprite.getOffsetVector().x, getOriginY() - sprite.getOffsetVector().y);
        }
    }

    @Override
    public void setSize(float width, float height) {
        float changedWidth = getWidth()/width;
        float changedHeight = getHeight()/height;
        setWidth2(width);
        setHeight2(height);
        float spriteWidth, spriteHeight;
        for (OffsetSprite sprite : spriteMap.values()) {
            spriteWidth = sprite.getWidth();
            spriteHeight = sprite.getHeight();
            sprite.setSize(spriteWidth/changedWidth,spriteHeight/changedHeight);
        }

    }

    public void setWidth2(float width){
        super.setWidth(width);
    }

    public void setHeight2(float height){
        super.setHeight(height);
    }

    @Override
    public void setHeight(float height) {
        setSize(getWidth(),height);
    }

    @Override
    public void setWidth(float width) {
        setSize(width, getHeight());
    }


    @Override
    protected void positionChanged() {
        super.positionChanged();
        for (OffsetSprite sprite: spriteMap.values()) {
            sprite.setPosition(getX() + sprite.getOffsetVector().x, getY() + sprite.getOffsetVector().y);
        }
    }

    @Override
    protected void originChanged() {
        super.originChanged();
        for (OffsetSprite sprite: spriteMap.values()) {
            sprite.setOrigin(getOriginX() - sprite.getOffsetVector().x, getOriginY() - sprite.getOffsetVector().y);
        }
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        for (OffsetSprite sprite: spriteMap.values()) {
            sprite.setRotation(getRotation());
        }
    }

    public void addSprite(OffsetSprite sprite){
        addSprite(sprite, "Sprite"+ spriteMap.size());
    }

    public void addSprite(OffsetSprite sprite, String key){
        spriteMap.put(key, sprite);
        sprite.setPosition(getX() + sprite.getOffsetVector().x, getY() + sprite.getOffsetVector().y);
        sprite.setOrigin(getOriginX() - sprite.getOffsetVector().x, getOriginY() - sprite.getOffsetVector().y);
        //addCollisionShape("SpriteRect"+ spriteMap.size(), new MyRectangle(sprite.getOffsetVector().x, sprite.getOffsetVector().y, sprite.getHeight(), sprite.getWidth(), sprite.getRotation(), getOriginX(), getOriginY()));
    }

    public void removeSprite(String key) {
        if(spriteMap.containsKey(key)){
            System.out.println("removing sprite");
            spriteMap.remove(key);
        }else{
            System.out.println(key + " nem létezik, nem lehet törölni");
        }
    }

    public OffsetSprite getSprite(String key ){
        if(spriteMap.containsKey(key)) return spriteMap.get(key);
        else return null;
    }

    public HashMap<String, OffsetSprite> getSpritesMap() {
        return spriteMap;
    }

    public Collection<OffsetSprite> getSprites(){
        return spriteMap.values();
    }

    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        super.drawDebugBounds(shapes);
        if (spriteMap != null) {
            for (OffsetSprite sprite: spriteMap.values()) {
                float r = 5/255f + (float)Math.cos(elapsedTime * 10f)/5f;
                float g = 155/255f + (float)Math.cos(elapsedTime * 10f)/5f;
                float b = 222/255f + (float)Math.cos(elapsedTime * 10f)/5f;
                // TODO: 12/15/2017 Másik szín
                Color c = new Color(r, g, b, g);
                shapes.setColor(c);
                drawDebugLines(sprite.getCorners(),shapes);
            }
        }
    }

}
