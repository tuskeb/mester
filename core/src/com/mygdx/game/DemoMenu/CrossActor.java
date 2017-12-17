package com.mygdx.game.DemoMenu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.Scene2D.MyCircle;
import com.mygdx.game.MyBaseClasses.Scene2D.MyRectangle;
import com.mygdx.game.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class CrossActor extends OneSpriteStaticActor {
    public CrossActor() {
        //super("badlogic.jpg");
        super(Assets.manager.get(Assets.BADLOGIC_TEXTURE));

        //addBaseCollisionRectangleShape();
        //addBaseCollisionCircleShape();
        addCollisionShape("LeftBottom1",new MyRectangle(20,20,40,40,getRotation(),40,getOriginX(), getOriginY()));
        addCollisionShape("LeftBottom2",new MyRectangle(40,40,40,40,getRotation(),0,getOriginX(), getOriginY()));
        addCollisionShape("Circle1", new MyCircle(90,180,30,getOriginX(), getOriginY()));
        addCollisionShape("Circle2", new MyCircle(0,0,60,getOriginX(), getOriginY()));

        setPosition(600,300);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //setPosition(getX()+1f, getY()+1f);
        rotateBy(1);
    }
}
