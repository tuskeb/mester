package com.mygdx.game.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyBaseClasses.Game.InitableInterface;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by M on 12/14/2017.
 */

public abstract class MultiSpriteActor extends MyActor implements InitableInterface {
    protected HashMap<String, OffsetSprite> spriteMap = new HashMap<String, OffsetSprite>();

    /*  OffsetSprite... olyam mint egy tömb de simán fel lehet sorolni a paramétereket. Nincs fix hossza.
        https://docs.oracle.com/javase/tutorial/java/javaOO/arguments.html#varargs */
    public MultiSpriteActor(OffsetSprite... offsetSprites) {
        for (OffsetSprite spite: offsetSprites) {
            addSprite(spite);
        }
        init();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // TODO: 12/15/2017 test
        rotateBy(0.5f);
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
}
